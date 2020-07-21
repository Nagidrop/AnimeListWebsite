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

/* This action got triggered when an admin create new season */
public class CreateNewSeasonAction extends ActionSupport implements ServletRequestAware {

    private String fullname; // season name
    private HttpServletRequest request; // HTTP request
    final String SUCCESS = "success"; // indicates sucessful action
    final String FAIL = "fail"; // indicates failed action

    /* Constructor */
    public CreateNewSeasonAction() {
    }

    @Override
    public String execute() throws Exception {
        try {
            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();
            fullname = request.getParameter("fullname-reg");
            if (dao.createNewSeason(fullname)) {
                return SUCCESS;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateNewUserAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FAIL;
    }

    /* Getters and Setters */
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

}
