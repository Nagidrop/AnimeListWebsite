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

/* This action got called when an admin views list of users */
public class ShowListUserAction {

    ArrayList<AccountDTO> listUser = new ArrayList<>(); // list of users
    private final String SUCCESS = "success"; // indicates successful action
    private final String USER = "user"; // indicates user intervention
    private final String FAIL = "fail"; // indicates failed action
    String genString = "1";

    /* Constructor */
    public ShowListUserAction() {
    }

    public String execute() {
        try {
            /* Check if user intervents in */
            Map session = ActionContext.getContext().getSession();
            int roleID = (int) session.get("roleid");
            if (roleID != 1) {
                return USER;
            }

            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();
            listUser = dao.getAccountList(2);

            // indicates sucessful action
            return SUCCESS;
        } catch (SQLException ex) {
            Logger.getLogger(ShowListUserAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        // indicates failed action
        return FAIL;
    }

    /* Getters and Setters */
    public String getGenString() {
        return genString;
    }

    public void setGenString(String genString) {
        this.genString = genString;
    }

    public ArrayList<AccountDTO> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<AccountDTO> listUser) {
        this.listUser = listUser;
    }

}
