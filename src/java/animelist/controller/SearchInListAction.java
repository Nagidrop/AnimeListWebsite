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

public class SearchInListAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request;
    private ArrayList<ListDTO> searchedAnimeList;
    private ArrayList<AnimeDTO> searchedAnimeDetailList;
    private ArrayList<String> statusList;
    private int accountID;
    private int listStatus;
    private final String SUCCESS = "success"; // indicates successful action

    @Override
    public String execute() throws Exception {
        AnimeListDAO dao = new AnimeListDAO();
        Map session = ActionContext.getContext().getSession();
        accountID = (int) session.get("id");
        searchedAnimeList = dao.getSearchAnimeInList(request.getParameter("search-text"), accountID);
        searchedAnimeDetailList = dao.getAnimeDetailsList(searchedAnimeList);

        if (searchedAnimeList != null) {
            request.setAttribute("AnimeList", searchedAnimeList);
            request.setAttribute("AnimeDetailsList", searchedAnimeDetailList);
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
