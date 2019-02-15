/*  Author: Lauren Rolan
    Discipline: Technologies Java
 */
package RMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ServeurHoraire extends UnicastRemoteObject implements InterfaceHeureServeur{

    protected ServeurHoraire() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            //System.setSecurityManager(new RMISecurityManager());
            java.rmi.registry.LocateRegistry.createRegistry(1999);
            InterfaceHeureServeur b = new ServeurHoraire();
            Naming.rebind("rmi://localhost:1999/hourserver", b);
        }catch (Exception e) {
            System.out.println("Chat Server failed: " + e);
        }
    }

    @Override
    public String getHeure() throws RemoteException {
        Calendar calendar = new GregorianCalendar();
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + " : " + String.valueOf(calendar.get(Calendar.MINUTE));
    }
}
