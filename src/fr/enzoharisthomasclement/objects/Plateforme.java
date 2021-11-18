package fr.enzoharisthomasclement.objects;

import java.util.ArrayList;
import java.util.List;

public class Plateforme {

    private String name;
    private List<Voie> voies;
    private Train noeudTrain;
    private List<Train> trains;

    public Plateforme(String name) {
        this.name = name;
        this.voies = new ArrayList<>();
        this.trains = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Voie> getVoies() {
        return voies;
    }

    public void setVoies(List<Voie> voies) {
        this.voies = voies;
    }

    public void addVoie(Voie v){
        this.voies.add(v);
    }

    public Train getNoeudTrain() {
        return noeudTrain;
    }

    public void setNoeudTrain(Train noeudTrain) {
        this.noeudTrain = noeudTrain;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public void addTrain(Train t){
        this.trains.add(t);
    }

}
