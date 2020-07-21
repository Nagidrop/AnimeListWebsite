/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got called when a user changes password */
public class ChangePasswordAction extends ActionSupport implements ServletRequestAware {

    private String username; // username passed from form input
    private String pass; // password passed from form input
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action
    HttpServletRequest request; // HTTP request

    /* Constructor */
    public ChangePasswordAction() {
    }

    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();
        
        /* Get user name from session */
        Map session = ActionContext.getContext().getSession();
        username = (String) session.get("username");
        
        pass = request.getParameter("password");
        
        if (dao.changePassword(username, pass)) {
            // if action is successful
            return SUCCESS;
        } else {
            // if action failed
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
