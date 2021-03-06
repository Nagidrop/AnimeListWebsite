/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.model;

import java.io.Serializable;

/* DTO for Role table */
public class RoleDTO implements Serializable {

    /* Account Role properties */
    private int id;
    private String slug;
    private String name;

    /* Constructors with and without arguments */
    public RoleDTO() {
    }

    public RoleDTO(int id, String slug, String name) {
        this.id = id;
        this.slug = slug;
        this.name = name;
    }

    /* Getters and Setters */
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
