package animelist.model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Quan Duc Loc CE140037 (SE1401)
 */
public class AccountDTO implements Serializable {
    private int id;
    private int roleId;
    private String username;
    private String password;
    private Date created_at;
    private Date deleted_at;

    public AccountDTO() {
    }

    public AccountDTO(int id, int roleId, String username, String password, Date created_at, Date deleted_at) {
        this.id = id;
        this.roleId = roleId;
        this.username = username;
        this.password = password;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
    
    
}
