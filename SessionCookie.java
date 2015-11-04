import java.util.Random;

/**
 * Created by Peter on 10/29/2015.
 */
public class SessionCookie {
    private long id;
    private long activitytime;

    public SessionCookie(long id) {
        Random n = new Random();
        long number = 0;
        while (number > 0 && number <= 9999) {
            number = n.nextLong();
        }
        this.id = number;
    }

    public long getID() {
        return this.id;
    }

    public boolean hasTimedOut() {
        if (this.activitytime - System.currentTimeMillis() > this.activitytime) {
            return true;
        }
        return false;
    }

    public void updateTimeOfActivity() {
        this.activitytime = System.currentTimeMillis();
    }
}
