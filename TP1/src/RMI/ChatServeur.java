package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ChatServeur extends UnicastRemoteObject implements InterfaceChatServeur {
    private Map<String, InterfaceChatClient> clients = new HashMap<>();

    public ChatServeur() throws RemoteException {
        super();
        java.rmi.registry.LocateRegistry.createRegistry(2000);
    }


    public static void main(String[] args) {
        try {
            //System.setSecurityManager(new RMISecurityManager());
            InterfaceChatServeur b = new ChatServeur();
            Naming.rebind("rmi://localhost:2000/chatserver", b);
        }catch (Exception e) {
            System.out.println("Chat Server failed: " + e);
        }
    }

    @Override
    public void connect(String pseudo, String url) throws RemoteException {
        try {
            clients.put(pseudo, (InterfaceChatClient) Naming.lookup(url));
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(pseudo + " connected");
        broadcastMessage(new Message(pseudo, " has just connected."));
    }

    @Override
    public void disconnect(String pseudo) throws RemoteException {
        clients.remove(pseudo);
        System.out.println(pseudo + " disconnected");
        broadcastMessage(new Message(pseudo, " has just disconnected."));
    }

    @Override
    public void broadcastMessage(Message msg) throws RemoteException {
        System.out.println(msg);


        for(Map.Entry<String, InterfaceChatClient> entry : clients.entrySet()) {
            try{
                System.out.println("Sending " + msg.getMessage());
                entry.getValue().diffuseMessage(msg);
            } catch(Exception e){
                disconnect(entry.getKey()); //TODO : treat on GUI
            }
        }
    }
}
