import bean.Adherent;

import java.sql.*;
import java.util.Scanner;

// https://www.geeksforgeeks.org/sha-224-hash-in-java/
// http://www.postgresqltutorial.com/postgresql-jdbc/insert/
// https://www.jetbrains.com/help/idea/creating-and-running-your-first-java-ee-application.html
// http://www.ntu.edu.sg/home/ehchua/programming/java/javaserverpages.html
// https://tecadmin.net/install-tomcat-9-on-ubuntu/
public class FillUpTables {
    private final String url = "jdbc:postgresql://postgres.ecole.ensicaen.fr/livres";
    private final String user = "lrolan";
    private final String password = "l4ur3n";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public FillUpTables() {}

    public static void main(String [] args) {
        FillUpTables fillUpTables = new FillUpTables();
        fillUpTables.insertInTable();
    }

    public void insertInTable() {
        Scanner input = new Scanner(System.in);

        for(int i=0; i < 5; i++) {
            System.out.println("Tapez le nom : ");
            String nom = input.next();
            System.out.println("Tapez le prenom : ");
            String prenom = input.next();
            System.out.println("Tapez le adresse : ");
            String adresse = input.next();
            System.out.println("Tapez le telephone : ");
            String telephone = input.next();
            System.out.println("Tapez le mail : ");
            String mail = input.next();
            System.out.println("Tapez le password : ");
            String password = input.next();

            Adherent ad = new Adherent(nom, prenom, adresse, telephone, mail, password);
            if(insertAdherent(ad) == 0)
                System.out.println("Error while inserting");
        }
    }

    public long insertAdherent(Adherent adherent) {
        String SQL = "INSERT INTO adherent(nom,prenom, adresse, telephone, email, password) "
                + "VALUES(?,?,?,?,?,?)";

        long id = 0;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, adherent.get_nom());
            pstmt.setString(2, adherent.get_prenom());
            pstmt.setString(3, adherent.get_adresse());
            pstmt.setString(4, adherent.get_telephone());
            pstmt.setString(5, adherent.get_email());
            pstmt.setString(6, adherent.get_password());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
}