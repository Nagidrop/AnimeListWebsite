/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author PC
 */
public class createNewSeasonAction extends ActionSupport implements ServletRequestAware  {
    private String fullname;
    private HttpServletRequest request;
    final String SUCCESS = "success";
    final String FAIL = "fail";
    public createNewSeasonAction() {
    }
    
    @Override
    public String execute() throws Exception {
try {
            AnimeListDAO dao = new AnimeListDAO();
            fullname = request.getParameter("fullname-reg");
            if(dao.createNewSeason(fullname)){
                return SUCCESS;
            }
            } catch (SQLException ex) {
            Logger.getLogger(createNewUserAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FAIL;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
}
