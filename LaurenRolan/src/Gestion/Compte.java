package Gestion;

import antlr.CppCodeGenerator;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Compte extends UnicastRemoteObject implements RMICompte {
    private RMIServeur server;

    protected Compte(int port) throws RemoteException {
        super(port);
        try {
            //System.setSecurityManager(new RMISecurityManager());
            server = (RMIServeur) Naming.lookup("rmi://localhost:2000/banque");
        }catch (Exception e) {
            System.out.println("Client failed: " + e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException {
        Compte client = new Compte(2001);
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

    @Override
    public void debiter(int compte, double montant) throws RemoteException{
        server.debiter(compte, montant);
    }

    @Override
    public void crediter(int compte, double montant) throws RemoteException {
        server.crediter(compte, montant);
    }

    @Override
    public double getSolde(int compte) throws RemoteException {
        return server.retourner(compte);
    }
}
