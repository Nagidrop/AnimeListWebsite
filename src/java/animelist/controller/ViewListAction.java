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
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got triggered when a web user views someone's list */
public class ViewListAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request; // HTTP request
    private ArrayList<ListDTO> animeList; // anime list of users (with progress)
    private ArrayList<AnimeDTO> animeDetailsList;  // anime details for each anime in list
    private ArrayList<String> statusList; // status list
    private int accountID; // accountID of user whose list is shown
    private int listStatus; // status of list in view
    private String accountUsername; // username of user whose list is shown
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action

    /* Constructor */
    public ViewListAction() {
    }

    @Override
    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();

        /* Get user whose list is viewed info and their own anime list */
        accountUsername = dao.getAccountUsername(accountID);
        animeList = dao.getAnimeList(accountID, listStatus);
        animeDetailsList = dao.getAnimeDetailsList(animeList);

        /* Get status list if there is user logged in */
        Map session = ActionContext.getContext().getSession();
        if (session.get("user") != null) {
            statusList = (ArrayList<String>) session.get("StatusList");
        }

        /* If user whose list is viewed has their own anime list */
        if (animeList != null) {
            request.setAttribute("AnimeList", animeList);
            request.setAttribute("AnimeDetailsList", animeDetailsList);
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
    
    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

}
