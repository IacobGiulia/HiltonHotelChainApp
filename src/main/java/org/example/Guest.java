package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class Guest {

    private int guestId;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private int hotelId;

    public Guest(int guestId, String guestName, String guestEmail, String guestPhone, int hotelId) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.guestPhone = guestPhone;
        this.hotelId = hotelId;
    }

    public int getGuestId() {
        return guestId;
    }
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }
    public String getGuestName() {
        return guestName;
    }
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
    public String getGuestEmail() {
        return guestEmail;
    }
    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }
    public String getGuestPhone() {
        return guestPhone;
    }
    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }
    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    void addGuests(Connection conn, Guest guest) {
        String sql="INSERT INTO guest (guest_name, guest_email, guest_phone, hotel_id) VALUES (?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, guest.getGuestName());
            stmt.setString(2, guest.getGuestEmail());
            stmt.setString(3, guest.getGuestPhone());
            stmt.setInt(4, guest.getHotelId());
            stmt.executeUpdate();
            System.out.println("Guest successfully added!");
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestId=" + guestId +
                ", name='" + guestName + '\'' +
                ", email='" + guestEmail + '\'' +
                ", phone='" + guestPhone + '\'' +
                ", hotelId=" + hotelId +
                '}';
    }

}
