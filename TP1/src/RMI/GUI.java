package RMI;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class GUI  extends Application {
    private boolean isConnected;
    private ChatClient client;
    private InterfaceChatServeur server;
    private InterfaceHeureServeur hserver;

    @FXML private TextField message_bar;
    @FXML private TextField pseudo;
    @FXML private TextArea chat_bar;
    @FXML private Label time_client;
    @FXML private Label time_server;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        server.disconnect(client.getName());
        isConnected = false;
    }

    @Override
    public void start(Stage stage) throws IOException {
        isConnected = false;
        java.rmi.registry.LocateRegistry.createRegistry(2001);

        FXMLLoader loader = new FXMLLoader();
        String fxmlDocPath = "src/RMI/GUI.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        AnchorPane root = (AnchorPane) loader.load(fxmlStream);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Le chat qui chat");
        stage.show();
    }

    public void connect(){
        if (pseudo.getText().equals("")){
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Choose a pseudo!");
            return;
        }
        try{
            client = new ChatClient(pseudo.getText(), "rmi://localhost:2001/" + pseudo.getText());
            client.setGUI(this);
            server = (InterfaceChatServeur) Naming.lookup("rmi://localhost:2000/chatserver");
            server.connect(client.getName(), client.getUrl());
            isConnected = true;
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Connection problems. Please, try again.");
        }
    }

    @FXML
    public void submitMessage() throws RemoteException {
        if (pseudo.getText().equals("")){
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Choose a pseudo!");
            return;
        }
        if(!isConnected) {
            connect();
        }
        server.broadcastMessage(new Message(pseudo.getText(), message_bar.getText()));
        message_bar.clear();
    }

    @FXML
    void newMessage(Message msg) {
        System.out.println(msg.getMessage());
        chat_bar.appendText(msg.getPseudo() + " : " + msg.getMessage());
    }
}