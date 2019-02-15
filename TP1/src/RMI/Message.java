/*  Author: Lauren Rolan
    Discipline: Technologies Java
 */

package RMI;

import java.io.Serializable;

/*Question: pourquoi la classe Message doit-elle implémenter l'interface java.io.Serializable ?
*  Parce que tout objet transmis entre deux programmes RMI doit être serializable, une fois
*  que la transmission est faite par recopie dans le cas des objets de classe.
* */

public class Message implements Serializable
{
    private String msg ;
    private String pseudo ;

    public Message(String pseudo,String msg)
    {
        this.pseudo = pseudo ; this.msg = msg ;
    }
    public String getPseudo () { return pseudo ; }
    public String getMessage () { return msg ; }
}

