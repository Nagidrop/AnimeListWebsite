package animelist.model;

import anime.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Quan Duc Loc CE140037 (SE1401)
 */
public class AnimeListDAO {

    private List<AnimeDTO> SearchAnimeList;

    public AnimeListDAO() {
    }

    public List<AnimeDTO> getSearchAnimeList() {
        return SearchAnimeList;
    }

    public void loadSearchAnime(String name, String type, int StudioID, int genreID, int seasonID) throws SQLException {
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
    }
}
