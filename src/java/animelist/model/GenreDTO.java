package animelist.model;

import java.io.Serializable;

/**
 *
 * @author Quan Duc Loc CE140037 (SE1401)
 */
public class GenreDTO implements Serializable {
    private int id;
    private String name;

    public GenreDTO() {
    }

    public GenreDTO(int id, String name) {
        this.id = id;
        this.name = name;
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
    
}
