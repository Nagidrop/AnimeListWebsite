/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeDTO;
import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got called when a user press search button (on nav bar or advanced search page) */
public class SearchAction extends ActionSupport implements ServletRequestAware {

    /* Search criteria */
    private String searchValue;
    private String type;
    private String genreID;
    private String studioID;
    private String seasonID;
    private ArrayList<AnimeDTO> listAnimeDTOs = new ArrayList<>(); // result search list
    private final String SUCCESS = "success"; // if action is successful
    private HttpServletRequest request; // HTTP request

    /* Constructor */
    public SearchAction() {
    }

    @Override
    public String execute() {
        try {
            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();

            /* Set search criteria to % if it is null */
            searchValue = request.getParameter("searchvalue");
            if (searchValue == null || searchValue.equalsIgnoreCase("")) {
                searchValue = "%";
            }

            type = request.getParameter("type");
            if (type == null || type.equalsIgnoreCase("")) {
                type = "%";
            }
            genreID = request.getParameter("genre");
            if (genreID == null || genreID.equalsIgnoreCase("")) {
                genreID = "%";
            }
            studioID = request.getParameter("studio");
            if (studioID == null || studioID.equalsIgnoreCase("")) {
                studioID = "%";
            }
            seasonID = request.getParameter("season");
            if (seasonID == null || seasonID.equalsIgnoreCase("")) {
                seasonID = "%";
            }

            listAnimeDTOs = dao.getSearchAnime(searchValue, type, studioID, genreID, seasonID);

            // set result list as attribute to pass
            request.setAttribute("showlist", listAnimeDTOs);
        } catch (SQLException ex) {
            Logger.getLogger(SearchAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SUCCESS;
    }

    /* Getters and Setters */
    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }

    public String getStudioID() {
        return studioID;
    }

    public void setStudioID(String studioID) {
        this.studioID = studioID;
    }

    public String getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(String seasonID) {
        this.seasonID = seasonID;
    }

    public ArrayList<AnimeDTO> getSearchAnimeList() {
        return listAnimeDTOs;
    }

    public void setSearchAnimeList(ArrayList<AnimeDTO> searchAnimeList) {
        this.listAnimeDTOs = searchAnimeList;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

}
