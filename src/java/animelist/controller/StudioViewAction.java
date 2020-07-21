/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import animelist.model.StudioDTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got called when an admin views studio list */
public class StudioViewAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request; // HTTP request
    private ArrayList<StudioDTO> studios; // list of studios
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates sucessful action

    /* Constructor */
    public StudioViewAction() {
    }

    @Override
    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();
        studios = dao.getStudios(); // get studios from DB
        Map session = ActionContext.getContext().getSession();
        if (session.isEmpty()) {
            return FAIL;
        }

        int roleID = (int) session.get("roleid");
        if (roleID != 1) {
            return FAIL;
        }
        request.setAttribute("listStudio", studios);
        return SUCCESS;
    }

    /* Getters and Setters */
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<StudioDTO> getStudios() {
        return studios;
    }

    public void setStudios(ArrayList<StudioDTO> studios) {
        this.studios = studios;
    }

}
