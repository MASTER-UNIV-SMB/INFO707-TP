package fr.enzoharisthomasclement.threads;

import fr.enzoharisthomasclement.objects.Operator;
import fr.enzoharisthomasclement.objects.Train;
import fr.enzoharisthomasclement.utils.LogUtils;
import fr.enzoharisthomasclement.utils.Pipe;
import fr.enzoharisthomasclement.utils.RandomUtils;

import java.io.IOException;

public class WayToNodeRunnable implements Runnable{

    private int way;
    private Pipe<Train> wayToNodePipe;
    private Pipe<Train> nodeToTrainLinePipe;

    public WayToNodeRunnable(int way, Pipe<Train> wayToNodePipe, Pipe<Train> nodeToTrainLinePipe) {
        this.way = way;
        this.wayToNodePipe = wayToNodePipe;
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
                // Dans le cas ou le train doit quitter le garage pour la ligne
                if(wayToNodePipe.ready()){
                    Train t = wayToNodePipe.read();
                    // On retire le train de la voie
                    LogUtils.logGarage(t.toString() + " quitte la voie " + way + " pour se diriger vers le noeud ferroviaire");

                    if(way == 1){
                        Operator.operator.getWay1().setTrain(null);
                    }else if (way == 2){
                        Operator.operator.getWay2().setTrain(null);
                    }else if (way == 3){
                        Operator.operator.getWay3().setTrain(null);
                    }

                    Thread.sleep(2000);

                    LogUtils.logOperateur(t.toString() + " est arrivé sur le noeud ferroviaire");

                    // On envoi le train dans le node
                    Operator.operator.getNode().setTrain(t);
                    nodeToTrainLinePipe.write(t);

                    System.out.println(Operator.operator.toString());
                }
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                break;
            }
        }
    }
}
