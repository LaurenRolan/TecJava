package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    public static void main(String args[]) {
        Socket s = null;
        PrintStream flux = null;
        try {
            s = new Socket("localhost", 2000);
            flux = new PrintStream(s.getOutputStream(), true);
            flux.println("/home/eleves/promo20/info/lrolan/Bureau");
            BufferedReader reponse = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line;
            while((line = reponse.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
            System.exit(1);
        }

    }
}
