package RMI;


import Socket.Client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ChatServeur extends UnicastRemoteObject implements InterfaceChatServeur {
    private List clients = new ArrayList<ChatClient>();

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
        clients.add(new ChatClient(pseudo, url));
        System.out.println(pseudo + "  got connected....");
        broadcastMessage(new Message(pseudo, " has just connected."));
    }

    @Override
    public void disconnect(String pseudo) throws RemoteException {
        System.out.println("Removing " + pseudo);
        clients.remove(new ChatClient(pseudo, "rmi://localhost:2001/" + pseudo));
        broadcastMessage(new Message(pseudo, " has just disconnected."));
    }

    @Override
    public void broadcastMessage(Message msg) throws RemoteException {
        System.out.println(msg);

        for(Object client : clients) {
            try{
                InterfaceChatClient tmp = (InterfaceChatClient) client;
                System.out.println(msg.getMessage());
                tmp.diffuseMessage(msg);
            } catch(Exception e){
                System.err.println("Error : " + e.getMessage());
            }
        }
    }
}
