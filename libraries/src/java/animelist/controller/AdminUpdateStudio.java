/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author PC
 */
public class AdminUpdateStudio extends ActionSupport implements ServletRequestAware {

    private int studioid;
    private String studioname;
    private final String Studio = "studio"; // indicates successful action
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action
    HttpServletRequest request;

    public AdminUpdateStudio() {
    }

    @Override
    public String execute() throws Exception {
        try {

            String studioidString = (String) request.getParameter("studioid");
            String studionameString = (String) request.getParameter("studioname");
            Map session = ActionContext.getContext().getSession();
            int StudioID = (int) session.get("studioid");
            if (StudioID != 1) {
                return Studio;
            }
            AnimeListDAO dao = new AnimeListDAO();
            dao.changeStudio(Integer.parseInt(studioidString), studionameString);
                        if (!studionameString.isEmpty()) {
                dao.changeStudio(Integer.parseInt(studioidString), studionameString);
            }
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(AdminUpdateStudio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FAIL;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = request;
    }
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public int getStudioid() {
        return studioid;
    }

    public void setStudioid(int studioid) {
        this.studioid = studioid;
    }

    public String getStudioname() {
        return studioname;
    }

    public void setStudioname(String studioname) {
        this.studioname = studioname;
    }

}
