/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AccountDTO;
import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PC
 */
public class UserInfoAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String avatar;
    private String gender;
    private HttpServletRequest request;

    public UserInfoAction() {
    }

    public String execute() throws Exception {
        // Map session = ActionContext.getContext().getSession();
        AnimeListDAO dao = new AnimeListDAO();
        AccountDTO account = dao.login(username, password);
        return SUCCESS;
    }
}
