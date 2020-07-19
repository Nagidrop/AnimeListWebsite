/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */

package animelist.controller;

import animelist.model.AccountDTO;
import animelist.model.AnimeDTO;
import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class ShowIndexAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;

    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    private ArrayList<AnimeDTO> listAnimeDTOs = new ArrayList<>();

    public ShowIndexAction() {
    }

    @Override
    public String execute() throws Exception {
        AnimeListDAO dao = new AnimeListDAO();
        listAnimeDTOs = dao.getAnimes(12);
   
        return SUCCESS;
    }

    public   List<AnimeDTO> getListAnimeDTOs() {
        return listAnimeDTOs;
    }

    public void setListAnimeDTOs(ArrayList<AnimeDTO> listAnimeDTOs) {
        this.listAnimeDTOs = listAnimeDTOs;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

}
