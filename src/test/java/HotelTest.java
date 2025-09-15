
import org.example.Hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel(1, "Test Hotel", "Test Location");
    }

    @Test
    void testAddHotelDetails() {
        assertEquals("Test Hotel", hotel.getHotelName());
        assertEquals("Test Location", hotel.getHotelLocation());

        hotel.setHotelName("Updated Hotel");
        hotel.setHotelLocation("Updated Location");

        assertEquals("Updated Hotel", hotel.getHotelName());
        assertEquals("Updated Location", hotel.getHotelLocation());
    }
}