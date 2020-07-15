package animelist.model;

import java.io.Serializable;

/**
 *
 * @author Quan Duc Loc CE140037 (SE1401)
 */
public class NotificationDTO implements Serializable {
    private int accountId;
    private int status;
    private String description;

    public NotificationDTO() {
    }

    public NotificationDTO(int accountId, int status, String description) {
        this.accountId = accountId;
        this.status = status;
        this.description = description;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
