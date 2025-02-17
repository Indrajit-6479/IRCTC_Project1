package ticket.booking;

import ticket.booking.services.UserBookingService;

import java.io.IOException;
import java.util.Scanner;

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
        }
    }
}
