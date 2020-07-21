/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AccountDTO;
import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;

/* Logic code when user press button in login view */
public class LogoutAction {

    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action

    /* Constructor */
    public LogoutAction() {
    }

    public String execute() throws Exception {
        /* Instantiate DAO object and calls login method to check from DB */

        String url = SUCCESS; // by default, login is not successful

        /* If login successful */
 /* Create new session for user with username */
        SessionMap session = (SessionMap) ActionContext.getContext().getSession();
        session.invalidate();

        return url;
    }

}
