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
 * @author HAPPY
 */
public class createNewUserAction extends ActionSupport implements ServletRequestAware {

    private String username;
    private String password;
    private String fullname;
    private String email;
    private String gender;
    private int roleID;
    private HttpServletRequest request;
    final String SUCCESS = "success";
    final String FAIL = "fail";

    public createNewUserAction() {
    }

    @Override
    public String execute() {

        try {
            AnimeListDAO dao = new AnimeListDAO();

            password = request.getParameter("password-reg");
            fullname = request.getParameter("fullname-reg");
            email = request.getParameter("email-reg");
            gender = request.getParameter("gender-reg");
            username = request.getParameter("username-reg");
            roleID = Integer.parseInt(request.getParameter("role-reg")) ;

            if (dao.createNewUser(roleID, username, password, fullname, email, gender)) {

                return SUCCESS;
            }
        } catch (SQLException ex) {
            Logger.getLogger(createNewUserAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FAIL;

    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
