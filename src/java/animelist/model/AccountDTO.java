/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.model;

import java.io.Serializable;
import java.sql.Date;

/* DTO for Account table */
public class AccountDTO implements Serializable {

    /* Account properties */
    private int id;
    private int roleID;
    private String username;
    private String fullName;
    private String avatar;
    private String email;
    private int gender;
    private Date created_at;
    private Date deleted_at;

    /* Constructors */
    public AccountDTO(int id, int roleID, String username, String fullName, String avatar, String email, int gender, Date created_at, Date deleted_at) {
        this.id = id;
        this.roleID = roleID;
        this.username = username;
        this.fullName = fullName;
        this.avatar = avatar;
        this.email = email;
        this.gender = gender;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
    }

    /* Getters and Setters */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
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
