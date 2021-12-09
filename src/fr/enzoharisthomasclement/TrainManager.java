package fr.enzoharisthomasclement;

import fr.enzoharisthomasclement.objects.*;
import fr.enzoharisthomasclement.threads.*;
import fr.enzoharisthomasclement.utils.LogUtils;
import fr.enzoharisthomasclement.utils.Pipe;
import fr.enzoharisthomasclement.utils.RandomUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainManager implements Runnable {

    // Liste des threads et runnables exécutés
    private List<Thread> threads;
    private List<Runnable> runnables;

    // Pipe de communication entre les threads
    private Pipe<Train> trainLineToNodePipe = new Pipe<>();
    private Pipe<Train> nodeToTrainLinePipe = new Pipe<>();
    private Pipe<Train> nodeToWayPipe = new Pipe<>();
    private Pipe<Train> wayToNodePipe = new Pipe<>();

    public TrainManager() throws IOException {
        this.threads = new ArrayList<>();
        this.runnables = new ArrayList<>();

        new Operator();

        this.runnables.add(new TrainLineToNodeRunnable(trainLineToNodePipe, nodeToWayPipe));
        this.runnables.add(new NodeToTrainLineRunnable(nodeToTrainLinePipe));
        this.runnables.add(new NodeToWayRunnable(nodeToWayPipe, nodeToTrainLinePipe));
        this.runnables.add(new WayToNodeRunnable(1, wayToNodePipe, nodeToTrainLinePipe));
        this.runnables.add(new WayToNodeRunnable(2, wayToNodePipe, nodeToTrainLinePipe));
        this.runnables.add(new WayToNodeRunnable(3, wayToNodePipe, nodeToTrainLinePipe));
    }

    @Override
    public void run() {
        System.out.println("Lancement des threads !");

        for(Runnable run : runnables) {
            Thread th = new Thread(run);
            th.start();
            threads.add(th);
            LogUtils.logThread("Lancement du thread #" + run.toString());
        }

        System.out.println(Operator.operator.toString());

        try {
            showMenu();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showMenu() throws IOException, InterruptedException {
        Scanner myInput = new Scanner(System.in);

        System.out.println("");
        System.out.println("Que voulez-vous faire ?");
        System.out.println("(1: Ajouter un train sur la ligne, 2: Sortir un train du garage, 3: Simuler une demande de sortie et d'entrée)");

        while(true){
            int action = myInput.nextInt();

            if(action == 1){
                System.out.println("");
                System.out.println("Ajout d'un train sur la ligne...");

                trainLineToNodePipe.write(new Train(RandomUtils.randomBetween(1, 100)));
            }

            if(action == 2){
                System.out.println("");
                System.out.println("Sur quelle voie voulez-vous sortir le train ? (1-3)");

                int way = myInput.nextInt();

                if(way == 1){
                    wayToNodePipe.write(Operator.operator.getWay1().getTrain());
                }else if (way == 2){
                    wayToNodePipe.write(Operator.operator.getWay2().getTrain());
                }else if (way == 3){
                    wayToNodePipe.write(Operator.operator.getWay3().getTrain());
                }
            }

            if(action == 3){
                System.out.println("");
                System.out.println("Simulation d'un conflit entrée / sortie...");

                wayToNodePipe.write(Operator.operator.getWay1().getTrain());
                trainLineToNodePipe.write(new Train(RandomUtils.randomBetween(1, 100)));
            }
        }
    }
}
