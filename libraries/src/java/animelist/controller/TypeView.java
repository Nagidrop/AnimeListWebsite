/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author PC
 */
public class TypeView extends ActionSupport implements ServletRequestAware  {
    HttpServletRequest request;
    private ArrayList<String> types ;
    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    public TypeView() {
    }
    
    @Override
    public String execute() throws Exception {
        AnimeListDAO dao = new AnimeListDAO();
        types = dao.getTypes();
        request.setAttribute("listType", types);
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }
    
}
