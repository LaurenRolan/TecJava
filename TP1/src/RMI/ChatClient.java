package RMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClient extends UnicastRemoteObject implements InterfaceChatClient {

    private GUI gui;
    private String name;
    private String url;
    private static int port = 2001;

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
        try {
            //System.setSecurityManager(new RMISecurityManager());
            java.rmi.registry.LocateRegistry.createRegistry(port);
            port++;
            Naming.rebind("rmi://localhost:2001/" + name, this);
        }catch (Exception e) {
            System.out.println("Chat client failed: " + e);
        }
    }

    @Override
    public void diffuseMessage(Message m) throws RemoteException {
        gui.newMessage(m);
    }

    public void setGUI(GUI newGUI){
        gui = newGUI;
    }
}
