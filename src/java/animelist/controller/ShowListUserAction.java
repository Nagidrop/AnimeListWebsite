/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.controller;

import animelist.model.AccountDTO;
import animelist.model.AnimeListDAO;
import java.util.ArrayList;

/**
 *
 * @author HAPPY
 */
public class ShowListUserAction {

    ArrayList<AccountDTO> listUser = new ArrayList<>();
    private final String SUCCESS = "success"; // indicates successful action
    String genString = "1";

    public String getGenString() {
        return genString;
    }

    public void setGenString(String genString) {
        this.genString = genString;
    }

    public ShowListUserAction() {
    }

    public String execute() throws Exception {
        AnimeListDAO dao = new AnimeListDAO();
        listUser = dao.getAccountList(2);
        return SUCCESS;
    }

    public ArrayList<AccountDTO> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<AccountDTO> listUser) {
        this.listUser = listUser;
    }

}
