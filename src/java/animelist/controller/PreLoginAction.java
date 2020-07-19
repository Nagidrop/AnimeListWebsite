/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */

package animelist.controller;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

/* Logic code when user press login button on header */
public class PreLoginAction {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    /* Constructor */
    public PreLoginAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession(); // get session

        String url = SUCCESS;
        
        /* If session already exists (user already logged in), do not allow to login anymore */
        if (session != null) {
            url = FAIL;
        }

        return url;
    }
}
