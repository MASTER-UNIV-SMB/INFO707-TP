package fr.enzoharisthomasclement.threads;

import fr.enzoharisthomasclement.objects.Node;
import fr.enzoharisthomasclement.objects.Train;
import fr.enzoharisthomasclement.objects.TrainLine;
import fr.enzoharisthomasclement.objects.Way;
import fr.enzoharisthomasclement.utils.Pipe;

import java.io.IOException;
import java.util.List;

public class OperatorRunnable implements Runnable{

    private Pipe<Train> trainLineToNodePipe;
    private Pipe<Train> nodeToTrainLinePipe;
    private Pipe<Train> nodeToWayPipe;
    private Pipe<Train> wayToNodePipe;

    public OperatorRunnable(Pipe<Train> trainLineToNodePipe, Pipe<Train> nodeToTrainLinePipe, Pipe<Train> nodeToWayPipe, Pipe<Train> wayToNodePipe) {
        this.trainLineToNodePipe = trainLineToNodePipe;
        this.nodeToTrainLinePipe = nodeToTrainLinePipe;
        this.nodeToWayPipe = nodeToWayPipe;
        this.wayToNodePipe = wayToNodePipe;
    }

    @Override
    public void run() {
        while(true) {
            if (Thread.interrupted()) {
                Thread.currentThread().interrupt();
                break;
            }

            try{
                if(trainLineToNodePipe.ready()){
                    Train t = trainLineToNodePipe.read();

                }


            }catch (IOException | ClassNotFoundException e){

            }
        }
    }
}
