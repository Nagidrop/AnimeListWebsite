/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import animelist.model.SeasonDTO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got called when an admin view seasons */
public class SeasonViewAction extends ActionSupport implements ServletRequestAware {
    HttpServletRequest request; // HTTP request
    private List<SeasonDTO> seasons; // list of seasons
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates sucessful action

    /* Constructor */
    public SeasonViewAction() {
    }

    @Override
    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();
        
        // Get seasons from DB
        seasons = dao.getSeasons();
        
        request.setAttribute("listSeason", seasons);
        return SUCCESS;
    }

    /* Getters and Setters */
    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public List<SeasonDTO> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonDTO> seasons) {
        this.seasons = seasons;
    }

}
