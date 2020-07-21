/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.model;

import java.io.Serializable;
import java.sql.Date;

/* DTO for ListHistory table */
public class ListHistoryDTO implements Serializable {

    /* Anime List History properties */
    private long listHistoryID;
    private int listAnimeID;
    private int listAccountID;
    private Date created_at;

    /* Constructors with and without arguments */
    public ListHistoryDTO() {
    }

    public ListHistoryDTO(long listHistoryID, int listAnimeID, int listAccountID, Date created_at) {
        this.listHistoryID = listHistoryID;
        this.listAnimeID = listAnimeID;
        this.listAccountID = listAccountID;
        this.created_at = created_at;
    }

    /* Getters and Setters */
    public long getListHistoryID() {
        return listHistoryID;
    }

    public void setListHistoryID(long listHistoryID) {
        this.listHistoryID = listHistoryID;
    }

    public int getListAnimeID() {
        return listAnimeID;
    }

    public void setListAnimeID(int listAnimeID) {
        this.listAnimeID = listAnimeID;
    }

    public int getListAccountID() {
        return listAccountID;
    }

    public void setListAccountID(int listAccountID) {
        this.listAccountID = listAccountID;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

}
