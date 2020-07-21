/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got called when a user presses register/sign up button */
public class RegisterAction extends ActionSupport implements ServletRequestAware {

    /* User credentials and basic info */
    private String username;
    private String password;
    private String fullname;
    private String email;
    private HttpServletRequest request; // HTTP request
    final String SUCCESS = "success"; // indicates sucessful action
    final String FAIL = "fail"; // indicates failed action

    /* Constructor */
    public RegisterAction() {
    }

    @Override
    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();

        /* Store data from params to variables */
        username = request.getParameter("registerUsername");
        password = request.getParameter("registerPassword");
        fullname = request.getParameter("registerFullname");
        email = request.getParameter("registerEmail");

        if (dao.register(username, password, fullname, email)) {
            // indicates sucessful action
            return SUCCESS;
        } else {
            // indicates failed action
            return FAIL;
        }
    }

    /* Getters and Setters */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

}
