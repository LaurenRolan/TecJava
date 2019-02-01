package RMI;


import java.rmi.RemoteException;
import java.util.*;

public class ChatServeur implements InterfaceChatServeur {
    private Map<String, String> clients = new HashMap<>();

    @Override
    public void connect(String pseudo, String url) throws RemoteException {
        clients.put(pseudo, url);
        System.out.println(pseudo + "  got connected....");
        broadcastMessage(new Message(pseudo, " has just connected."));
    }

    @Override
    public void disconnect(String pseudo) throws RemoteException {
        clients.remove(pseudo);
        broadcastMessage(new Message(pseudo, " has just disconnected."));
    }

    @Override
    public void broadcastMessage(Message msg) throws RemoteException {
        System.out.println(msg);

        Iterator it = clients.entrySet().iterator();
        while (it.hasNext()) {
            try{
                InterfaceChatClient tmp=(InterfaceChatClient) it;
                tmp.diffuseMessage(msg);
            } catch(Exception e){
                System.err.println("Error : " + e.getMessage());
            }
            it.remove(); // avoids a ConcurrentModificationException
        }

    }
}
