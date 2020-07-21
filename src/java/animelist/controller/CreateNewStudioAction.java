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

/* This action got triggered when an admin create new stidop */
public class CreateNewStudioAction extends ActionSupport implements ServletRequestAware {

    private String fullname; // studio name
    private Date date; // creation date
    private HttpServletRequest request; // HTTP request
    final String SUCCESS = "success"; // indicates sucessful action 
    final String FAIL = "fail"; // indicates failed action

    /* Constructor */
    public CreateNewStudioAction() {
    }

    @Override
    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();
        fullname = request.getParameter("fullname-reg");
        date = Date.valueOf(LocalDate.now()); // 

        if (dao.createNewStudio(fullname, date)) {
            return SUCCESS;
        } else {

            return FAIL;
        }
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
