/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got triggered when an admin create new user */
public class CreateNewUserAction extends ActionSupport implements ServletRequestAware {

    /* User credentials and info */
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String gender;
    private int roleID;
    private HttpServletRequest request; // HTTP request
    final String SUCCESS = "success"; // indicates sucessful action
    final String FAIL = "fail"; // indicates failed action

    /* Constructor */
    public CreateNewUserAction() {
    }

    @Override
    public String execute() {
        try {
            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();

            /* Store params to variables */
            password = request.getParameter("password-reg");
            fullname = request.getParameter("fullname-reg");
            email = request.getParameter("email-reg");
            gender = request.getParameter("gender-reg");
            username = request.getParameter("username-reg");
            roleID = Integer.parseInt(request.getParameter("role-reg"));

            if (dao.createNewUser(roleID, username, password, fullname, email, gender)) {
                // if action is successful
                return SUCCESS;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateNewUserAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        // if action failed
        return FAIL;

    }

    /* Getters and Setters */
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

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

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
