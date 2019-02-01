package RMI;

import java.rmi.*;

public interface InterfaceHeureServeur extends Remote {
    String getHeure() throws RemoteException;
}
