package animelist.model;

import java.io.Serializable;

/**
 *
 * @author Quan Duc Loc CE140037 (SE1401)
 */
public class RoleDTO implements Serializable {
    private int id;
    private String slug;
    private String name;

    public RoleDTO() {
    }

    public RoleDTO(int id, String slug, String name) {
        this.id = id;
        this.slug = slug;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
