/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeDTO;
import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action is activated when user go to index page (home page) */
public class ShowIndexAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request; // HTTP request
    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    private ArrayList<AnimeDTO> listAnimeDTOs = new ArrayList<>(); // list of animes to display

    /* Constructor */
    public ShowIndexAction() {
    }

    @Override
    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();
        listAnimeDTOs = dao.getAnimes(12); // get random list of 12 animes in DB

        return SUCCESS;
    }

    /* Getters and Setters */
    public List<AnimeDTO> getListAnimeDTOs() {
        return listAnimeDTOs;
    }

    public void setListAnimeDTOs(ArrayList<AnimeDTO> listAnimeDTOs) {
        this.listAnimeDTOs = listAnimeDTOs;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
