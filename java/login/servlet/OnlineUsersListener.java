package login.servlet;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineUsersListener implements HttpSessionListener {
    private static int onlineUsersCount = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        onlineUsersCount++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        onlineUsersCount--;
    }

    public static int getOnlineUsersCount() {
        return onlineUsersCount;
    }
}
