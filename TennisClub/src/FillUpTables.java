import bean.Adherent;
import bean.Tournoi;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

// https://www.jetbrains.com/help/idea/working-with-the-hibernate-console.html
// https://www.geeksforgeeks.org/sha-224-hash-in-java/
// http://www.postgresqltutorial.com/postgresql-jdbc/insert/
// https://www.jetbrains.com/help/idea/creating-and-running-your-first-java-ee-application.html
// http://www.ntu.edu.sg/home/ehchua/programming/java/javaserverpages.html
// https://tecadmin.net/install-tomcat-9-on-ubuntu/
// https://examples.javacodegeeks.com/enterprise-java/servlet/java-servlet-sendredirect-example/
// https://blog.2ndquadrant.com/using-hibernate-query-language-hql-with-postgresql/


public class FillUpTables {
    private final String url = "jdbc:postgresql://localhost/tennis";
    private final String user = "lrolan";
    private final String password = "l4ur3n";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private FillUpTables() {}

    public static void main(String [] args) {
        FillUpTables fillUpTables = new FillUpTables();
        fillUpTables.addAdherents();
        fillUpTables.addTournois();
    }

    private void addTournois() {
        Scanner input = new Scanner(System.in);
        String continuer = "o";
        while(continuer.equals("o")) {
            System.out.println("Tapez le nom : ");
            input.nextLine();
            String nom = input.nextLine();;
            System.out.println("Tapez le lieu : ");
            input.nextLine();
            String lieu = input.nextLine();
            System.out.println("Tapez la date (JJ-MM-AAAA) : ");
            String date = input.next();
            System.out.println("Tapez le code : ");
            int code = input.nextInt();


            try {
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date dateUtil;
                dateUtil = sdf1.parse(date);
                java.sql.Date sqlDate = new java.sql.Date(dateUtil.getTime());
                Tournoi tournoi = new Tournoi(nom, lieu, sqlDate, code);
                if(insertTournoi(tournoi) == 0)
                    System.out.println("Error while inserting");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.println("Continuer? o:n ");
            continuer = input.next();
        }
    }

    private void addAdherents() {
        Scanner input = new Scanner(System.in);
        String continuer = "o";
        while(continuer.equals("o")) {
            System.out.println("Tapez le nom : ");
            String nom = input.next();
            System.out.println("Tapez le prenom : ");
            String prenom = input.next();
            System.out.println("Tapez le adresse : ");
            input.nextLine();
            String adresse = input.nextLine();
            System.out.println("Tapez le telephone : ");
            String telephone = input.next();
            System.out.println("Tapez le mail : ");
            String mail = input.next();
            System.out.println("Tapez le password : ");
            String password = input.next();

            Adherent ad = new Adherent(nom, prenom, adresse, telephone, mail, password, 1);
            if(insertAdherent(ad) == 0)
                System.out.println("Error while inserting");

            System.out.println("Continuer? o:n ");
            continuer = input.next();
        }
    }

    private long insertTournoi(Tournoi tournoi) {
        String SQL = "INSERT INTO tournoi(nom, lieu, date, codetournoi) "
                + "VALUES(?,?,?, ?)";

        long id = 0;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, tournoi.getNom());
            pstmt.setString(2, tournoi.getLieu());
            pstmt.setDate(3, tournoi.getDate());
            pstmt.setInt(4, tournoi.getCodeTournoi());

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

    private long insertAdherent(Adherent adherent) {
        String SQL = "INSERT INTO adherent(nom,prenom, adresse, telephone, email, password) "
                + "VALUES(?,?,?,?,?,?)";

        long id = 0;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, adherent.getNom());
            pstmt.setString(2, adherent.getPrenom());
            pstmt.setString(3, adherent.getAdresse());
            pstmt.setString(4, adherent.getTelephone());
            pstmt.setString(5, adherent.getEmail());
            pstmt.setString(6, adherent.getPassword());

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