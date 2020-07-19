/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeDTO;
import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* Logic code when user press button in login view */
public class ViewAnimeAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request;
    private int animeID; // anime ID passed from form input
    private AnimeDTO anime;
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action

    /* Constructor */
    public ViewAnimeAction() {
    }

    public String execute() throws Exception {
        /* Instantiate DAO object and calls login method to check from DB */
        AnimeListDAO dao = new AnimeListDAO();
        anime = dao.getAnimeDetails(animeID);

        String url = FAIL; // by default, login is not successful

        if (anime != null) {
            url = SUCCESS;
            request.setAttribute("StudioList", anime.getStudios());
            request.setAttribute("GenreList", anime.getGenres());
            anime.setTrailer(anime.getTrailer().replace("&autoplay=1", ""));
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

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
