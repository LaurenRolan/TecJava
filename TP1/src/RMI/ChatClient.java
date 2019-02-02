package RMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ChatClient extends UnicastRemoteObject implements InterfaceChatClient {

    GUI gui;
    String name;
    String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public ChatClient(String name, String url) throws RemoteException {
        super();
        this.name = name;
        this.url = url;
    }

    @Override
    public void diffuseMessage(Message m) throws RemoteException {
        System.out.println(m.getMessage());
        gui.newMessage(m);
    }

    public void setGUI(GUI newGUI){
        gui = newGUI;
    }
}
