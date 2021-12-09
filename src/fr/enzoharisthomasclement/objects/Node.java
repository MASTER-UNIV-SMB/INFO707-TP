package fr.enzoharisthomasclement.objects;

import java.io.Serializable;

public class Node implements Serializable {

    private Train train;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public String toString() {
        return "Noeud{" +
                "train=" + train +
                '}';
    }
}
