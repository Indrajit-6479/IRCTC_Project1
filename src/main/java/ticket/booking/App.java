package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class App {
    public static void main(String[] args) {
        System.out.println("Running Train Booking Service...");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        UserBookingService userBookingService;
        try {
            userBookingService = new UserBookingService();
        } catch (IOException ex) {
            System.err.printf("Exception in main %s", ex.getMessage());
            return;
        }
        while (option != 7) {
            System.out.println("Choose option");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exist the App");

            option = sc.nextInt();
            Train trainSelectedForBooking = new Train();
            switch (option) {
                case 1 -> {
                    System.out.println("Enter the User Name to sign up");
                    String userNameToSignup = sc.next();
                    System.out.println("Enter the Password to sign up");
                    String passwordToSignup = sc.next();
                    User userToSignup = new User(userNameToSignup, passwordToSignup,
                            UserServiceUtil.hashPassword(passwordToSignup),
                            new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUp(userToSignup);
                }
                case 2 -> {

                }
            }
        }
    }
}
