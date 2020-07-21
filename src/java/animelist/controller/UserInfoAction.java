/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AccountDTO;
import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class UserInfoAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String avatar;
    private String gender;
    private HttpServletRequest request;

    public UserInfoAction() {
    }

    public String execute() throws Exception {
        // Map session = ActionContext.getContext().getSession();
        AnimeListDAO dao = new AnimeListDAO();
        AccountDTO account = dao.login(username, password);
        return SUCCESS;
    }
}
