package bean;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Adherent {
    private String _nom;
    private String _prenom;
    private String _adresse;
    private String _telephone;
    private String _email;
    private String _password;

    public Adherent(String nom, String prenom, String adresse, String telephone, String email, String password) {
        _nom = nom;
        _prenom = prenom;
        _adresse = adresse;
        _telephone = telephone;
        _email = email;
        _password = password;
    }

    public String get_nom() {
        return _nom;
    }

    public String get_prenom() {
        return _prenom;
    }

    public String get_adresse() {
        return _adresse;
    }

    public String get_telephone() {
        return _telephone;
    }

    public String get_email() {
        return _email;
    }

    public String get_password() {
        return encryptThisString(_password);
    }

    public static String encryptThisString(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-224
            MessageDigest md = MessageDigest.getInstance("SHA-224");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

