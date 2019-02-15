/*  Author: Lauren Rolan
    Discipline: Technologies Java
 */
package RMI;

import java.rmi.*;

public interface InterfaceHeureServeur extends Remote {
    String getHeure() throws RemoteException;
}
