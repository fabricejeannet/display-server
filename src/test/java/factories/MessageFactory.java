package factories;

import command.message.MessageCommand;
import domain.MessageInformations;

/**
 * Created by fabricejeannet on 10/10/2016.
 */
public class MessageFactory {

    public MessageInformations messageInformations(){
        MessageInformations messageInformations = new MessageInformations();
        messageInformations.body ="The body";
        messageInformations.title = "The title";
        return messageInformations;
    }

    public void multiple(int count){

        for (int index = 0; index < count; index++) {
            MessageInformations messageInformations = messageInformations();
            messageInformations.title = "message " + index;
            new MessageCommand(messageInformations).execute();
        }

    }




}
