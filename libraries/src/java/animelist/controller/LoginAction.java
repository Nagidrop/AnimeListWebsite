/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AccountDTO;
import animelist.model.AnimeDTO;
import animelist.model.AnimeListDAO;
import animelist.model.ListDTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* Logic code when user press button in login view */
public class LoginAction extends ActionSupport implements ServletRequestAware {

    private String username; // username passed from form input
    private String pass; // password passed from form input
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action
    private final String ADMIN = "admimpage"; // indicates successful action
    private HttpServletRequest request;
    private ArrayList<String> statusList;

    /* Constructor */
    public LoginAction() {
    }

    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();
        AccountDTO account = dao.login(username, pass);

        String url = FAIL; // by default, action is not successful

        /* If login successful */
        if (account != null) {
            /* Create new session for user with username */
            Map session = ActionContext.getContext().getSession();
            session.put("user", account);
            session.put("fullname", account.getFullName());
            session.put("id", account.getId());
            session.put("username", account.getUsername());
            session.put("email", account.getEmail());
            session.put("roleid", account.getRoleID());
            switch (account.getGender()) {
                case 0:
                    session.put("gender", "Male");
                    break;
                case 1:
                    session.put("gender", "Female");
                    break;
                case 2:
                    session.put("gender", "Other");
                    break;
            }
            if (account.getRoleID() == 1) {
                url = ADMIN;
            } else {
                url = SUCCESS;

            }

            statusList = new ArrayList<>();
            statusList.add("Currently Watching");
            statusList.add("Completed");
            statusList.add("On Hold");
            statusList.add("Dropped");
            statusList.add("Plan to Watch");
            session.put("StatusList", statusList);
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

    public ArrayList<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(ArrayList<String> statusList) {
        this.statusList = statusList;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
}
