package Gestion;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServeur extends Remote {
    void debiter (int id, double montant) throws RemoteException;
    void crediter( int id, double montant ) throws RemoteException;
    double retourner( int id) throws RemoteException;
}
