/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author HAPPY
 */
public class DeleteUserAction extends ActionSupport implements ServletRequestAware {

    String id;
    HttpServletRequest request;
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action
    private final String USER = "user"; // indicates successful action

    public DeleteUserAction() {
    }

    @Override
    public String execute() {
        try {
            AnimeListDAO dao = new AnimeListDAO();
            id = request.getParameter("id");
            Map session = ActionContext.getContext().getSession();
            int roleID = (int) session.get("roleid");
            if (roleID != 1) {
                return USER;
            }
            Date date = Date.valueOf(LocalDate.now());
            dao.deleteUser(Integer.parseInt(id), date);
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(DeleteUserAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FAIL;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
