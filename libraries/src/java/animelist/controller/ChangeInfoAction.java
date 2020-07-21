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
public class ChangeInfoAction extends ActionSupport implements ServletRequestAware {

    private String username; // username passed from form input
    private String pass; // password passed from form input
    private int gender;
    private String fullname;
    private String avatar;
    private String email;
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action
    HttpServletRequest request;

    public ChangeInfoAction() {
    }

    @Override
    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();
        Map session = ActionContext.getContext().getSession();
        username = (String) session.get("username");
        gender = Integer.parseInt(request.getParameter("gender"));
        System.out.println("Hello:" + gender);
        email = request.getParameter("email");
        fullname = request.getParameter("name");
        avatar = request.getParameter("upload");
        session.replace("fullname", fullname);
        session.replace("email", email);
        switch (gender) {
            case 0:
                session.replace("gender", "Male");
                break;
            case 1:
                session.replace("gender", "Female");
                break;
            case 2:
                session.replace("gender", "Other");
                break;
        }
        dao.changeInfo(username, fullname, avatar, email, gender);
        return SUCCESS;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
}
