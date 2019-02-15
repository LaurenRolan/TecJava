/*  Author: Lauren Rolan
    Discipline: Technologies Java
 */
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
            flux.println("/home/eleves/promo20/info/lrolan/Desktop");
            BufferedReader reponse = new BufferedReader(new InputStreamReader(s.getInputStream()));
            int number_of_files = Integer.valueOf(reponse.readLine());
            if(number_of_files == -1) {
                System.out.println("Error");
            } else {
                for(int i = 0; i <number_of_files; i++) {
                    System.out.println(reponse.readLine());
                }
            }

        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
            System.exit(1);
        }

    }
}
