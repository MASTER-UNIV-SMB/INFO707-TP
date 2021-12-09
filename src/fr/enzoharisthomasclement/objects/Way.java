package fr.enzoharisthomasclement.objects;

import java.io.Serializable;

public class Way implements Serializable {

    private int numeroVoie;
    private Train train;

    public Way(int numeroVoie) {
        this.numeroVoie = numeroVoie;
    }

    public int getNumeroVoie() {
        return numeroVoie;
    }

    public void setNumeroVoie(int numeroVoie) {
        this.numeroVoie = numeroVoie;
    }

    public Train getTrain() {
        return train;
    }

    public boolean isAvailable() {
        return train == null;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public String toString() {
        return "Voie{" +
                "numeroVoie=" + numeroVoie +
                ", train=" + train +
                '}';
    }
}
