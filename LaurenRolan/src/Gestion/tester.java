package Gestion;

import java.rmi.RemoteException;
import java.util.Scanner;

public class tester {
    private Compte client = new Compte(2001);
    private RMIServeur server;

    public tester() throws RemoteException {
    }

    public static void main(String[] args) {
        direct();
    }

     void RMI() throws RemoteException {
        Scanner in = new Scanner(System.in);
        System.out.print("Saisissez votre numéro de compte : ");
        int compte = in.nextInt();
        String command = "a";
        while(!command.equals("q")) {
             System.out.println("Que souhaitez vous faire : ");
             System.out.println("a : Afficher le solde");
             System.out.println("c <montant> : Créditer le compte de montant");
             System.out.println("d <montant> : Débiter le compte de montant");
             System.out.println("q : Quitter l'application");
             command = in.nextLine();
             if(command.equals("a")) {
                 System.out.println(client.getSolde(compte));
             } else if(command.contains("c")) {
                 client.crediter(compte, Double.parseDouble(command.substring(1)));
             } else if(command.contains("d")) {
                 client.debiter(compte, Double.parseDouble(command.substring(1)));
             }
         }
    }

    static void direct() {
        GestionBase gb = new GestionBase();
        Scanner in = new Scanner(System.in);
        System.out.print("Saisissez votre numéro de compte : ");
        int compte = in.nextInt();
        String command = "a";
        while(!command.equals("q")) {
            System.out.println("Que souhaitez vous faire : ");
            System.out.println("a : Afficher le solde");
            System.out.println("c <montant> : Créditer le compte de montant");
            System.out.println("d <montant> : Débiter le compte de montant");
            System.out.println("q : Quitter l'application");
            command = in.nextLine();
            if(command.equals("a")) {
                System.out.println(gb.retourner(compte));
            } else if(command.contains("c")) {
                gb.crediter(compte, Double.parseDouble(command.substring(1)));
            } else if(command.contains("d")) {
                gb.debiter(compte, Double.parseDouble(command.substring(1)));
            }
        }
    }

}
