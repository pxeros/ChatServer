/**
 * Created by Peter on 10/29/2015.
 */
public class User {
    private String password;
    private String username;
    private SessionCookie cookie;

    User(String password, String username, SessionCookie cookie) {
        this.password = password;
        this.username = username;
        this.cookie = cookie;
    }

    public String getName() {
        return this.username;
    }

    public boolean checkPassword(String password) {
        if (password.equals(this.password)) {
            return true;
        }
        return false;
    }

    public SessionCookie getCookie() {
        return this.cookie;
    }

    public void setCookie(SessionCookie cookie) {
        this.cookie = cookie;
    }

}
