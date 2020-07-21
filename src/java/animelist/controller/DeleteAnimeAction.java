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

/* This action got called when an admin delete anime */
public class DeleteAnimeAction extends ActionSupport implements ServletRequestAware {

    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    HttpServletRequest request; // HTTP request

    /* Constructor */
    public DeleteAnimeAction() {
    }

    @Override
    public String execute() {
        try {
            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();
             Map session = ActionContext.getContext().getSession();
            if (session.isEmpty()) {
                return FAIL;
            }

            int roleID = (int) session.get("roleid");
            if (roleID != 1) {
                return FAIL;
            }
            String id = request.getParameter("id");
            Date date = Date.valueOf(LocalDate.now()); // get curent time
            
            dao.deleteAnime(id, date);
            
            // if action is successful
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(DeleteAnimeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        // if action failed
        return FAIL;

    }

    /* Getters and Setters */
    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;//To change body of generated methods, choose Tools | Templates.
    }

}
