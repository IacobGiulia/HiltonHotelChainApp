package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/hotelDB";
        String DbUser = "postgres";
        String password = "1q2w3e";

        try (Connection conn = DriverManager.getConnection(url, DbUser, password)) {
            System.out.println("Conexiune realizată cu succes!");
            Scanner sc = new Scanner(System.in);
            System.out.println("Are you connecting as a guest or as an admin? ");
            String userType = sc.nextLine().trim().toLowerCase();
            if (userType.equals("guest")) {
                guestMenu(conn, sc);
            } else if (userType.equals("admin")) {
                adminMenu(conn, sc);
            } else {
                System.out.println("Invalid user type!");
            }
        } catch (SQLException e) {
            System.out.println("Eroare conectare baza de date");
            e.printStackTrace();
        }


    }

    private static void guestMenu(Connection conn, Scanner sc) {
        while (true) {
            System.out.println("\n--- Guest Menu ---");
            System.out.println("1. Make a reservation");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. View my reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consumăm enter-ul

            switch (choice) {
                case 1:
                    makeReservation(conn, sc);
                    break;
                case 2:
                    cancelReservation(conn, sc);
                    break;
                case 3:
                    viewReservations(conn, sc);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
    private static void adminMenu(Connection conn, Scanner sc) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Hotel");
            System.out.println("2. Add Room");
            System.out.println("3. Add Guest");
            System.out.println("4. View all reservations for a hotel");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addHotel(conn, sc);
                    break;
                case 2:
                    addRoom(conn, sc);
                    break;
                case 3:
                    addGuest(conn, sc);
                    break;
                case 4:
                    viewReservationsForHotel(conn, sc);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option!");

        }
    }

    }
    private static void makeReservation(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter reservation id: ");
            int resId = sc.nextInt();
            System.out.print("Enter guest id: ");
            int guestId = sc.nextInt();
            System.out.print("Enter hotel id: ");
            int hotelId = sc.nextInt();
            System.out.print("Enter room number: ");
            int roomNumber = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter check-in date (yyyy-mm-dd): ");
            LocalDate checkIn = LocalDate.parse(sc.nextLine());
            System.out.print("Enter check-out date (yyyy-mm-dd): ");
            LocalDate checkOut = LocalDate.parse(sc.nextLine());

            Room room = new Room(roomNumber, "", true, hotelId); // Simplificat
            Reservation reservation = new Reservation(resId, guestId, hotelId, roomNumber, checkIn, checkOut, LocalDate.now(), "PENDING");

            reservation.makeReservationsForRooms(conn,room);
            System.out.println("Reservation status: " + reservation.getStatus());
        } catch (Exception e) {
            System.out.println("Error making reservation!");
            e.printStackTrace();
        }
    }

    private static void cancelReservation(Connection conn, Scanner sc) {
        System.out.print("Enter reservation id to cancel: ");
        int resId = sc.nextInt();
        Reservation reservation = new Reservation(resId, 0,0,0,null,null,null,"");
        reservation.cancelRoomReservation(conn, resId);
    }

    private static void viewReservations(Connection conn, Scanner sc) {
        System.out.print("Enter hotel id to view your reservations: ");
        int hotelId = sc.nextInt();
        List<Reservation> reservations = Reservation.getReservationsForHotel(conn, hotelId);
        for (Reservation res : reservations) {
            System.out.println(res);
        }
    }

    private static void addHotel(Connection conn, Scanner sc) {
        System.out.print("Enter hotel name: ");
        String name = sc.nextLine();
        System.out.print("Enter hotel location: ");
        String location = sc.nextLine();

        Hotel hotel = new Hotel(0, name, location);
        hotel.addHotel(conn, hotel);
    }

    private static void addRoom(Connection conn, Scanner sc) {
        System.out.print("Enter room number: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter room type: ");
        String type = sc.nextLine();
        System.out.print("Enter hotel id: ");
        int hotelId = sc.nextInt();

        Room room = new Room(roomNumber, type, true, hotelId);
        room.addRoom(conn, room);
    }

    private static void addGuest(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter guest id: ");
            int guestId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter guest name: ");
            String name = sc.nextLine();
            System.out.print("Enter guest email: ");
            String email = sc.nextLine();
            System.out.print("Enter guest phone: ");
            String phone = sc.nextLine();
            System.out.print("Enter hotel id: ");
            int hotelId = sc.nextInt();

            Guest guest = new Guest(guestId, name, email, phone, hotelId);
            guest.addGuests(conn, guest);
            System.out.println("Guest added successfully!");

        } catch (Exception e) {
            System.out.println("Error adding guest!");
            e.printStackTrace();
        }
    }


    private static void viewReservationsForHotel(Connection conn, Scanner sc) {
        System.out.print("Enter hotel id: ");
        int hotelId = sc.nextInt();
        List<Reservation> reservations = Reservation.getReservationsForHotel(conn, hotelId);
        for (Reservation res : reservations) {
            System.out.println(res);
        }
    }

}