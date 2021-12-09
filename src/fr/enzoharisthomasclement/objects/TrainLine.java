package fr.enzoharisthomasclement.objects;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

public class TrainLine implements Serializable {

    private Train train;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public String toString() {
        return "TrainLine{" +
                "train=" + train +
                '}';
    }
}
