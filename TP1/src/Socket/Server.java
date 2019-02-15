/*  Author: Lauren Rolan
    Discipline: Technologies Java
 */
package Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class Server implements Runnable{

    ServerSocket s = null;
    PrintStream flux = null;
    Socket clientSocket = null;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }

    public Server() throws IOException {

        try {
            s = new ServerSocket(2000);
        } catch (IOException e) {
            System.err.println("Erreur socket " + e);
            System.exit(1);
        }


        while(true) {
            try {
                clientSocket = s.accept();
            } catch (IOException e) {
                System.err.println("Error : " + e);
                clientSocket.close();
            }

            run();
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader request = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String path = request.readLine();
            System.out.println("The request is : " + path);
            flux = new PrintStream(clientSocket.getOutputStream(), true);

            File folder = new File(path);

            if(folder.exists()) {

                File[] listOfFiles = folder.listFiles();
                flux.println(listOfFiles.length);
                for (File f : listOfFiles) {
                    flux.println(f.getName());
                }
            } else {
                flux.println(-1);
            }

        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }

}
