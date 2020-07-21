/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;

/* This action got called when user add, edit, remove anime from list from anime page view */
public class AddRemoveAnimeAction {

    /* Anime properties to update list */
    private int animeID;
    private int progress;
    private int episodes;
    private int status;
    private int accountID;
    private String btnAction; // store button value
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action

    /* Constructor */
    public AddRemoveAnimeAction() {
    }

    public String execute() throws Exception {
        // If user adds an anime
        if (btnAction.equals("Add")) {
            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();

            boolean result = dao.addAnimeToList(accountID, animeID, progress, episodes, status);
            String url = FAIL; // by default, action is not successful

            // if action is successful
            if (result) {
                url = SUCCESS;
            }

            return url;
            // If user edits an anime
        } else if (btnAction.equals("Edit")) {
            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();

            boolean result = dao.editAnimeInList(accountID, animeID, progress, episodes, status);
            String url = FAIL; // by default, action is not successful

            // if action is successful
            if (result) {
                url = SUCCESS;
            }

            return url;
            // If user remove an anime
        } else if (btnAction.equals("Remove")) {
            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();

            boolean result = dao.removeAnimeFromList(accountID, animeID);
            String url = FAIL; // by default, action is not successful

            // if action is successful
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
