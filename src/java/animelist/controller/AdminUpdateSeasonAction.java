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

/* This action got called when an admin update season */
public class AdminUpdateSeasonAction extends ActionSupport implements ServletRequestAware {

    private int id; // Season ID
    private String name; // Season Name
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action
    HttpServletRequest request; // HTTP request

    /* Constructor */
    public AdminUpdateSeasonAction() {
    }

    @Override
    public String execute() throws Exception {
        try {
            /* Get Season ID and Name from passed params */
            String idString = (String) request.getParameter("id");
            String nameString = (String) request.getParameter("name");

            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();

            dao.changeSeason(Integer.parseInt(idString), nameString);

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

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

}
