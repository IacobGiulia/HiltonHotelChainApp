package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomNumber;
    private String roomType;
    private boolean isAvailable;
    private int hotelId;

    public Room(int roomNumber, String roomType, boolean isAvailable, int hotelId) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
        this.hotelId = hotelId;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getRoomType() {
        return roomType;
    }
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void addRoom(Connection conn, Room room) {
        String sql="INSERT INTO room (room_type, available, hotel_id) VALUES (?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, room.getRoomType());
            stmt.setBoolean(2, room.isAvailable());
            stmt.setInt(3, room.getHotelId());
            stmt.executeUpdate();
            System.out.println("Room successfully added!");
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public List<Room> getRoomsForHotel(Connection conn, int hotelId) {
        List<Room> rooms = new ArrayList<Room>();
        String sql = "SELECT room_number, room_type, available, hotel_id FROM room WHERE hotel_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("room_number"),
                        rs.getString("type"),
                        rs.getBoolean("available"),
                        rs.getInt("hotel_id")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Room getRoom(Connection conn, int roomNumber)
    {
        String sql="SELECT room_number, room_type, available, hotel_id FROM room WHERE room_number = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, roomNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Room(
                        rs.getInt("room_number"),
                        rs.getString("type"),
                        rs.getBoolean("available"),
                        rs.getInt("hotel_id")
                );
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", type='" + roomType + '\'' +
                ", available=" + isAvailable +
                ", hotelId=" + hotelId +
                '}';
    }
}
