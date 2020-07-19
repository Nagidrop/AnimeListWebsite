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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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
    public String execute() throws Exception {
        AnimeListDAO dao = new AnimeListDAO();
        ArrayList<String> type = new ArrayList<>();
        ArrayList<String> count = new ArrayList<>();

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
