/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.model;

import java.io.Serializable;
import java.sql.Date;

/* DTO for Studio table */
public class StudioDTO implements Serializable {

    /* An Anime Studio properties */
    private int id;
    private String name;
    private Date created_at;
    private Date deleted_at;

    /* Constructors with and without arguments */
    public StudioDTO() {
    }

    public StudioDTO(int id, String name, Date created_at, Date deleted_at) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
