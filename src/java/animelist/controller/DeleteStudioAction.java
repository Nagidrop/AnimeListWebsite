/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got called when an admin delete studio */
public class DeleteStudioAction extends ActionSupport implements ServletRequestAware {

    String id; // Studio ID
    HttpServletRequest request; // HTTP request
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action

    /* Constructor */
    public DeleteStudioAction() {
    }

    @Override
    public String execute() throws Exception {
        try {
            id = request.getParameter("id"); // get id from param

            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();
            Date date = Date.valueOf(LocalDate.now()); // get current time

            dao.deleteStudio(Integer.parseInt(id), date);

            // if action is successful
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(DeleteUserAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        // if action failed
        return FAIL;
    }

    /* Getters and Setters */
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}
