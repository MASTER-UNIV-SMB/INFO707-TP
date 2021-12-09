package fr.enzoharisthomasclement;

import fr.enzoharisthomasclement.objects.Node;
import fr.enzoharisthomasclement.objects.Train;
import fr.enzoharisthomasclement.objects.TrainLine;
import fr.enzoharisthomasclement.objects.Way;
import fr.enzoharisthomasclement.threads.OperatorRunnable;
import fr.enzoharisthomasclement.utils.Pipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainManager implements Runnable {

    //
    private TrainLine trainLine;
    private Node node;
    private Way way1;
    private Way way2;
    private Way way3;

    // Liste des threads et runnables exécutés
    private List<Thread> threads;
    private List<Runnable> runnables;

    private Pipe<Train> trainLineToNodePipe = new Pipe<>();
    private Pipe<Train> nodeToTrainLinePipe = new Pipe<>();

    private Pipe<Train> nodeToWayPipe = new Pipe<>();
    private Pipe<Train> wayToNodePipe = new Pipe<>();

    public TrainManager() throws IOException {
        this.threads = new ArrayList<>();
        this.runnables = new ArrayList<>();
    }

    @Override
    public void run() {
        System.out.println("Lancement des threads !");

        // ---------------------

        while(true) {
            try {
                // Dans le cas ou le train doit passer au noeud.
                if(trainLineToNodePipe.ready()){
                    Train t = trainLineToNodePipe.read();
                    // On le traite sur le noeuds pour l'envoyer sur une voie
                    nodeToWayPipe.write(t);
                }

                // Dans le cas ou le train doit quitter le noeud pour une voie de garage
                if(nodeToWayPipe.ready()){
                    Train t = nodeToWayPipe.read();
                    // On envoi le train sur la voie libre
                    if(!way1.isAvailable() && !way2.isAvailable() && way3.isAvailable()){
                        // Pas de voie dispo on renvoi le train sur la grande ligne.
                        node.setTrain(null);
                        trainLine.setTrain(t);
                    }else{
                        if(way1.isAvailable()) {
                            way1.setTrain(t);
                            System.out.println(t.toString() + " garé sur " + way1);
                        } else if(way2.isAvailable()){
                            way2.setTrain(t);
                            System.out.println(t.toString() + " garé sur " + way2);
                        } else if(way3.isAvailable()){
                            way3.setTrain(t);
                            System.out.println(t.toString() + " garé sur " + way3);
                        }
                    }
                }

                // Dans le cas ou le train doit quitter le noeud pour la ligne
                if(nodeToTrainLinePipe.ready()){
                    Train t = nodeToTrainLinePipe.read();
                    // On envoi le train sur la ligne et on le supprime
                    node.setTrain(null);
                    trainLine.setTrain(t);
                }

                // Dans le cas ou le train doit quitter le garage pour la ligne
                if(wayToNodePipe.ready()){
                    Train t = wayToNodePipe.read();
                    // On envoi le train dans le node
                    node.setTrain(t);
                    nodeToTrainLinePipe.write(t);
                }

                Thread.sleep(150);
            }catch (ClassNotFoundException | IOException | InterruptedException e) {
                e.printStackTrace();
                for(Thread th : threads) {
                    th.interrupt();
                }
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
