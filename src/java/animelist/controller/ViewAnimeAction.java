/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeDTO;
import animelist.model.AnimeListDAO;
import animelist.model.ListDTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got triggered when web user views an anime */
public class ViewAnimeAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request; // HTTP request
    private int animeID; // anime ID passed from form input
    private AnimeDTO anime; // anime details to view
    private ArrayList<ListDTO> animeList; // list of anime of current user (if any)
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action

    /* Constructor */
    public ViewAnimeAction() {
    }

    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();
        anime = dao.getAnimeDetails(animeID);

        /* Check if user logged in to get list */
        Map session = ActionContext.getContext().getSession();
        if (session.get("user") != null) {
            animeList = dao.getAnimeList((int) session.get("id"), 0);
        }

        ListDTO animeInList = null; // not null if there is an anime in list

        String url = FAIL; // by default, action is not successful

        // if there is anime details from DB
        if (anime != null) {
            url = SUCCESS; // indicates action is successful
            request.setAttribute("StudioList", anime.getStudios());
            request.setAttribute("GenreList", anime.getGenres());
            request.setAttribute("Anime", anime);

            // if there is an anime list for user who views
            if (animeList != null) {
                for (ListDTO listAnime : animeList) {
                    if (listAnime.getAnimeID() == animeID) {
                        animeInList = listAnime; // mark anime in list
                    }
                }

                request.setAttribute("AnimeInList", animeInList);
            }
        }

        return url;
    }

    /* Getters and Setters */
    public int getAnimeID() {
        return animeID;
    }

    public void setAnimeID(int animeID) {
        this.animeID = animeID;
    }

    public AnimeDTO getAnime() {
        return anime;
    }

    public void setAnime(AnimeDTO anime) {
        this.anime = anime;
    }

    public ArrayList<ListDTO> getAnimeList() {
        return animeList;
    }

    public void setAnimeList(ArrayList<ListDTO> animeList) {
        this.animeList = animeList;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
