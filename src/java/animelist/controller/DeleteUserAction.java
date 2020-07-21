/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got called when an admin delete a user */
public class DeleteUserAction extends ActionSupport implements ServletRequestAware {

    String id; // User ID
    HttpServletRequest request; // HTTP request
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action
    private final String USER = "user"; // indicates user intervention

    /* Constructor */
    public DeleteUserAction() {
    }

    @Override
    public String execute() {
        try {
            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();

            id = request.getParameter("id");

            /* Check if user is in current session, kick him/her out */
            Map session = ActionContext.getContext().getSession();
            int roleID = (int) session.get("roleid");
            if (roleID != 1) {
                return USER;
            }

            Date date = Date.valueOf(LocalDate.now()); // get current time
            dao.deleteUser(Integer.parseInt(id), date);

            // if action is successful
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(DeleteUserAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        // if action failed
        return FAIL;

    }

    /* Getters and Setters */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

}
