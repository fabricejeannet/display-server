package command.message;

import command.Command;
import domain.Message;
import domain.MessageInformations;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import persistence.Repositories;

import java.util.UUID;

/**
 * Created by fabricejeannet on 12/10/2016.
 */
public class MessageCommand implements Command<UUID> {

    public MessageCommand(MessageInformations messageInformations) {
        this.messageInformations = messageInformations;
    }

    public UUID execute() {
        Message message = Message.create(messageInformations);
        message.body = messageInformations.body;
        message.title = messageInformations.title;
        message.coverUrl = messageInformations.coverUrl;
        message.startDate = toDateTime(messageInformations.startDate);
        message.endDate = toDateTime(messageInformations.endDate);
        message.pinned = messageInformations.pinned;
        message.type = messageInformations.type;
        Repositories.messages().add(message);

        return message.id;
    }

    public DateTime toDateTime(String dateStr) {
        DateTime dateTime = null;
        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
            dateTime = formatter.parseDateTime(dateStr);
        } finally {
            return dateTime;
        }
    }

    private MessageInformations messageInformations;
}
