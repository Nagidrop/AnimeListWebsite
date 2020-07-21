/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AccountDTO;
import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowListUserAction {

    ArrayList<AccountDTO> listUser = new ArrayList<>();
    private final String SUCCESS = "success"; // indicates successful action
    private final String USER = "user"; // indicates successful action
    private final String FAIL = "fail"; // indicates failed action

    String genString = "1";

    public String getGenString() {
        return genString;
    }

    public void setGenString(String genString) {
        this.genString = genString;
    }

    public ShowListUserAction() {
    }

    public String execute() {
        try {
            Map session = ActionContext.getContext().getSession();
            int roleID = (int) session.get("roleid");
            if (roleID != 1) {
                return USER;
            }
            AnimeListDAO dao = new AnimeListDAO();
            listUser = dao.getAccountList(2);
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(ShowListUserAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FAIL;
    }

    public ArrayList<AccountDTO> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<AccountDTO> listUser) {
        this.listUser = listUser;
    }

}
