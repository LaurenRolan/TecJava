/*  Author: Lauren Rolan
    Discipline: Technologies Java
 */
package RMI;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatClient extends UnicastRemoteObject implements InterfaceChatClient {

    private GUI gui;
    private String name;
    private String url;
    private Registry registry;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public ChatClient(String name, int port) throws RemoteException {
        super();
        this.name = name;
        try {
            //System.setSecurityManager(new RMISecurityManager());
            registry = java.rmi.registry.LocateRegistry.createRegistry(port);
            String inetAddress = InetAddress.getLocalHost().getHostAddress();
            this.url = "rmi://" + inetAddress +  ":" + String.valueOf(port) + "/" + name;
            Naming.rebind(this.url, this);
            System.out.println(port);
        }catch (Exception e) {
            System.out.println("Chat client failed: " + e);
        }
    }

    @Override
    public void diffuseMessage(Message m) throws RemoteException {
        gui.newMessage(m);
    }

    public void disconnect() {
        try {
            UnicastRemoteObject.unexportObject(registry, true);
        } catch (NoSuchObjectException e) {
            System.out.println("Could not unexport : " + e.getMessage());
        }
    }

    public void setGUI(GUI newGUI){
        gui = newGUI;
    }
}
