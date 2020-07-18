package animelist.controller;

import animelist.model.AccountDTO;
import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

/**
 *
 * @author Quan Duc Loc CE140037 (SE1401)
 */
public class LoginAction {

    private String username;
    private String pass;
    private final String FAIL = "fail";
    private final String SUCCESS = "success";

    public LoginAction() {
    }

    public String execute() throws Exception {
        AnimeListDAO dao = new AnimeListDAO();
        AccountDTO account = dao.login(username, pass);
        String url = FAIL;
        if (account != null) {
            Map session = ActionContext.getContext().getSession();
            session.put("USERNAME", getUsername());

            url = SUCCESS;
        }

        return url;
    }

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
