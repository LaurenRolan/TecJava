package Gestion;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Serveur extends UnicastRemoteObject implements RMIServeur {
    private GestionBase gestionBase;

    public Serveur() throws RemoteException {
        super();
        java.rmi.registry.LocateRegistry.createRegistry(2000);
        gestionBase = new GestionBase();
    }


    public static void main(String[] args) {
        try {
            //System.setSecurityManager(new RMISecurityManager());
            RMIServeur s = new Serveur();
            Naming.rebind("rmi://localhost:2000/banque", s);
        }catch (Exception e) {
            System.out.println("Server failed: " + e);
        }
    }

    @Override
    public void debiter (int id, double montant) throws RemoteException {
        gestionBase.debiter(id, montant);
    }

    @Override
    public void crediter( int id, double montant ) throws RemoteException {
        gestionBase.crediter(id, montant);
    }

    @Override
    public double retourner( int id) throws RemoteException {
        return gestionBase.retourner(id);
    }

}
