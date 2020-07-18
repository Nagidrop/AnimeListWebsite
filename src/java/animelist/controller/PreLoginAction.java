package animelist.controller;

import animelist.model.AccountDTO;
import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

/**
 *
 * @author Quan Duc Loc CE140037 (SE1401)
 */
public class PreLoginAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public PreLoginAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();

        String url = SUCCESS;
        if (session != null) {
            url = FAIL;
        }

        return url;
    }
}
