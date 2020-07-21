/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;

/* Logic code when user press button in login view */
public class AddRemoveAnimeAction {

    private int animeID; // password passed from form input
    private int progress; // username passed from form input
    private int episodes; // username passed from form input
    private int status; // password passed from form input
    private int accountID; // password passed from form input
    private String btnAction;
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action

    /* Constructor */
    public AddRemoveAnimeAction() {
    }

    public String execute() throws Exception {
        if (btnAction.equals("Add")) {
            /* Instantiate DAO object and calls login method to check from DB */
            AnimeListDAO dao = new AnimeListDAO();

            boolean result = dao.addAnimeToList(accountID, animeID, progress, episodes, status);
            String url = FAIL; // by default, login is not successful

            if (result) {
                url = SUCCESS;
            }

            return url;
        } else if (btnAction.equals("Edit")) {
            /* Instantiate DAO object and calls login method to check from DB */
            AnimeListDAO dao = new AnimeListDAO();

            boolean result = dao.editAnimeInList(accountID, animeID, progress, episodes, status);
            String url = FAIL; // by default, login is not successful

            if (result) {
                url = SUCCESS;
            }

            return url;
        } else if (btnAction.equals("Remove")) {
            /* Instantiate DAO object and calls login method to check from DB */
            AnimeListDAO dao = new AnimeListDAO();

            boolean result = dao.removeAnimeFromList(accountID, animeID);
            String url = FAIL; // by default, login is not successful

            if (result) {
                url = SUCCESS;
            }

            return url;
        } else {
            return FAIL;
        }
    }

    /* Getters and Setters */
    public int getAnimeID() {
        return animeID;
    }

    public void setAnimeID(int animeID) {
        this.animeID = animeID;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getBtnAction() {
        return btnAction;
    }

    public void setBtnAction(String btnAction) {
        this.btnAction = btnAction;
    }
}
