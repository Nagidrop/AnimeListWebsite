/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got called when an admin changes user info */
public class AdminUpdateUser extends ActionSupport implements ServletRequestAware {

    /* User info properties */
    private String username;
    private String pass;
    private int gender;
    private String fullname;
    private String avatar;
    private String email;
    private String password;
    private final String USER = "user"; // indicates user intervention
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action
    HttpServletRequest request;

    /* Constructor */
    public AdminUpdateUser() {

    }

    @Override
    public String execute() {
        try {
            /* Get params and store to variables */
            String usernameString = (String) request.getParameter("username");
            String fullnameString = (String) request.getParameter("fullname");
            String genderString = (String) request.getParameter("gender");
            String emailString = (String) request.getParameter("email");
            String passwordString = (String) request.getParameter("password");

            /* Check if user enters in this page */
            Map session = ActionContext.getContext().getSession();
            int roleID = (int) session.get("roleid");
            if (roleID != 1) {
                return USER;
            }

            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();

            dao.changeInfo(usernameString, fullnameString, "", emailString, Integer.parseInt(genderString));

            // if user wants to change password
            if (!passwordString.isEmpty()) {
                dao.changePassword(usernameString, passwordString);
            }

            // if action is successful
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(AdminUpdateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        // if action failed
        return FAIL;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public static com.opensymphony.xwork2.util.logging.Logger getLOG() {
        return LOG;
    }

    public static void setLOG(com.opensymphony.xwork2.util.logging.Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr; //To change body of generated methods, choose Tools | Templates.
    }

}
