package bus.station.service;

import bus.station.model.BusTrip;
import bus.station.model.Passenger;
import bus.station.model.Ticket;
import bus.station.repository.BusTripRepository;
import bus.station.repository.PassengerRepository;
import bus.station.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final BusTripRepository busTripRepository;
    private final PassengerRepository passengerRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, BusTripRepository busTripRepository, PassengerRepository passengerRepository) {
        this.ticketRepository = ticketRepository;
        this.busTripRepository = busTripRepository;
        this.passengerRepository = passengerRepository;
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Ticket ID must be non-null and positive.");
        }
        return ticketRepository.findById(id);
    }

    @Transactional
    public Ticket save(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket object cannot be null.");
        }

        if (ticket.getBusTrip() == null || ticket.getBusTrip().getId() == null) {
            throw new IllegalArgumentException("Ticket must have a valid Bus Trip ID.");
        }

        if (ticket.getPassenger() == null || ticket.getPassenger().getId() == null) {
            throw new IllegalArgumentException("Ticket must have a valid Passenger ID.");
        }

        Optional<BusTrip> tripOpt = busTripRepository.findById(ticket.getBusTrip().getId());
        if (tripOpt.isEmpty()) {
            throw new IllegalArgumentException("Bus Trip with ID " + ticket.getBusTrip().getId() + " not found.");
        }
        BusTrip trip = tripOpt.get();

        Optional<Passenger> passengerOpt = passengerRepository.findById(ticket.getPassenger().getId());
        if (passengerOpt.isEmpty()) {
            throw new IllegalArgumentException("Passenger with ID " + ticket.getPassenger().getId() + " not found.");
        }
        Passenger passenger = passengerOpt.get();

        if (ticket.getSeatNumber() == null || ticket.getSeatNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Seat number is required.");
        }

        if (ticket.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        if (ticket.getId() == null) {
            if (trip.getAvailableSeats() <= 0) {
                throw new RuntimeException("No available seats on this trip!");
            }

            if (ticketRepository.existsByBusTripIdAndSeatNumber(trip.getId(), ticket.getSeatNumber())) {
                throw new IllegalArgumentException("Seat number " + ticket.getSeatNumber() + " is already taken on this trip.");
            }

            double finalPrice = trip.getBasePrice();
            if (passenger.getDateOfBirth() != null) {
                int age = Period.between(passenger.getDateOfBirth(), LocalDate.now()).getYears();
                if (age <= 12 || age >= 65) {
                    finalPrice = finalPrice * 0.80;
                }
            }
            ticket.setPrice(finalPrice);

            trip.setAvailableSeats(trip.getAvailableSeats() - 1);
            busTripRepository.save(trip);
        } else {
            Optional<Ticket> existingTicketOpt = ticketRepository.findById(ticket.getId());
            if (existingTicketOpt.isPresent() && !existingTicketOpt.get().getSeatNumber().equals(ticket.getSeatNumber())) {
                if (ticketRepository.existsByBusTripIdAndSeatNumber(trip.getId(), ticket.getSeatNumber())) {
                    throw new IllegalArgumentException("Seat number " + ticket.getSeatNumber() + " is already taken on this trip.");
                }
            }

            if (ticket.getPrice() == 0) {
                ticket.setPrice(existingTicketOpt.get().getPrice());
            }
        }

        return ticketRepository.save(ticket);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Ticket ID must be non-null and positive.");
        }

        Optional<Ticket> ticketOpt = ticketRepository.findById(id);

        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            BusTrip trip = ticket.getBusTrip();

            if (trip != null) {
                Optional<BusTrip> managedTripOpt = busTripRepository.findById(trip.getId());
                if (managedTripOpt.isPresent()) {
                    BusTrip managedTrip = managedTripOpt.get();
                    managedTrip.setAvailableSeats(managedTrip.getAvailableSeats() + 1);
                    busTripRepository.save(managedTrip);
                }
            }

            ticketRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Ticket with ID " + id + " not found for deletion.");
        }
    }
}