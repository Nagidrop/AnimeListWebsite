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
public class RegisterAction extends ActionSupport implements ServletRequestAware{
    private String username;
    private String password;
    private String fullname;
    private String email;
    private HttpServletRequest request;
    final String SUCCESS = "success";
    final String FAIL = "fail";

    public RegisterAction() {
    }

    @Override
    public String execute() throws Exception {
        
        AnimeListDAO dao = new AnimeListDAO();
        
        username = request.getParameter("registerUsername");
        password = request.getParameter("registerPass");
        fullname = request.getParameter("registerFullname");
        email = request.getParameter("registerEmail");
        
        dao.register(username, password, fullname, email);
        
        return SUCCESS;
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

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
    
    
    
}
