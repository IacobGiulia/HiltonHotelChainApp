package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/hotelDB";
        String DbUser = "postgres";
        String password = "1q2w3e";

        try {
            // Conectarea la baza de date
            Connection conn = DriverManager.getConnection(url, DbUser, password);
            System.out.println("Conexiune realizată cu succes!");

            // Închide conexiunea la final
            conn.close();
        } catch (SQLException e) {
            System.out.println("Eroare la conectarea la baza de date:");
            e.printStackTrace();
        }


    System.out.println("Are you connecting as a guest or as an admin ? ");
    Scanner sc = new Scanner(System.in);
    String user = sc.nextLine();



    }
}