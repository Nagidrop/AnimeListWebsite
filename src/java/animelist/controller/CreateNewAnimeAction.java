/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.GenreDTO;
import animelist.model.StudioDTO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author HAPPY
 */
public class CreateNewAnimeAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    private final String FAIL = "fail";
    private final String SUCCESS = "success";

    public CreateNewAnimeAction() {

    }

    @Override
    public String execute() {

        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr; //To change body of generated methods, choose Tools | Templates.
    }

}
