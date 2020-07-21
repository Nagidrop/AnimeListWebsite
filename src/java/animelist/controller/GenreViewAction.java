/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import animelist.model.GenreDTO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got called when an view genre */
public class GenreViewAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request; // HTTP request
    private ArrayList<GenreDTO> genres; // Genre List
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates sucessful action

    /* Constructor */
    public GenreViewAction() {
    }

    @Override
    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();
        genres = dao.getGenres();
        request.setAttribute("listGenre", genres);
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

    public ArrayList<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<GenreDTO> genres) {
        this.genres = genres;
    }

}
