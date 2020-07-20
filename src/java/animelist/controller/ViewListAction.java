/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeDTO;
import animelist.model.AnimeListDAO;
import animelist.model.ListDTO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* Logic code when user press button in login view */
public class ViewListAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request;
    private ArrayList<ListDTO> animeList;
    private ArrayList<AnimeDTO> animeDetailsList;
    private int accountID;
    private int listStatus;
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action

    /* Constructor */
    public ViewListAction() {
    }

    public String execute() throws Exception {
        /* Instantiate DAO object and calls login method to check from DB */
        AnimeListDAO dao = new AnimeListDAO();

        animeList = dao.getAnimeList(accountID, listStatus);
        animeDetailsList = dao.getAnimeDetailsList(animeList);

        if (animeList != null) {
            request.setAttribute("AnimeList", animeList);
            request.setAttribute("AnimeDetailsList", animeDetailsList);
            statusList = new ArrayList<>();
            statusList.add("Currently Watching");
            statusList.add("Completed");
            statusList.add("On Hold");
            statusList.add("Dropped");
            statusList.add("Plan to Watch");
            request.setAttribute("StatusList", statusList);
        } else {
            request.setAttribute("AnimeList", new ArrayList<>());
            request.setAttribute("AnimeDetailsList", new ArrayList<>());
        }

        return SUCCESS;
    }

    /* Getters and Setters */
    public ArrayList<ListDTO> getAnimeList() {
        return animeList;
    }

    public void setAnimeList(ArrayList<ListDTO> animeList) {
        this.animeList = animeList;
    }

    public ArrayList<AnimeDTO> getAnimeDetailsList() {
        return animeDetailsList;
    }

    public void setAnimeDetailsList(ArrayList<AnimeDTO> animeDetailsList) {
        this.animeDetailsList = animeDetailsList;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public ArrayList<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(ArrayList<String> statusList) {
        this.statusList = statusList;
    }

    public int getListStatus() {
        return listStatus;
    }

    public void setListStatus(int listStatus) {
        this.listStatus = listStatus;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
