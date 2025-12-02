package bus.station.repository;

import bus.station.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsByBusTripIdAndSeatNumber(Long id, String seatNumber);
}
