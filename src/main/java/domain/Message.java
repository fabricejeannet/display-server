package domain;

import org.joda.time.DateTime;
import persistence.Root;

import java.util.UUID;

/**
 * Created by fabricejeannet on 10/10/2016.
 */
public class Message implements Root {

    public static Message create(MessageInformations messageInformations){
        Message message = new Message();
        message.id = UUID.randomUUID();
        return message;
    }

    private Message() {
    }

    public Object getId() {
        return id;
    }

    public String body;
    public UUID id;
    public String title;
    public String coverUrl;
    public DateTime startDate;
    public DateTime endDate;
    public boolean pinned;
    public String type;
}
