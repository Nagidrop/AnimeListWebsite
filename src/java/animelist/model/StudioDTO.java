package animelist.model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Quan Duc Loc CE140037 (SE1401)
 */
public class StudioDTO  implements Serializable {
    private int id;
    private String name;
    private Date created_at;
    private Date deleted_at;

    public StudioDTO() {
    }

    public StudioDTO(int id, String name, Date created_at, Date deleted_at) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
    }

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
