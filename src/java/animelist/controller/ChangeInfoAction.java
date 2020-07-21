/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author PC
 */
public class ChangeInfoAction extends ActionSupport implements ServletRequestAware {

    private String username; // username passed from form input
    private String pass; // password passed from form input
    private int gender;
    private String fullname;
    private File avatar;
    private String avatarFileName;
    private String avatarContentType;
    private String email;
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action
    HttpServletRequest request;

    public ChangeInfoAction() {
    }

    @Override
    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        String fileExt = null;
        AnimeListDAO dao = new AnimeListDAO();
        Map session = ActionContext.getContext().getSession();
        username = (String) session.get("username");
        String path = request.getSession().getServletContext().getRealPath("/");
        path = path.substring(0, path.length() - 10).concat("web\\images\\users");
        // random file name
        if (avatar != null) {
            fileExt = avatarFileName.substring(avatarFileName.length() - 4, avatarFileName.length());
            String ext = "";
            if (avatarFileName != null || avatarFileName.equalsIgnoreCase("")) {
                ext = avatarFileName.substring(avatarFileName.lastIndexOf("."), avatarFileName.length());
            }
            Random r = new Random();
            avatarFileName = r.ints(10, 971).findFirst().getAsInt() + Calendar.getInstance().getTimeInMillis() + ext;
            File newFile = new File(path, avatarFileName);
            FileUtils.copyFile(avatar, newFile);

        } else {
            avatarFileName = (String) session.get("userAvatar");
        }
        
        session.replace("userAvatar", avatarFileName);
        session.replace("fullname", fullname);
        session.replace("email", email);

        switch (gender) {
            case 0:
                session.replace("gender", "Male");
                break;
            case 1:
                session.replace("gender", "Female");
                break;
            case 2:
                session.replace("gender", "Other");
                break;
        }

        dao.changeInfo(username, fullname, avatarFileName, email, gender);
        return SUCCESS;
    }

    public String getAvatarFileName() {
        return avatarFileName;
    }

    public void setAvatarFileName(String avatarFileName) {
        this.avatarFileName = avatarFileName;
    }

    public String getAvatarContentType() {
        return avatarContentType;
    }

    public void setAvatarContentType(String avatarContentType) {
        this.avatarContentType = avatarContentType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
}
