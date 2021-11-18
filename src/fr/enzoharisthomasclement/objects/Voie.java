package fr.enzoharisthomasclement.objects;

public class Voie {

    private int numeroVoie;
    private Train train;

    public Voie(int numeroVoie) {
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

    public void setTrain(Train train) {
        this.train = train;
    }
}
