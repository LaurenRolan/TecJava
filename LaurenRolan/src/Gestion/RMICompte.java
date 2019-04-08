package Gestion;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMICompte  extends Remote {
    void debiter(int compte, double montant) throws RemoteException;
    void crediter(int compte, double montant) throws RemoteException;
    double getSolde(int compte) throws RemoteException;
}
