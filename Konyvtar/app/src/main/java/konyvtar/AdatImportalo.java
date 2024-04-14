import konyvtar.CSVOlvaso;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AdatImportalo {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/kolcsonzokDB?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "yourUsername";
    private static final String DB_PASSWORD = "yourPassword";

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Használat: java AdatImportalo <Kolcsonzok.csv> <Kolcsonzesek.csv>");
            return;
        }

        CSVOlvaso olvaso = new CSVOlvaso();
        try {
            importalKolcsonzok(args[0], olvaso);
            importalKolcsonzesek(args[1], olvaso);
        } catch (IOException e) {
            System.out.println("Hiba az adatok importálása során: " + e.getMessage());
        }
    }

    private static void importalKolcsonzok(String filePath, CSVOlvaso olvaso) throws IOException {
        List<String[]> sorok = olvaso.olvasCSV(filePath);
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Kolcsonzok (nev, szulIdo) VALUES (?, ?)");
            for (String[] sor : sorok) {
                stmt.setString(1, sor[0]);
                stmt.setString(2, sor[1]);
                stmt.executeUpdate();
            }
            System.out.println("Sikeresen importálva: " + sorok.size() + " sor.");
        } catch (SQLException e) {
            System.out.println("Hiba az adatok importálása során: " + e.getMessage());
        }
    }

    private static void importalKolcsonzesek(String filePath, CSVOlvaso olvaso) throws IOException {
        List<String[]> sorok = olvaso.olvasCSV(filePath);
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Kolcsonzesek (kolcsonzokId, iro, mufaj, cim) VALUES (?, ?, ?, ?)");
            for (String[] sor : sorok) {
                stmt.setInt(1, Integer.parseInt(sor[0]));
                stmt.setString(2, sor[1]);
                stmt.setString(3, sor[2]);
                stmt.setString(4, sor[3]);
                stmt.executeUpdate();
            }
            System.out.println("Sikeresen importálva: " + sorok.size() + " sor.");
        } catch (SQLException e) {
            System.out.println("Hiba az adatok importálása során: " + e.getMessage());
        }
    }
}
