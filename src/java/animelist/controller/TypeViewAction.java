/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/* This action got called when an admin views type list */
public class TypeViewAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request; // HTTP request
    private ArrayList<String> types; // list of types
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates sucessful action

    /* Constructor */
    public TypeViewAction() {
    }

    @Override
    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();
        Map session = ActionContext.getContext().getSession();
        if (session.isEmpty()) {
            return FAIL;
        }

        int roleID = (int) session.get("roleid");
        if (roleID != 1) {
            return FAIL;
        }
        types = dao.getTypes();
        request.setAttribute("listType", types);
        return SUCCESS;
    }

    /* Getters and Setters */
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

}
