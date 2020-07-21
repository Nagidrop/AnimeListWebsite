/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeDTO;
import animelist.model.AnimeListDAO;
import animelist.model.ListDTO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got called when a user uses search function in list */
public class SearchInListAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request; // HTTP request
    private ArrayList<ListDTO> searchedAnimeList; // get user anime list
    private ArrayList<AnimeDTO> searchedAnimeDetailList; // get user anime list (with details for each anime)
    private ArrayList<String> statusList; // status list (5 status)
        private String accountUsername;
    private int accountID; // the account ID of list page
    private int listStatus; // status of list
    private final String SUCCESS = "success"; // indicates successful action

    @Override
    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();

        /* Get anime list and anime details for each list anime */
                accountUsername = dao.getAccountUsername(accountID);
        searchedAnimeList = dao.getSearchAnimeInList(request.getParameter("search-text"), accountID);
        searchedAnimeDetailList = dao.getAnimeDetailsList(searchedAnimeList);

        // if the list of the user with account ID is not null (not necessarily the logged in user)
        if (searchedAnimeList != null) {
            request.setAttribute("AnimeList", searchedAnimeList);
            request.setAttribute("AnimeDetailsList", searchedAnimeDetailList);

            /* Instantiate and add data to status list */
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
    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

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

    public ArrayList<ListDTO> getSearchedAnimeList() {
        return searchedAnimeList;
    }

    public void setSearchedAnimeList(ArrayList<ListDTO> searchedAnimeList) {
        this.searchedAnimeList = searchedAnimeList;
    }

    public ArrayList<AnimeDTO> getSearchedAnimeDetailList() {
        return searchedAnimeDetailList;
    }

    public void setSearchedAnimeDetailList(ArrayList<AnimeDTO> searchedAnimeDetailList) {
        this.searchedAnimeDetailList = searchedAnimeDetailList;
    }

    public ArrayList<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(ArrayList<String> statusList) {
        this.statusList = statusList;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getListStatus() {
        return listStatus;
    }

    public void setListStatus(int listStatus) {
        this.listStatus = listStatus;
    }
}
