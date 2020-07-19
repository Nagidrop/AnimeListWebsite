/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AccountDTO;
import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

/* Logic code when user press button in login view */
public class LoginAction {

    private String username; // username passed from form input
    private String pass; // password passed from form input
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action

    /* Constructor */
    public LoginAction() {
    }

    public String execute() throws Exception {
        /* Instantiate DAO object and calls login method to check from DB */
        AnimeListDAO dao = new AnimeListDAO();
        AccountDTO account = dao.login(username, pass);

        String url = FAIL; // by default, login is not successful

        /* If login successful */
        if (account != null) {
            /* Create new session for user with username */
            Map session = ActionContext.getContext().getSession();
            session.put("user", account);
            session.put("fullname", account.getFullName());
            session.put("id", account.getId());
            url = SUCCESS;
        }

        return url;
    }

    /* Getters and Setters */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
