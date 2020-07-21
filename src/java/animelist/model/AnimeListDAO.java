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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     * @return Account object (except password) if login successful, null if login credentials don't match
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
            st = conn.prepareStatement("SELECT * FROM Account WHERE username = ? AND password = ? AND deleted_at is null");
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
            st = conn.prepareStatement("SELECT * FROM Anime where deleted_at is null ORDER BY RAND() LIMIT ?");
            st.setInt(1, amount);
            rs = st.executeQuery();

            while (rs.next()) {
                int animeID = rs.getInt("animeID");
                SeasonDTO season = getSeason(rs.getInt("seasonID"));
                ArrayList<StudioDTO> studios = getStudioList(animeID);
                ArrayList<GenreDTO> genres = getGenreList(animeID);
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

                animeList.add(new AnimeDTO(animeID, 0, season, studios, genres, type, name, releaseDate, rating, episodes, status, duration, description, poster, trailer, created_at, deleted_at));
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

    public ArrayList<AnimeDTO> getAllAnimes() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<AnimeDTO> animeList = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("SELECT * FROM Anime where deleted_at is null ORDER BY AnimeID DESC");
            rs = st.executeQuery();

            while (rs.next()) {
                int animeID = rs.getInt("animeID");
                SeasonDTO season = getSeason(rs.getInt("seasonID"));
                ArrayList<StudioDTO> studios = getStudioList(animeID);
                ArrayList<GenreDTO> genres = getGenreList(animeID);
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

                animeList.add(new AnimeDTO(animeID, 0, season, studios, genres, type, name, releaseDate, rating, episodes, status, duration, description, poster, trailer, created_at, deleted_at));
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
                SeasonDTO season = getSeason(rs.getInt("seasonID"));
                ArrayList<StudioDTO> studios = getStudioList(animeID);
                ArrayList<GenreDTO> genres = getGenreList(animeID);
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

                animeList.add(new AnimeDTO(animeID, 0, season, studios, genres, type, name, releaseDate, rating, episodes, status, duration, description, poster, trailer, created_at, deleted_at));
            }

            return animeList;

        } catch (SQLException ex) {
            Logger.getLogger(AnimeListDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AnimeListDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
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
                SeasonDTO season = getSeason(rs.getInt("seasonID"));
                ArrayList<StudioDTO> studios = getStudioList(animeID);
                ArrayList<GenreDTO> genres = getGenreList(animeID);
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

                animeList.add(new AnimeDTO(animeID, 0, season, studios, genres, animetype, name, releaseDate, rating, episodes, status, duration, description, poster, trailer, created_at, deleted_at));
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

    public ArrayList<ListDTO> getSearchAnimeInList(String searchValue, int accountID) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<ListDTO> myList = null;

        try {
            conn = DBUtils.makeConnection();

            st = conn.prepareStatement("select * from list join anime on list.AnimeID = anime.AnimeID where name like ? and list.AccountID = ?");

            st.setString(1, "%" + searchValue + "%");
            st.setInt(2, accountID);

            rs = st.executeQuery();
            while (rs.next()) {
                int animeID = rs.getInt("animeID");
                int status = rs.getInt("status");
                int progress = rs.getInt("progress");
                String statusString = "";
                switch (status) {
                    case 1:
                        statusString = "Currently Watching";

                        break;

                    case 2:
                        statusString = "Completed";

                        break;

                    case 3:
                        statusString = "On Hold";

                        break;

                    case 4:
                        statusString = "Dropped";

                        break;

                    case 5:
                        statusString = "Plan to Watch";

                        break;
                }
                if (myList == null) {
                    myList = new ArrayList<>();
                }

                myList.add(new ListDTO(animeID, accountID, statusString, progress));
            }

            return myList;

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

            st = conn.prepareStatement("SELECT * FROM genre WHERE 1 AND deleted_at is null");

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

            st = conn.prepareStatement("SELECT * FROM studio WHERE 1 AND deleted_at is null");

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

            st = conn.prepareStatement("SELECT * FROM season WHERE 1 AND deleted_at is null ORDER BY SeasonID DESC");

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

    public String countAnimes() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();

            st = conn.prepareStatement("SELECT count(*) as count FROM anime");

            rs = st.executeQuery();
            if (rs.first()) {
                String countString = rs.getString(1);
                return countString;
            }

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
        return "";
    }

    public String countUsers() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();

            st = conn.prepareStatement("SELECT count(*) as count FROM account where RoleID=2");

            rs = st.executeQuery();

            if (rs.first()) {
                String countString = rs.getString(1);
                return countString;
            }

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
        return "";
    }

    public String countAdmin() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();

            st = conn.prepareStatement("SELECT count(*) as count FROM account where RoleID = 1");

            rs = st.executeQuery();
            if (rs.first()) {
                String countString = rs.getString(1);
                return countString;
            }

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
        return "";
    }

    public HashMap<String, String> countAnimeofType() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            HashMap<String, String> list = new HashMap<>();

            st = conn.prepareStatement("SELECT count(*),type as count FROM anime GROUP BY type");
            rs = st.executeQuery();
            if (rs.first()) {
                do {
                    String countString = rs.getString(1);
                    String name = rs.getString(2);
                    list.put(name, countString);
                } while (rs.next());

                return list;
            }

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

    public boolean changeInfo(String username, String fullname, String avatar, String email, int gender) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("Update account set fullname = ?, email=? , avatar=?, gender=? where username=?");
            st.setString(1, fullname);
            st.setString(2, email);
            st.setString(3, avatar);
            st.setInt(4, gender);
            st.setString(5, username);
            int count = st.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean deleteUser(int id, Date deleted_at) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        conn = DBUtils.makeConnection();
        st = conn.prepareStatement("Update account set deleted_at = ? where AccountID=?");
        st.setDate(1, deleted_at);
        st.setInt(2, id);
        int count = st.executeUpdate();
        if (count > 0) {
            return true;
        }

        return false;
    }

    public boolean changePassword(String username, String password) throws SQLException {
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
            Logger.getLogger(AnimeListDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
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
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public AnimeDTO getAnimeDetails(int animeID) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        AnimeDTO anime = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("SELECT * FROM Anime WHERE AnimeID = ?");
            st.setInt(1, animeID);
            rs = st.executeQuery();

            if (rs.next()) {
                SeasonDTO season = getSeason(rs.getInt("seasonID"));
                ArrayList<StudioDTO> studios = getStudioList(animeID);
                ArrayList<GenreDTO> genres = getGenreList(animeID);
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
                if (trailer != null) {
                    trailer = trailer.replace("&autoplay=1", "");
                }
                Date created_at = rs.getDate("created_at");

                anime = new AnimeDTO(animeID, 0, season, studios, genres, type, name, releaseDate, rating, episodes, status, duration, description, poster, trailer, created_at, null);
            }

            return anime;
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

    public SeasonDTO getSeason(int seasonID) throws SQLException {
        String seasonName = null;

        if (seasonID == 0) {
            seasonName = " ";
            return new SeasonDTO(0, "", null, null);
        }

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("SELECT name FROM Season WHERE SeasonID = ? AND deleted_at is null");
            st.setInt(1, seasonID);
            rs = st.executeQuery();

            if (rs.next()) {
                seasonName = rs.getString("name");
            }

            return new SeasonDTO(seasonID, seasonName, null, null);
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

    public ArrayList<StudioDTO> getStudioList(int animeID) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<StudioDTO> studioList = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("SELECT * FROM anime_studio JOIN studio ON studio.StudioID = anime_studio.StudioID WHERE anime_studio.AnimeID = ?");
            st.setInt(1, animeID);
            rs = st.executeQuery();

            while (rs.next()) {
                int studioID = rs.getInt("StudioID");
                String studioName = rs.getString("name");

                if (studioList == null) {
                    studioList = new ArrayList<>();
                }

                studioList.add(new StudioDTO(studioID, studioName, null, null));
            }

            return studioList;
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

    public ArrayList<GenreDTO> getGenreList(int animeID) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<GenreDTO> genreList = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("SELECT * FROM genre_anime JOIN genre on genre.GenreID = genre_anime.GenreID WHERE genre_anime.AnimeID = ?");
            st.setInt(1, animeID);
            rs = st.executeQuery();

            while (rs.next()) {
                int genreID = rs.getInt("GenreID");
                String genreName = rs.getString("name");

                if (genreList == null) {
                    genreList = new ArrayList<>();
                }

                genreList.add(new GenreDTO(genreID, genreName, null, null));
            }

            return genreList;
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

    public ArrayList<ListDTO> getAnimeList(int accountID, int listStatus) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<ListDTO> animeList = null;

        try {
            conn = DBUtils.makeConnection();
            if (listStatus == 0) {
                st = conn.prepareStatement("SELECT * FROM list WHERE AccountID = ?");
            } else {
                st = conn.prepareStatement("SELECT * FROM list WHERE AccountID = ? AND status = ?");
                st.setInt(2, listStatus);
            }
            st.setInt(1, accountID);
            rs = st.executeQuery();

            while (rs.next()) {
                int animeID = rs.getInt("AnimeID");
                int status = rs.getInt("status");
                String statusString = "";

                switch (status) {
                    case 1:
                        statusString = "Currently Watching";

                        break;

                    case 2:
                        statusString = "Completed";

                        break;

                    case 3:
                        statusString = "On Hold";

                        break;

                    case 4:
                        statusString = "Dropped";

                        break;

                    case 5:
                        statusString = "Plan to Watch";

                        break;
                }

                int progress = rs.getInt("progress");

                if (animeList == null) {
                    animeList = new ArrayList<>();
                }

                animeList.add(new ListDTO(animeID, accountID, statusString, progress));
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

    public ArrayList<AccountDTO> getAccountList(int RoleID) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<AccountDTO> accountList = null;
        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("SELECT * FROM account where deleted_at is null");
            rs = st.executeQuery();

            while (rs.next()) {
                int AccountID = rs.getInt("AccountID");
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                int gender = rs.getInt("gender");
                int role = rs.getInt("RoleID");
                String avatar = rs.getString("avatar");

                if (accountList == null) {
                    accountList = new ArrayList<>();
                }

                accountList.add(new AccountDTO(AccountID, role, username, fullname, avatar, email, gender, null, null));
            }

            return accountList;
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

    public boolean changeGenre(int GenreID, String name) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("Update genre set name = ? where GenreID=?");
            st.setString(1, name);
            st.setInt(2, GenreID);
            int count = st.executeUpdate();
            if (count > 0) {
                return true;
            }
        } finally {
            if (st != null) {
                st.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean changeSeason(int SeasonID, String name) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("Update season set name = ? where SeasonID=?");
            st.setString(1, name);
            st.setInt(2, SeasonID);
            int count = st.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean changeStudio(int StudioID, String name) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("Update studio set name = ? where StudioID=?");
            st.setString(1, name);
            st.setInt(2, StudioID);
            int count = st.executeUpdate();
            if (count > 0) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public ArrayList<AnimeDTO> getAnimeDetailsList(ArrayList<ListDTO> animeList) throws SQLException {
        ArrayList<AnimeDTO> animeDetailsList = null;

        if (animeList != null) {
            animeDetailsList = new ArrayList<>();

            for (ListDTO listData : animeList) {
                animeDetailsList.add(getAnimeDetails(listData.getAnimeID()));
            }
        }

        return animeDetailsList;
    }

    public boolean editAnimeInList(int accountID, int animeID, int progress, int episodes, int status) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;

        if (progress > 8888) {
            progress = 8888;
        }

        if (episodes != 0) {
            if (progress > episodes) {
                progress = episodes;
            } else if (progress < 0) {
                progress = 0;
            }

            if (status == 2) {
                progress = episodes;
            } else if (status == 5) {
                progress = 0;
            }
        } else {
            if (progress < 0 || status == 2 || status == 5) {
                progress = 0;
            }
        }

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("UPDATE List SET progress = ?, status = ? WHERE AccountID = ? AND AnimeID = ? ");
            st.setInt(1, progress);
            st.setInt(2, status);
            st.setInt(3, accountID);
            st.setInt(4, animeID);
            int result = st.executeUpdate();

            if (result > 0) {
                return true;
            }
        } finally {
            if (st != null) {
                st.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

        return false;
    }

    public int getTotalAnimesInList(int accountID) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("SELECT COUNT(*) from list where list.AccountID = ? GROUP BY list.AccountID");
            st.setInt(1, accountID);

            rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } finally {
            if (st != null) {
                st.close();
            }

            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public boolean addAnimeToList(int accountID, int animeID, int progress, int episodes, int status) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;

        if (progress > 8888) {
            progress = 8888;
        }

        if (episodes != 0) {
            if (progress > episodes) {
                progress = episodes;
            } else if (progress < 0) {
                progress = 0;
            }

            if (status == 2) {
                progress = episodes;
            } else if (status == 5) {
                progress = 0;
            }
        } else {
            if (progress < 0 || status == 2 || status == 5) {
                progress = 0;
            }
        }

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("INSERT INTO List VALUES(?, ?, ?, ?)");
            st.setInt(1, animeID);
            st.setInt(2, accountID);
            st.setInt(3, status);
            st.setInt(4, progress);
            int result = st.executeUpdate();

            if (result > 0) {
                return true;
            }
        } finally {
            if (st != null) {
                st.close();
            }

            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean removeAnimeFromList(int accountID, int animeID) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("DELETE FROM List WHERE AnimeID = ? AND AccountID = ?");
            st.setInt(1, animeID);
            st.setInt(2, accountID);
            int result = st.executeUpdate();

            if (result > 0) {
                return true;
            }
        } finally {
            if (st != null) {
                st.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

        return false;
    }

    public int getTotalCompletedAnimesInList(int accountID) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("SELECT COUNT(*)from list WHERE list.AccountID = ? and list.status = 2 GROUP by list.AccountID");
            st.setInt(1, accountID);

            rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } finally {
            if (st != null) {
                st.close();
            }

            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
/**
 *
 * @param StudioID
 * @param deleted_at
 * @return
 * @throws SQLException
 */
    public boolean deleteStudio(int StudioID, Date deleted_at) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        conn = DBUtils.makeConnection();
        st = conn.prepareStatement("Update studio set deleted_at = ? where StudioID=?");
        st.setDate(1, deleted_at);
        st.setInt(2, StudioID);
        int count = st.executeUpdate();
        if (count > 0) {
            return true;
        }

        return false;
    }
/**
 *
 * @param SeasonID
 * @param deleted_at
 * @return
 * @throws SQLException
 */
    public boolean deleteSeason(int SeasonID, Date deleted_at) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        conn = DBUtils.makeConnection();
        st = conn.prepareStatement("Update season set deleted_at = ? where SeasonID=?");
        st.setDate(1, deleted_at);
        st.setInt(2, SeasonID);
        int count = st.executeUpdate();
        if (count > 0) {
            return true;
        }

        return false;
    }
/**
 *
 * @param GenreID input
 * @param deleted_at input
 * @return true false
 * @throws SQLException
 */
    public boolean deleteGenre(int GenreID, Date deleted_at) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        conn = DBUtils.makeConnection();
        st = conn.prepareStatement("Update genre set deleted_at = ? where GenreID =?");
        st.setDate(1, deleted_at);
        st.setInt(2, GenreID);
        int count = st.executeUpdate();
        if (count > 0) {
            return true;
        }

        return false;
    }
/**
 *
 * @param AnimeID input
 * @param deleted_at input
 * @return true false
 * @throws SQLException
 */
    public boolean deleteType(int AnimeID, Date deleted_at) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        conn = DBUtils.makeConnection();
        st = conn.prepareStatement("Update type from anime set deleted_at = ? where AnimeID =?");
        st.setDate(1, deleted_at);
        st.setInt(2, AnimeID);
        int count = st.executeUpdate();
        if (count > 0) {
            return true;
        }

        return false;
    }

    public boolean createNewUser(int roleID, String username, String password, String fullname, String email, String gender) throws SQLException {
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
            st = conn.prepareStatement("INSERT INTO Account(RoleID, username, password, fullname, email,gender, created_at) VALUES (?, ?, ?, ?, ?, ?,?) ");
            st.setInt(1, roleID);
            st.setString(2, username);
            st.setString(3, hashPassword);
            st.setString(4, fullname);
            st.setString(5, email);
            st.setString(6, gender);
            st.setDate(7, new Date(System.currentTimeMillis()));
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

    public boolean deleteAnime(String id, Date date) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        conn = DBUtils.makeConnection();
        st = conn.prepareStatement("UPDATE anime SET deleted_at =? WHERE AnimeID= ?");
        st.setDate(1, date);
        st.setString(2, id);

        int rs = st.executeUpdate();
        if (rs == 1) {
            return true;
        }
        return false;
    }
    /**
     *
     * @param name input
     * @return true false
     * @throws SQLException
     */
    public boolean createNewGenre(String name) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("INSERT INTO GENRE(name) VALUES (?)");
            st.setString(1, name);
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
     *
     * @param name input
     * @return true false
     * @throws SQLException
     */
    public boolean createNewSeason(String name) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("INSERT INTO SEASON(name) VALUES (?)");
            st.setString(1, name);
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
     *
     * @param name input
     * @param date input
     * @return true false
     * @throws SQLException
     */
     public boolean createNewStudio(String name, Date date) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("INSERT INTO STUDIO(name,created_at) VALUES (?,?)");
            st.setString(1, name);
            st.setDate(2, date);
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

    public String getAccountUsername(int accountID) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.makeConnection();
            st = conn.prepareStatement("SELECT username FROM Account WHERE AccountID = ?");
            st.setInt(1, accountID);

            rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        } finally {
            if (st != null) {
                st.close();
            }

            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return null;
      }

}
