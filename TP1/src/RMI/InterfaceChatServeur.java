/*  Author: Lauren Rolan
    Discipline: Technologies Java
 */
package RMI;

import java.rmi.* ;
public interface InterfaceChatServeur extends Remote
{
    /** Connexion sur le serveur
     * @param pseudo
    pseudo choisi
     * @param url
    url sur l'objet client distant
     * @throws RemoteException
     */
    public void connect (String pseudo,String url) throws RemoteException ;
    /**
     *
     * @param pseudo
    pseudo qui se deconnecte
     * @throws RemoteException
     */
    public void disconnect (String pseudo) throws RemoteException ;
    /**
     *
     * @param msg Message diffuse a tous les utilisateurs
     * @throws RemoteException
     */
    public void broadcastMessage (Message msg) throws RemoteException ;
}