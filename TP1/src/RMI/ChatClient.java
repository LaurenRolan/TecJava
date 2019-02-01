package RMI;

import com.sun.org.apache.xml.internal.utils.URI;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ChatClient implements InterfaceChatClient {

    public static void main( String args[]) {
        try {
            InterfaceChatServeur ics = (InterfaceChatServeur) Naming.lookup("rmi/::localhost/discserver");
            InterfaceHeureServeur ihs = (InterfaceHeureServeur) Naming.lookup("rmi/::localhost/heureserver");
        } catch(NotBoundException | URI.MalformedURIException| RemoteException e) {
            System.err.println("Error : " + e.getMessage());
        }

    }

    @Override
    public void diffuseMessage(Message m) throws RemoteException {

    }

    public String getHeure() {
        Calendar calendar = new GregorianCalendar();
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + " : " + String.valueOf(calendar.get(Calendar.MINUTE));
    }
}
