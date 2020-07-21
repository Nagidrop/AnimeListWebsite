/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author PC
 */
public class ChangePasswordAction extends ActionSupport implements ServletRequestAware {

    private String username; // username passed from form input
    private String pass; // password passed from form input
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action
    HttpServletRequest request;

    public ChangePasswordAction() {
    }

    public String execute() throws Exception {
        /* Instantiate DAO object and calls login method to check from DB */
        AnimeListDAO dao = new AnimeListDAO();
        Map session = ActionContext.getContext().getSession();
         username = (String) session.get("username");
        pass = request.getParameter("password");
        System.out.println("Hello:"+username);
        if (dao.changePassword(username, pass)) {
            return SUCCESS;
        } else {
            return FAIL;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
