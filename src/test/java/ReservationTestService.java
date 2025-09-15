import org.example.Reservation;
import org.example.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    private Reservation reservation;
    private Room room;

    @BeforeEach
    void setUp() {

        reservation = new Reservation(
                1,
                1,
                1,
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(2),
                LocalDate.now(),
                "PENDING"
        );

        room = new Room(101, "Single", true, 1);
    }

    @Test
    void testMakeReservation() {

        reservation.setStatus("CONFIRMED");
        room.setAvailable(false);

        assertEquals("CONFIRMED", reservation.getStatus());
        assertFalse(room.isAvailable());
    }

    @Test
    void testCancelReservation() {

        reservation.setStatus("CONFIRMED");
        room.setAvailable(false);

        reservation.setStatus("CANCELED");
        room.setAvailable(true);

        assertEquals("CANCELED", reservation.getStatus());
        assertTrue(room.isAvailable());
    }
}
