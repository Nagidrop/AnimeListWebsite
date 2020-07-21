/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Tran Minh Thang CE140085
 */
public class ViewTotalNumberAnimesAction extends ActionSupport implements ServletRequestAware{
    int totalAnimes;
    int totalCompletedAnimes;
    int accountID;
    HttpServletRequest request;
    final String SUCCESS = "success";

    public ViewTotalNumberAnimesAction() {
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    @Override
    public String execute() throws Exception {
        AnimeListDAO dao = new AnimeListDAO();
        totalAnimes = dao.getTotalAnimesInList(accountID);
        totalCompletedAnimes = dao.getTotalCompletedAnimesInList(accountID);
        return SUCCESS;
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
