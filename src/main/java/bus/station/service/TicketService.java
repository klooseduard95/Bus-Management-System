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
        return ticketRepository.findById(id);
    }

    @Transactional
    public Ticket save(Ticket ticket) {


        if (ticket.getBusTrip() == null || ticket.getPassenger() == null) {
            throw new IllegalArgumentException("Ticket must have a valid Trip and Passenger.");
        }

        BusTrip trip = ticket.getBusTrip();
        Passenger passenger = ticket.getPassenger();

        if (ticket.getId() == null) {

            if (trip.getAvailableSeats() <= 0) {
                throw new RuntimeException("No available seats on this trip!");
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
        }

        return ticketRepository.save(ticket);
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(id);

        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            BusTrip trip = ticket.getBusTrip();

            if (trip != null) {
                trip.setAvailableSeats(trip.getAvailableSeats() + 1);
                busTripRepository.save(trip);
            }

            ticketRepository.deleteById(id);
        }
    }
}