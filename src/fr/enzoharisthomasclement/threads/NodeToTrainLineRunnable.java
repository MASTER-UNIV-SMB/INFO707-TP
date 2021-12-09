package fr.enzoharisthomasclement.threads;

import fr.enzoharisthomasclement.objects.Operator;
import fr.enzoharisthomasclement.objects.Train;
import fr.enzoharisthomasclement.utils.LogUtils;
import fr.enzoharisthomasclement.utils.Pipe;
import fr.enzoharisthomasclement.utils.RandomUtils;
import sun.rmi.runtime.Log;

import java.io.IOException;

public class NodeToTrainLineRunnable implements Runnable{

    private Pipe<Train> nodeToTrainLinePipe;

    public NodeToTrainLineRunnable(Pipe<Train> nodeToTrainLinePipe) {
        this.nodeToTrainLinePipe = nodeToTrainLinePipe;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                Thread.currentThread().interrupt();
                break;
            }

            try {
                // Dans le cas ou le train doit quitter le noeud pour la ligne
                if(nodeToTrainLinePipe.ready()){
                    Train t = nodeToTrainLinePipe.read();
                    // On envoi le train sur la ligne et on le supprime

                    LogUtils.logNode(t.toString() + " quitte le noeud pour se rendre sur la voie principale.");
                    Thread.sleep(1000);
                    Operator.operator.getNode().setTrain(null);
                    LogUtils.logTrainLine(t.toString() + " est arrivé sur la voie principale et est parti !");

                    System.out.println(Operator.operator.toString());
                }
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
