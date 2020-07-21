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

/* Logic code when user press button in login view */
public class ViewAnimeAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request;
    private int animeID; // anime ID passed from form input
    private AnimeDTO anime;
    private ArrayList<ListDTO> animeList;
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action

    /* Constructor */
    public ViewAnimeAction() {
    }

    public String execute() throws Exception {
        /* Instantiate DAO object and calls login method to check from DB */
        AnimeListDAO dao = new AnimeListDAO();
        anime = dao.getAnimeDetails(animeID);

        Map session = ActionContext.getContext().getSession();
        animeList = dao.getAnimeList((int) session.get("id"), 0);

        ListDTO animeInList = null;

        String url = FAIL; // by default, login is not successful

        if (anime != null) {
            url = SUCCESS;
            request.setAttribute("StudioList", anime.getStudios());
            request.setAttribute("GenreList", anime.getGenres());
            request.setAttribute("Anime", anime);

            for (ListDTO listAnime : animeList) {
                if (listAnime.getAnimeID() == animeID) {
                    animeInList = listAnime;
                }
            }
            
            request.setAttribute("AnimeInList", animeInList);
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
