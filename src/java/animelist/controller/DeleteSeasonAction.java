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
public class DeleteSeasonAction extends ActionSupport implements ServletRequestAware {
    String id;
    HttpServletRequest request;
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action
    private final String Studio = "Studio"; // indicates successful action
    public DeleteSeasonAction() {
    }
    
    @Override
    public String execute() throws Exception {
        try {
            id = request.getParameter("id"); //get id
            System.out.println("Hello" + id); //print what is here
            AnimeListDAO dao = new AnimeListDAO();//new animelistdao
            Date date = Date.valueOf(LocalDate.now());//tao bien date kieu Date voi tgian set now
            dao.deleteSeason(Integer.parseInt(id), date);//lay id va date va xoa 
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(DeleteSeasonAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FAIL;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
}
