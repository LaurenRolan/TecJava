package Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
            File[] listOfFiles = folder.listFiles();
            for(File f : listOfFiles) {
                flux.println(f.getName());
            }

        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }

}
