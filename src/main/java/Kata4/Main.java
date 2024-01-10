package Kata4;

import swing.Histogram;
import swing.MainFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection conexion = DriverManager.getConnection("jdbc:sqlite:chinook.db")) {
            SqliteTrackLoader Tabla = new SqliteTrackLoader(conexion);

            Map<String, Integer> map = new HashMap<>();
            for (Track track : Tabla.loadsql()) {
                String tipo = track.genre();
                map.put(tipo, map.getOrDefault(tipo, 0) + 1);
            }

            Histogram histogram = new Histogram("Histograma", "Generos", "Cantidad", map);
            MainFrame frame = new MainFrame();
            frame.histogramDisplay().show(histogram);
            frame.setVisible(true);
        }
    }
}
