package fr.enzoharisthomasclement;

import fr.enzoharisthomasclement.objects.Plateforme;
import fr.enzoharisthomasclement.objects.Voie;
import fr.enzoharisthomasclement.threads.OperateurSocketThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class OperateurServer {

    public static ServerSocket serverSocket = null;
    public static int clientNumber = 0;

    public static Plateforme plateforme;

    public static void main(String[] args) throws IOException {
        // Initialisation plateforme
        System.out.println("Lancement de la simulation de la Rotonde de Chambéry...");

        plateforme = new Plateforme("Rotonde Chambéry");

        plateforme.addVoie(new Voie(1));
        plateforme.addVoie(new Voie(2));
        plateforme.addVoie(new Voie(3));
        plateforme.addVoie(new Voie(4));

        // Socket

        System.out.println("Lancement du serveur de l'opérateur...");

        try {
            serverSocket = new ServerSocket(7777);

            System.out.println("L'opérateur est prêt à recevoir des instructions de la part des trains !");

            while (true) {
                Socket socketOfServer = serverSocket.accept();
                new OperateurSocketThread(socketOfServer, clientNumber++).start();
            }
        } finally {
            serverSocket.close();
        }
    }

}
