/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.controller;

import animelist.model.AnimeListDAO;

/* Logic code when user press button in login view */
public class EditListAction {

    private int progressEdit; // username passed from form input
    private int episodesEdit; // password passed from form input
    private int statusEdit; // password passed from form input
    private int animeIDEdit; // password passed from form input
    private int accountIDEdit; // password passed from form input
    private final String FAIL = "fail"; // indicates failed action
    private final String SUCCESS = "success"; // indicates successful action

    /* Constructor */
    public EditListAction() {
    }

    public String execute() throws Exception {
        /* Instantiate DAO object and interacts with DB */
        AnimeListDAO dao = new AnimeListDAO();
        System.out.println("accountID" + accountIDEdit);
        System.out.println("animeIDEdit" +animeIDEdit);
        System.out.println("progressEdit" +progressEdit);
        System.out.println("episodesEdit" +episodesEdit);
        System.out.println("statusEdit" +statusEdit);
        boolean result = dao.editAnimeInList(accountIDEdit, animeIDEdit, progressEdit, episodesEdit, statusEdit);
        String url = FAIL; // by default, action is not successful

        if (result) {
            url = SUCCESS;
        }

        return url;
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

}
