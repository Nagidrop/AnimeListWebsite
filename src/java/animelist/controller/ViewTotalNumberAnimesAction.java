/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got triggered when user profile page is shown */
public class ViewTotalNumberAnimesAction extends ActionSupport implements ServletRequestAware {

    /* Data */
    int totalAnimes;
    int totalCompletedAnimes;
    int accountID; // user ID
    HttpServletRequest request; // HTTP request
    final String SUCCESS = "success"; // indicates sucessful action

    /* Constructor */
    public ViewTotalNumberAnimesAction() {
    }

    @Override
    public String execute() throws Exception {
        // Get user ID from session
        Map session = ActionContext.getContext().getSession();
        accountID = (int) session.get("id");
       
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();
        totalAnimes = dao.getTotalAnimesInList(accountID);
        totalCompletedAnimes = dao.getTotalCompletedAnimesInList(accountID);

        return SUCCESS;
    }

    /* Getters and Setters */
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getTotalAnimes() {
        return totalAnimes;
    }

    public void setTotalAnimes(int totalAnimes) {
        this.totalAnimes = totalAnimes;
    }

    public int getTotalCompletedAnimes() {
        return totalCompletedAnimes;
    }

    public void setTotalCompletedAnimes(int totalCompletedAnimes) {
        this.totalCompletedAnimes = totalCompletedAnimes;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
