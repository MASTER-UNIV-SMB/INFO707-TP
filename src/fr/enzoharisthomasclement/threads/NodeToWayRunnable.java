package fr.enzoharisthomasclement.threads;

import fr.enzoharisthomasclement.objects.Operator;
import fr.enzoharisthomasclement.objects.Train;
import fr.enzoharisthomasclement.objects.Way;
import fr.enzoharisthomasclement.utils.LogUtils;
import fr.enzoharisthomasclement.utils.Pipe;
import fr.enzoharisthomasclement.utils.RandomUtils;

import java.io.IOException;

public class NodeToWayRunnable implements Runnable{

    private Pipe<Train> nodeToWayPipe;
    private Pipe<Train> nodeToTrainLinePipe;

    public NodeToWayRunnable(Pipe<Train> nodeToWayPipe, Pipe<Train> nodeToTrainLinePipe) {
        this.nodeToWayPipe = nodeToWayPipe;
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
                // Dans le cas ou le train doit quitter le noeud pour une voie de garage
                if(nodeToWayPipe.ready()){
                    Train t = nodeToWayPipe.read();

                    LogUtils.logOperateur("Vérification si une voie de garage est libre pour le " + t.toString());

                    int wayAvailable = Operator.operator.getFirstAvailableWay();

                    Thread.sleep(1000);

                    // Pas de voie disponible
                    if(wayAvailable == 0){
                        LogUtils.logOperateur("Aucune voie de garage libre pour " + t.toString() + ", renvoi sur la ligne !");
                        nodeToTrainLinePipe.write(t);
                    }else{
                        LogUtils.logNode(t.toString() + " se dirige vers la voie #" + wayAvailable + "...");

                        Thread.sleep(2000);

                        if(wayAvailable == 1){
                            Operator.operator.getWay1().setTrain(t);
                            LogUtils.logGarage(t.toString() + " est garé sur la voie #" + wayAvailable + " !");
                        }else if(wayAvailable == 2){
                            Operator.operator.getWay2().setTrain(t);
                            LogUtils.logGarage(t.toString() + " est garé sur la voie #" + wayAvailable + " !");
                        } else if(wayAvailable == 3){
                            Operator.operator.getWay3().setTrain(t);
                            LogUtils.logGarage(t.toString() + " est garé sur la voie #" + wayAvailable + " !");
                        }

                        System.out.println(Operator.operator.toString());
                    }
                }
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
