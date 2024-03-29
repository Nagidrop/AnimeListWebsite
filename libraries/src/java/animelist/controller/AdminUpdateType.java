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
public class AdminUpdateType extends ActionSupport implements ServletRequestAware {

    private int id;
    private String name;
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action
    HttpServletRequest request;

    public AdminUpdateType() {
    }

    @Override
    public String execute() throws Exception {
        try {

            String idString = (String) request.getParameter("id");
            String nameString = (String) request.getParameter("name");
            AnimeListDAO dao = new AnimeListDAO();
            dao.changeType(Integer.parseInt(idString), nameString);
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(AdminUpdateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FAIL;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = request;
    }

}
