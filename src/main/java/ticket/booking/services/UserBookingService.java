package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {
    private static final String USER_PATH = System.getProperty("user.dir") + "/localDB/users.json";
    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();

    public UserBookingService(User user) throws IOException {
        this.user = user;
        File users = new File(USER_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    public Boolean loginService() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equalsIgnoreCase(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File userFile = new File(USER_PATH);
        objectMapper.writeValue(userFile, userList);
    }

    public void fetchBooking() {
        Optional<User> userFetched = userList.stream().filter(user1 -> {
            return user1.getName().equalsIgnoreCase(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
    }

    public Boolean cancelBooking(String ticketId) {
        if (ticketId == null || ticketId.isEmpty()) {
            System.err.println("Ticket Id can not be null or empty");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter the Ticket Id to cancel: ");
            ticketId = scanner.next();
            if (ticketId == null || ticketId.isEmpty()) return Boolean.FALSE;
        }


        String finalTicketId = ticketId;
        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equalsIgnoreCase(finalTicketId));

        if (removed) {
            System.out.printf("Ticket with Id %s is has been canceled", finalTicketId);
            return Boolean.TRUE;
        } else {
            System.out.printf("No ticket with Id found %s", finalTicketId);
            return Boolean.FALSE;
        }
    }
}
