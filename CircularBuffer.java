import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ruhana on 10/29/15.
 */
public class CircularBuffer {
    private int size = 0;
    private static int digits = 0; // does making this static work???
    private String bufferMessage = "";
    private String [] messagesInBuffer;

    public CircularBuffer(int size) {
        this.size = size;
        this.messagesInBuffer = new String [size];
    }

    public void put(String message) {
        if (message == null) {

        }
        ////////// composes number
        else if (digits == 9999) {
            digits = 0;
        }

        if (digits < 10000 && digits >= 0) {
            digits = digits + 1;
            String digitString = String.valueOf(digits);
            if (digitString.length() <= 4) {
                for (int i = 0; i < 4 - digitString.length(); i ++ ) {
                    digitString = "0" + digitString;
                }
            }
            bufferMessage =  digitString + ") " + message;
        }
        ///////// PUTTING THE STRING INTO AN ARRAY

        for (int i = 0; i < size; i ++) {
            if (i == size - 1) {
                continue;
            }
            else {
                messagesInBuffer[i + 1] = messagesInBuffer [i];
            }

        }
        messagesInBuffer[0] = bufferMessage;

    }

    public String[] getNewest(int numMessages) {
        if (numMessages < 0) {
            return null;
        }
        else if (numMessages >= size) {
            return messagesInBuffer;
        }
        for (int i = 0; i < size; i ++ ) {
            if (messagesInBuffer [i].equals("")) {
                numMessages = i;
                break;
            }
        }

            return Arrays.copyOf(messagesInBuffer, numMessages);


    }
}
