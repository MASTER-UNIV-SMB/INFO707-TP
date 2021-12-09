package fr.enzoharisthomasclement.threads;

import fr.enzoharisthomasclement.objects.Operator;
import fr.enzoharisthomasclement.objects.Train;
import fr.enzoharisthomasclement.utils.LogUtils;
import fr.enzoharisthomasclement.utils.Pipe;
import fr.enzoharisthomasclement.utils.RandomUtils;

import java.io.IOException;

public class TrainLineToNodeRunnable implements Runnable{

    private Pipe<Train> trainLineToNodePipe;
    private Pipe<Train> nodeToWayPipe;

    public TrainLineToNodeRunnable(Pipe<Train> trainLineToNodePipe, Pipe<Train> nodeToWayPipe) {
        this.trainLineToNodePipe = trainLineToNodePipe;
        this.nodeToWayPipe = nodeToWayPipe;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                Thread.currentThread().interrupt();
                break;
            }

            try {
                // Dans le cas ou le train doit passer au noeud.
                if(trainLineToNodePipe.ready()){
                    Train t = trainLineToNodePipe.read();

                    LogUtils.logTrainLine("Le " + t.toString() + " est actuellement sur la ligne.");
                    LogUtils.logOperateur("Le " + t.toString() + " se dirige vers le noeud ferroviaire.");

                    Thread.sleep(2000);

                    System.out.println(Operator.operator.toString());

                    // On le traite sur le noeuds pour l'envoyer sur une voie
                    nodeToWayPipe.write(t);
                }
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
