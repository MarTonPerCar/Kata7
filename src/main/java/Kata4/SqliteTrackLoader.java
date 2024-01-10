package Kata4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqliteTrackLoader implements TrackLoader{
    private final Connection conexion;

    public SqliteTrackLoader(Connection conexion) {
        this.conexion = conexion;
    }

    private ResultSet queryAll() throws SQLException {
        return conexion.createStatement().executeQuery(
                "SELECT tracks.Name AS Track, tracks.Milliseconds, tracks.UnitPrice, albums.Title AS Album, genres.Name AS genre, artists.Name AS Artist " +
                        "FROM tracks " +
                        "JOIN albums ON tracks.AlbumId = albums.AlbumId " +
                        "JOIN genres ON tracks.GenreId = genres.GenreId " +
                        "JOIN artists ON albums.ArtistId = artists.ArtistId"
        );
    }

    @Override
    public List<Track> loadsql() {
        try {
            return load(queryAll());
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    private List<Track> load(ResultSet resultSet) throws SQLException {
        List<Track> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(
                    new Track(
                            resultSet.getString("track"),
                            resultSet.getInt("milliseconds"),
                            resultSet.getDouble("unitPrice"),
                            resultSet.getString("album"),
                            resultSet.getString("genre"),
                            resultSet.getString("artist")
                    )
            );
        }
        return list;
    }
}
