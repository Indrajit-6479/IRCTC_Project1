package ticket.booking.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private Date date;
    private Train train;

    public String getTicketInfo() {
        return String.format("Ticket id %s, belongs to user %s from %s to %s", ticketId, userId, source, destination);
    }
}
