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
 * @author HAPPY
 */
public class DeleteAnimeAction extends ActionSupport implements ServletRequestAware {

    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    HttpServletRequest request;

    public DeleteAnimeAction() {
    }

    @Override
    public String execute() {
        try {
            AnimeListDAO dao = new AnimeListDAO();
            String id = request.getParameter("id");
            System.out.println(id);
            Date date = Date.valueOf(LocalDate.now());
            dao.deleteAnime(id, date);
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(DeleteAnimeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FAIL;

    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;//To change body of generated methods, choose Tools | Templates.
    }

}
