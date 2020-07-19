/**
 *
 * @author Wibu Group (Duc Tong, Duc Loc, Minh Thang, Tien Minh)
 */
package animelist.model;

import animelist.utils.DBUtils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* DAO for Anime List Project */
public class AnimeListDAO {

    /* Constructor */
    public AnimeListDAO() {
    }

    /**
     * Check if user login credentials match ones in database
     *
     * @param username
     * @param password
     * @return Account object (except password) if login successful, null if
     * login credentials don't match
     * @throws java.sql.SQLException
     */
    public AccountDTO login(String username, String password) throws SQLException {
        String hashPassword = ""; // store password that is MD5 hashed version of user's password (for validation)

        /* Code to hash password using MD5 algorithm */
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

        /* Declare Connection, PreparedStatement and ResultSet variables */
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("SELECT * FROM Account WHERE username = ? AND password = ?");
            st.setString(1, username);
            st.setString(2, hashPassword);
            rs = st.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("AccountID");
                int roleID = rs.getInt("RoleID");
                String fullname = rs.getString("fullname");
                String avatar = rs.getString("avatar");
                String email = rs.getString("email");
                int gender = rs.getInt("gender");
                Date created_at = rs.getDate("created_at");
                Date deleted_at = rs.getDate("deleted_at");

                return new AccountDTO(id, roleID, username, fullname, avatar, email, gender, created_at, deleted_at);
            }
        } finally {
            /* Close the JDBC resources after use */
            if (rs != null) {
                rs.close();
            }

            if (st != null) {
                st.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

        return null;
    }

    /**
     * Register a new user to database
     *
     * @param username
     * @param password
     * @param fullname
     * @param email
     * @return true if successful, false otherwise
     * @throws java.sql.SQLException
     */
    public boolean register(String username, String password, String fullname, String email) throws SQLException {
        String hashPassword = ""; // store password that is MD5 hashed version of user's password (for validation)

        /* Code to hash password using MD5 algorithm */
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

        /* Declare Connection, PreparedStatement variables */
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("INSERT INTO Account(RoleID, username, password, fullname, email, created_at) VALUES (?, ?, ?, ?, ?, ?) ");
            st.setInt(1, 2);
            st.setString(2, username);
            st.setString(3, hashPassword);
            st.setString(4, fullname);
            st.setString(5, email);
            st.setDate(6, new Date(System.currentTimeMillis()));
            int result = st.executeUpdate();

            if (result > 0) {
                return true;
            }
        } finally {
            /* Close the JDBC resources after use */

            if (st != null) {
                st.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

        return false;
    }

    /**
     * Get an arbitrary anime list of an amount
     *
     * @param amount (amount of animes to get)
     * @return list of animes of such amount
     * @throws java.sql.SQLException
     */
    public ArrayList<AnimeDTO> getAnimes(int amount) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<AnimeDTO> animeList = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("SELECT * FROM Anime ORDER BY RAND() LIMIT ?");
            st.setInt(1, amount);
            rs = st.executeQuery();

            while (rs.next()) {
                int animeID = rs.getInt("animeID");
                int seasonID = rs.getInt("seasonID");
                String type = rs.getString("type");
                String name = rs.getString("name");
                Date releaseDate = rs.getDate("releaseDate");
                String rating = rs.getString("rating");
                int episodes = rs.getInt("episodes");
                String status = rs.getString("status");
                String duration = rs.getString("duration");
                String description = rs.getString("description");
                String poster = rs.getString("poster");
                String trailer = rs.getString("trailer");
                Date created_at = rs.getDate("created_at");
                Date deleted_at = rs.getDate("deleted_at");

                if (animeList == null) {
                    animeList = new ArrayList<>();
                }

                animeList.add(new AnimeDTO(animeID, 0, seasonID, type, name, releaseDate, rating, episodes, status, duration, description, poster, trailer, created_at, deleted_at));
            }

            return animeList;
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (st != null) {
                st.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
    }

    public ArrayList<AnimeDTO> getTopAnimesByType(int top) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<AnimeDTO> animeList = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("SELECT TOP(?) FROM Anime ORDER BY Type");
            st.setInt(1, top);
            rs = st.executeQuery();

            while (rs.next()) {
                int animeID = rs.getInt("animeID");
                int seasonID = rs.getInt("seasonID");
                String type = rs.getString("type");
                String name = rs.getString("name");
                Date releaseDate = rs.getDate("releaseDate");
                String rating = rs.getString("rating");
                int episodes = rs.getInt("episodes");
                String status = rs.getString("status");
                String duration = rs.getString("duration");
                String description = rs.getString("description");
                String poster = rs.getString("poster");
                String trailer = rs.getString("trailer");
                Date created_at = rs.getDate("created_at");
                Date deleted_at = rs.getDate("deleted_at");

                if (animeList == null) {
                    animeList = new ArrayList<>();
                }

                animeList.add(new AnimeDTO(animeID, 0, seasonID, type, name, releaseDate, rating, episodes, status, duration, description, poster, trailer, created_at, deleted_at));
            }

            return animeList;
        } catch (SQLException ex) {
            Logger.getLogger(AnimeListDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (st != null) {
                    st.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AnimeListDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public ArrayList<AnimeDTO> getSearchAnime(String searchValue, String type, String StudioID, String genreID, String seasonID) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<AnimeDTO> animeList = null;

        try {
            conn = DBUtils.makeConnection();

            st = conn.prepareStatement("SELECT anime.AccountID, anime.AnimeID, anime.SeasonID, anime.name , anime.type , anime.releaseDate , anime.rating , anime.episodes , anime.status , anime.duration, anime.description, anime.poster, anime.trailer, anime.created_at, anime.deleted_at, StudioID, GenreID \n"
                    + "FROM \n"
                    + "anime JOIN anime_studio on anime.AnimeID = anime_studio.AnimeID  \n"
                    + "JOIN genre_anime on genre_anime.AnimeID = anime.AnimeID \n"
                    + "WHERE anime.name like ? and \n"
                    + "type like ? and \n"
                    + "GenreID like ? and \n"
                    + "StudioID like ? and \n"
                    + "(SeasonID like ? or SeasonID is NULL)\n"
                    + "GROUP BY anime.name");

            st.setString(1, "%" + searchValue + "%");
            st.setString(2, type);
            st.setString(3, genreID);
            st.setString(4, StudioID);
            st.setString(5, seasonID);

            rs = st.executeQuery();
            while (rs.next()) {
                int animeID = rs.getInt("animeID");
                int seasonid = rs.getInt("seasonID");
                String animetype = rs.getString("type");
                String name = rs.getString("name");
                Date releaseDate = rs.getDate("releaseDate");
                String rating = rs.getString("rating");
                int episodes = rs.getInt("episodes");
                String status = rs.getString("status");
                String duration = rs.getString("duration");
                String description = rs.getString("description");
                String poster = rs.getString("poster");
                String trailer = rs.getString("trailer");
                Date created_at = rs.getDate("created_at");
                Date deleted_at = rs.getDate("deleted_at");

                if (animeList == null) {
                    animeList = new ArrayList<>();
                }

                animeList.add(new AnimeDTO(animeID, 0, seasonid, animetype, name, releaseDate, rating, episodes, status, duration, description, poster, trailer, created_at, deleted_at));
            }

            return animeList;

        } finally {
            if (conn != null) {
                conn.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public ArrayList<String> getTypes() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<String> types = null;
        try {
            conn = DBUtils.makeConnection();

            st = conn.prepareStatement("SELECT type FROM anime GROUP BY type");

            rs = st.executeQuery();

            while (rs.next()) {
                if (types == null) {
                    types = new ArrayList<>();
                }
                types.add(rs.getString("type"));
            }
            return types;
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public ArrayList<GenreDTO> getGenres() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<GenreDTO> genres = null;
        try {
            conn = DBUtils.makeConnection();

            st = conn.prepareStatement("SELECT * FROM genre");

            rs = st.executeQuery();

            while (rs.next()) {
                int GenreID = rs.getInt("GenreID");
                String name = rs.getString("name");
                Date created_at = rs.getDate("created_at");
                Date deleted_at = rs.getDate("deleted_at");
                GenreDTO genre = new GenreDTO(GenreID, name, created_at, deleted_at);
                if (genres == null) {
                    genres = new ArrayList<>();
                }
                genres.add(genre);
            }

            return genres;

        } finally {
            if (conn != null) {
                conn.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public ArrayList<StudioDTO> getStudios() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<StudioDTO> studios = null;
        try {
            conn = DBUtils.makeConnection();

            st = conn.prepareStatement("SELECT * FROM studio");

            rs = st.executeQuery();

            while (rs.next()) {
                int StudioID = rs.getInt("StudioID");
                String name = rs.getString("name");
                Date created_at = rs.getDate("created_at");
                Date deleted_at = rs.getDate("deleted_at");
                StudioDTO studio = new StudioDTO(StudioID, name, created_at, deleted_at);
                if (studios == null) {
                    studios = new ArrayList<>();
                }
                studios.add(studio);
            }

            return studios;

        } finally {
            if (conn != null) {
                conn.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public List<SeasonDTO> getSeasons() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<SeasonDTO> seasons = null;
        try {
            conn = DBUtils.makeConnection();

            st = conn.prepareStatement("SELECT * FROM season ORDER BY SeasonID desc");

            rs = st.executeQuery();

            while (rs.next()) {
                int SeasonID = rs.getInt("SeasonID");
                String name = rs.getString("name");
                Date created_at = rs.getDate("created_at");
                Date deleted_at = rs.getDate("deleted_at");
                SeasonDTO season = new SeasonDTO(SeasonID, name, created_at, deleted_at);
                if (seasons == null) {
                    seasons = new ArrayList<>();
                }
                seasons.add(season);
            }

            return seasons;

        } finally {
            if (conn != null) {
                conn.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

}
