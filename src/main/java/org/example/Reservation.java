package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Reservation {
    private int reservationId;
    private int guestId;
    private int hotelId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate reservationDate;
    private String status;

    public Reservation(int reservationId, int guestId, int hotelId, LocalDate checkInDate, LocalDate checkOutDate, LocalDate reservationDate, String status) {
        this.reservationId = reservationId;
        this.guestId = guestId;
        this.hotelId = hotelId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationDate = reservationDate;
        this.status = status;
    }
    public int getReservationId() {
        return reservationId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
    public int getGuestId() {
        return guestId;
    }
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public LocalDate getReservationDate() {
        return reservationDate;
    }
    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void makeReservation(Connection conn, Room room) {
        if (room.isAvailable()) {
            try {
                String updateRoomSql = "UPDATE room SET available = ? WHERE room_number = ?";
                try (PreparedStatement stmt = conn.prepareStatement(updateRoomSql)) {
                    stmt.setBoolean(1, false);
                    stmt.setInt(2, room.getRoomNumber());
                    stmt.executeUpdate();
                }

                this.status = "CONFIRMED";
                String insertReservationSql =
                        "INSERT INTO reservation (reservation_id, guest_id, hotel_id, check_in_date, check_out_date, reservation_date, status) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement stmt = conn.prepareStatement(insertReservationSql)) {
                    stmt.setInt(1, this.getReservationId());
                    stmt.setInt(2, this.getGuestId());
                    stmt.setInt(3, this.getHotelId());
                    stmt.setDate(4, java.sql.Date.valueOf(this.getCheckInDate()));
                    stmt.setDate(5, java.sql.Date.valueOf(this.getCheckOutDate()));
                    stmt.setDate(6, java.sql.Date.valueOf(this.getReservationDate()));
                    stmt.setString(7, this.getStatus());
                    stmt.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                this.status = "NOT CONFIRMED";
            }
        } else {
            this.status = "NOT CONFIRMED";
        }
    }

    public void cancelRoomReservation(Connection conn, int reservationId) {
        try {

            String selectReservationSql = "SELECT hotel_id, guest_id FROM reservation WHERE reservation_id = ?";
            int hotelId = -1;
            int guestId = -1;

            try (PreparedStatement stmt = conn.prepareStatement(selectReservationSql)) {
                stmt.setInt(1, reservationId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    hotelId = rs.getInt("hotel_id");
                    guestId = rs.getInt("guest_id");
                } else {
                    System.out.println("Reservation not found!");
                    return;
                }
            }


            String updateReservationSql = "UPDATE reservation SET status = ? WHERE reservation_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateReservationSql)) {
                stmt.setString(1, "CANCELED");
                stmt.setInt(2, reservationId);
                stmt.executeUpdate();
            }

            String updateRoomSql = "UPDATE room SET available = ? WHERE hotel_id = ? AND room_number IN " +
                    "(SELECT room_number FROM reservation WHERE reservation_id = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(updateRoomSql)) {
                stmt.setBoolean(1, true);
                stmt.setInt(2, hotelId);
                stmt.setInt(3, reservationId);
                stmt.executeUpdate();
            }

            this.status = "CANCELED";
            System.out.println("Reservation " + reservationId + " has been canceled and room made available.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", hotelId=" + hotelId +
                ", guestId=" + guestId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", reservationDate=" + reservationDate +
                ", status='" + status + '\'' +
                '}';
    }

}
