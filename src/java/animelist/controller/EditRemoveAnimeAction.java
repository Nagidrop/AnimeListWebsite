/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;

/* Logic code when user press button in login view */
public class EditRemoveAnimeAction {

    /* Anime List properties used to edit/remove */
    private int progressEdit;
    private int episodesEdit;
    private int statusEdit;
    private int animeIDEdit;
    private int accountIDEdit;
    private String btnAction; // store action of user (edit/remove)
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action

    /* Constructor */
    public EditRemoveAnimeAction() {
    }

    public String execute() throws Exception {
        // if user presses edit
        if (btnAction.equals("Edit")) {
            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();

            boolean result = dao.editAnimeInList(accountIDEdit, animeIDEdit, progressEdit, episodesEdit, statusEdit);
            String url = FAIL; // by default, action is not successful

            // if action is successful
            if (result) {
                url = SUCCESS;
            }

            return url;
            // if user presses delete
        } else if (btnAction.equals("Delete")) {
            /* Instantiate DAO object and interacts with DB */
            AnimeListDAO dao = new AnimeListDAO();

            boolean result = dao.removeAnimeFromList(accountIDEdit, animeIDEdit);
            String url = FAIL; // by default, action is not successful

            // if action is successful
            if (result) {
                url = SUCCESS;
            }

            return url;
            // if there is no action
        } else {
            return FAIL;
        }
    }

    /* Getters and Setters */
    public int getProgressEdit() {
        return progressEdit;
    }

    public void setProgressEdit(int progressEdit) {
        this.progressEdit = progressEdit;
    }

    public int getEpisodesEdit() {
        return episodesEdit;
    }

    public void setEpisodesEdit(int episodesEdit) {
        this.episodesEdit = episodesEdit;
    }

    public int getStatusEdit() {
        return statusEdit;
    }

    public void setStatusEdit(int statusEdit) {
        this.statusEdit = statusEdit;
    }

    public int getAnimeIDEdit() {
        return animeIDEdit;
    }

    public void setAnimeIDEdit(int animeIDEdit) {
        this.animeIDEdit = animeIDEdit;
    }

    public int getAccountIDEdit() {
        return accountIDEdit;
    }

    public void setAccountIDEdit(int accountIDEdit) {
        this.accountIDEdit = accountIDEdit;
    }

    public String getBtnAction() {
        return btnAction;
    }

    public void setBtnAction(String btnAction) {
        this.btnAction = btnAction;
    }
    
    

}
