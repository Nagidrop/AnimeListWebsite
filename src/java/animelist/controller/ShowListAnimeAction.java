/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeDTO;
import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class ShowListAnimeAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;

    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    private ArrayList<AnimeDTO> listAnimeDTOs = new ArrayList<>();

    public ShowListAnimeAction() {
    }

    @Override
    public String execute() {
        try {
            AnimeListDAO dao = new AnimeListDAO();
            listAnimeDTOs = dao.getAllAnimes();
            return SUCCESS;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ShowListAnimeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FAIL;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<AnimeDTO> getListAnimeDTOs() {
        return listAnimeDTOs;
    }

    public void setListAnimeDTOs(ArrayList<AnimeDTO> listAnimeDTOs) {
        this.listAnimeDTOs = listAnimeDTOs;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
