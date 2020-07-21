/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author PC
 */
public class createNewStudioAction extends ActionSupport implements ServletRequestAware {
     private String fullname;
     private Date date;
    private HttpServletRequest request;
    final String SUCCESS = "success";
    final String FAIL = "fail";
    public createNewStudioAction() {
    }
    
     @Override
    public String execute() throws Exception {
        AnimeListDAO dao = new AnimeListDAO();
        fullname = request.getParameter("fullname-reg");
        date = Date.valueOf(LocalDate.now());
        System.out.println("Hello"+fullname);
        if(dao.createNewStudio(fullname,date)){
            return SUCCESS;
        }else{

        return FAIL;}
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
