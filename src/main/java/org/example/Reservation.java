package org.example;

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
