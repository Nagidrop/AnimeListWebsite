/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import animelist.model.GenreDTO;
import animelist.model.StudioDTO;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author HAPPY
 */
public class CreateNewAnimeAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    private File poster;
    private String posterFileName;
    private String posterContentType;
    private final String FAIL = "fail";
    private final String SUCCESS = "success";

    public CreateNewAnimeAction() {

    }

    public File getPoster() {
        return poster;
    }

    public void setPoster(File poster) {
        this.poster = poster;
    }

    public String getPosterFileName() {
        return posterFileName;
    }

    public void setPosterFileName(String posterFileName) {
        this.posterFileName = posterFileName;
    }

    public String getPosterContentType() {
        return posterContentType;
    }

    public void setPosterContentType(String posterContentType) {
        this.posterContentType = posterContentType;
    }

    @Override
    public String execute() throws Exception {
        try {
            AnimeListDAO dao = new AnimeListDAO();
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String releaseDate = request.getParameter("releaseDate");
            String rating = request.getParameter("rating");
            String episodes = request.getParameter("episodes");
            String status = request.getParameter("status");
            String duration = request.getParameter("duration");
            String description = request.getParameter("description");
            String trailer = request.getParameter("trailer");
            String season = request.getParameter("season");
            String path = request.getSession().getServletContext().getRealPath("/");
            path = path.substring(0, path.length() - 10).concat("web\\images\\poster");
            // random file name
            String fileExt = "";
            if (poster != null) {
                fileExt = posterFileName.substring(posterFileName.length() - 4, posterFileName.length());
                String ext = "";
                if (posterFileName != null || posterFileName.equalsIgnoreCase("")) {
                    ext = posterFileName.substring(posterFileName.lastIndexOf("."), posterFileName.length());
                }
                Random r = new Random();
                posterFileName = r.ints(0, 4000).findFirst().getAsInt() + Calendar.getInstance().getTimeInMillis() + ext;
                File newFile = new File(path, posterFileName);
                FileUtils.copyFile(poster, newFile);
            }
            System.out.println(posterFileName);
            System.out.println(path);
            int id = dao.createNewAnime(1, season, type, name, releaseDate, rating, episodes, status, duration, description, posterFileName, trailer, null, null);
            String[] studio = request.getParameterValues("studio");
            for (String i : studio) {
                dao.createAnimeStudio(String.valueOf(id), i, null);
            }
            String[] genre = request.getParameterValues("genre");
            for (String i : genre) {
                dao.createAnimeGenre(String.valueOf(id), i, null);
            }
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(CreateNewAnimeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FAIL;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr; //To change body of generated methods, choose Tools | Templates.
    }

}
