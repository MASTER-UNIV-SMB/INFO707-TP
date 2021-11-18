package fr.enzoharisthomasclement;

import java.io.*;
import java.net.Socket;

public class TrainClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7777);
        System.out.println("SOCKET = " + socket);

        BufferedReader plec = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        PrintWriter pred = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())),
                true);

        String str = "bonjour";
        for (int i = 0; i < 10; i++) {
            pred.println(str);          // envoi d'un message
            str = plec.readLine();      // lecture de l'écho
        }
        System.out.println("END");     // message de terminaison
        pred.println("END") ;
        plec.close();
        pred.close();
        socket.close();
    }

}
