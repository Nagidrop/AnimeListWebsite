/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animelist.model;

import animelist.utils.DBUtils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class UserInfoDAO {

    List<AccountDTO> listaccount;

    public UserInfoDAO() {
    }

    public List<AccountDTO> getaccountlist() {
        return listaccount;
    }

    void infoUpdate(String username, String fullname, String avatar, String email, String gender) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("Update account set fullname = ?, email=? , avatar=?, gender=? where username=?");
            st.setString(1, fullname);
            st.setString(2, email);
            st.setString(3, avatar);
            st.setString(4, gender);
            st.setString(5, username);
            int count = st.executeUpdate();
            if (count > 0) {
            }
        } catch (SQLException e) {
        }
    }

    void passwordUpdate(String username, String password) throws SQLException {
        String hashPassword = ""; // store password that is MD5 hashed version of user's password (for validation)

        /* code to hash password using MD5 algorithm */
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(password.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            hashPassword = no.toString(16);
            while (hashPassword.length() < 32) {
                hashPassword = "0" + hashPassword;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AnimeListDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("Update account set password = ? where username=?");
            st.setString(1, hashPassword);
            st.setString(2, username);
            int count = st.executeUpdate();
            if (count > 0) {
            }
        } catch (SQLException e) {
        }
    }
}
