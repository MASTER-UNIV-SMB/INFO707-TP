package fr.enzoharisthomasclement.threads;

import fr.enzoharisthomasclement.OperateurServer;
import fr.enzoharisthomasclement.objects.Train;
import fr.enzoharisthomasclement.objects.Voie;

import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.util.UUID;

public class OperateurSocketThread extends Thread {

    private int clientNumber;
    private Socket socketOfServer;

    public OperateurSocketThread(Socket socketOfServer, int clientNumber) {
        this.clientNumber = clientNumber;
        this.socketOfServer = socketOfServer;

        // Log
        System.out.println("Connexion du train # " + this.clientNumber + " sur l'opérateur " + socketOfServer);

        OperateurServer.plateforme.addTrain(new Train(UUID.randomUUID(), this.clientNumber));
    }

    @Override
    public void run() {
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));

            while (true) {
                String line = is.readLine();

                // If users send QUIT (To end conversation).
                if (line.equals("QUIT")) {
                    os.write(">> OK");
                    os.newLine();
                    os.flush();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
