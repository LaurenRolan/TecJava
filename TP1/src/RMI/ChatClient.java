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

    public ChatClient(String name) throws RemoteException {
        super();
        this.name = name;
        try {
            //System.setSecurityManager(new RMISecurityManager());
            java.rmi.registry.LocateRegistry.createRegistry(port);
            Naming.rebind("rmi://localhost:" + String.valueOf(port) + "/" + name, this);
            this.url = "rmi://localhost:" + String.valueOf(port) + "/" + name;
            port++;
            System.out.println(port);
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
