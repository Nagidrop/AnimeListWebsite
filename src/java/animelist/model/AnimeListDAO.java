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
import java.util.logging.Level;
import java.util.logging.Logger;

/* DAO for Anime List Project */
public class AnimeListDAO {

    public AnimeListDAO() {
    }
    
    public AccountDTO login(String username, String password) {
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

    public boolean register(String username, String password, String fullname, String email) {
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
            st = conn.prepareStatement("INSERT INTO Account(RoleID, username, password, fullname, email, created_at) VALUES (?, ?, ?, ?, ?) ");
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
        } catch (SQLException ex) {
            Logger.getLogger(AnimeListDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
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

        return false;
    }

    public ArrayList<AnimeDTO> getAnimes(int amount) {
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
    
    
     public ArrayList<AnimeDTO> loadSearchAnime(String name, String type, int StudioID, int genreID, int seasonID) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();

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
        return null;
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
    
    public ArrayList<SeasonDTO> getSeasons() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<SeasonDTO> seasons = null;
        try {
            conn = DBUtils.makeConnection();

            st = conn.prepareStatement("SELECT * FROM studio");

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
