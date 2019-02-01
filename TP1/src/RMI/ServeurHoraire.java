package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ServeurHoraire extends UnicastRemoteObject implements InterfaceHeureServeur{

    protected ServeurHoraire() throws RemoteException {
        super();
    }

    @Override
    public String getHeure() throws RemoteException {
        Calendar calendar = new GregorianCalendar();
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + " : " + String.valueOf(calendar.get(Calendar.MINUTE));
    }
}
