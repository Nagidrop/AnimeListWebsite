/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author HAPPY
 */
public class AdminIndexAction extends ActionSupport implements ServletRequestAware {

    String totalAnimes, totalUsers, totalAdmins;
    private HttpServletRequest request;
    HashMap<String, String> totalTypeHashMap;
    private final String USER = "user"; // indicates successful action
    private final String FAIL = "fail"; // indicates successful action
    private final String SUCCESS = "success"; // indicates successful action
    private String label[], number[];

    public HashMap<String, String> getTotalTypeHashMap() {
        return totalTypeHashMap;
    }

    public void setTotalTypeHashMap(HashMap<String, String> totalTypeHashMap) {
        this.totalTypeHashMap = totalTypeHashMap;
    }

    public String[] getLabel() {
        return label;
    }

    public void setLabel(String[] label) {
        this.label = label;
    }

    public String[] getNumber() {
        return number;
    }

    public void setNumber(String[] number) {
        this.number = number;
    }

    public AdminIndexAction() {
    }

    @Override
    public String execute() {
        try {
            AnimeListDAO dao = new AnimeListDAO();
            ArrayList<String> type = new ArrayList<>();
            ArrayList<String> count = new ArrayList<>();
            Map session = ActionContext.getContext().getSession();
            if (session.isEmpty()) {
                return USER;
            }
            int roleID = (int) session.get("roleid");
            if (roleID != 1) {
                return USER;
            }
            totalTypeHashMap = dao.countAnimeofType();
            totalTypeHashMap.keySet().forEach((typesString) -> {
                type.add(typesString);
            });
            totalTypeHashMap.values().forEach((typesString) -> {
                count.add(typesString);
            });
            setLabel(type.toArray(new String[0]));
            setNumber(count.toArray(new String[0]));
            totalAdmins = dao.countAdmin();
            totalAnimes = dao.countAnimes();
            totalUsers = dao.countUsers();
            return SUCCESS;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AdminIndexAction.class.getName()).log(Level.SEVERE, null, ex);

        }
        return FAIL;
    }

    public String getTotalAnimes() {
        return totalAnimes;
    }

    public void setTotalAnimes(String totalAnimes) {
        this.totalAnimes = totalAnimes;
    }

    public String getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(String totalUsers) {
        this.totalUsers = totalUsers;
    }

    public String getTotalAdmins() {
        return totalAdmins;
    }

    public void setTotalAdmins(String totalAdmins) {
        this.totalAdmins = totalAdmins;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
