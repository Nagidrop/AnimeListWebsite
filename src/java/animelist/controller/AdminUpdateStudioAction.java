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

/* This action got called when an admin update studio */
public class AdminUpdateStudioAction extends ActionSupport implements ServletRequestAware {

    private int id;  // Studio ID
    private String name;  // Studio Name
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action
    private final String STUDIO = "studio";
    HttpServletRequest request; // HTTP request

    /* Constructor */
    public AdminUpdateStudioAction() {
    }

    @Override
    public String execute() throws Exception {
        try {
            /* Get Studio ID and Name from passed params */
            String idString = (String) request.getParameter("id");
            String nameString = (String) request.getParameter("name");
            Map session = ActionContext.getContext().getSession();
            if (session.isEmpty()) {
                return FAIL;
            }

            int roleID = (int) session.get("roleid");
            if (roleID != 1) {
                return FAIL;
            }
            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();

            dao.changeStudio(Integer.parseInt(idString), nameString);

            // if action is successful
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(AdminUpdateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        // if action failed
        return FAIL;
    }

    /* Getters and Setters */
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr; //To change body of generated methods, choose Tools | Templates.
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
