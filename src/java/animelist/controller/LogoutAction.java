/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.dispatcher.SessionMap;

/* Logic code when user press logout */
public class LogoutAction {

    private final String SUCCESS = "success"; // indicates successful action

    /* Constructor */
    public LogoutAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;

        /* Invalidate current session */
        SessionMap session = (SessionMap) ActionContext.getContext().getSession();
        session.invalidate();

        return url;
    }

}
