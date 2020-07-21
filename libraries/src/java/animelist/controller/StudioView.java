/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import animelist.model.StudioDTO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author PC
 */
public class StudioView extends ActionSupport implements ServletRequestAware {
    HttpServletRequest request;
    private ArrayList<StudioDTO> studios;
    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    public StudioView() {
    }
    
    public String execute() throws Exception {
        AnimeListDAO dao = new AnimeListDAO();
        studios = dao.getStudios();
        request.setAttribute("listStudio", studios);
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

    public ArrayList<StudioDTO> getStudios() {
        return studios;
    }

    public void setStudios(ArrayList<StudioDTO> studios) {
        this.studios = studios;
    }
    
}
