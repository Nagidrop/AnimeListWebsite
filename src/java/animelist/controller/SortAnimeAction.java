/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeDTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class SortAnimeAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    private ArrayList<AnimeDTO> listAnimeDTOs;
    private final String SUCCESS = "success";

    public String sortByName() {
        Map session = ActionContext.getContext().getSession();
        listAnimeDTOs = (ArrayList<AnimeDTO>) session.get("sortlist");
        Collections.sort(listAnimeDTOs, AnimeDTO.animeNameComp);

        request.setAttribute("showlist", listAnimeDTOs);
        return SUCCESS;
    }

    public String sortBySeason() {
        Map session = ActionContext.getContext().getSession();
        listAnimeDTOs = (ArrayList<AnimeDTO>) session.get("sortlist");
        Collections.sort(listAnimeDTOs, AnimeDTO.animeSeasonComp);

        request.setAttribute("showlist", listAnimeDTOs);
        return SUCCESS;
    }

    public String sortByEpisode() {
        Map session = ActionContext.getContext().getSession();
        listAnimeDTOs = (ArrayList<AnimeDTO>) session.get("sortlist");
        Collections.sort(listAnimeDTOs, AnimeDTO.animeEpisodeComp);

        request.setAttribute("showlist", listAnimeDTOs);
        return SUCCESS;
    }

    public HttpServletRequest getRequest() {
        return request;
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
