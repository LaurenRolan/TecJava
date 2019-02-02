package RMI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class GUI  extends Application implements Initializable {
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
    public void start(Stage stage) throws IOException, NotBoundException {
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

    public String getHour() {
        Calendar calendar = new GregorianCalendar();
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + " : " + String.valueOf(calendar.get(Calendar.MINUTE));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            hserver = (InterfaceHeureServeur) Naming.lookup("rmi://localhost:1999/hourserver");
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
        System.out.println(time_server.getText());
        try {
            time_server.setText(hserver.getHeure());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(10), actionEvent -> {
            time_client.setText(getHour());
            try {
                time_server.setText(hserver.getHeure());
            } catch (RemoteException e) {
                System.out.println("Error : " + e.getMessage());
            }
        }));
        timeUpdate.setCycleCount(Timeline.INDEFINITE);
        timeUpdate.play();
    }
}