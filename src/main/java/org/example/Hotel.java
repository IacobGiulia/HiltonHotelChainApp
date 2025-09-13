package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.*;

public class Hotel {
    private int hotelId;
    private String hotelName;
    private String hotelLocation;
    private List<Room> rooms;
    private List<Reservation> reservations;
    private List<Guest> guests;

    Hotel(int hotelId, String hotelName, String hotelLocation) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelLocation = hotelLocation;
    }
    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
    public String getHotelName() {
        return hotelName;
    }
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public String getHotelLocation() {
        return hotelLocation;
    }
    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }
    public void addHotel(Connection conn, Hotel hotel) {
        String sql = "INSERT INTO hotel (hotel_name, hotel_location) VALUES (?, ?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hotel.getHotelName());
            stmt.setString(2, hotel.getHotelLocation());
            stmt.executeUpdate();
            System.out.println("Hotel successfully added");

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public Hotel getHotel(Connection conn, int id) {
        String sql="SELECT hotel_id, hotel_name, hotel_location FROM hotel WHERE hotel_id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Hotel(
                        rs.getInt("hotel_id"),
                        rs.getString("name"),
                        rs.getString("location")
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", name='" + hotelName + '\'' +
                ", location='" + hotelLocation + '\'' +
                '}';
    }

}
