package fr.enzoharisthomasclement.objects;

import java.util.concurrent.locks.ReentrantLock;

public class Operator {

    public static Operator operator;

    private final TrainLine trainLine;
    private final Node node;
    private final Way way1;
    private final Way way2;
    private final Way way3;

    private final ReentrantLock lock = new ReentrantLock();

    public Operator() {
        this.trainLine = new TrainLine();
        this.node = new Node();
        this.way1 = new Way(1);
        this.way2 = new Way(2);
        this.way3 = new Way(3);
        operator = this;
    }

    // Methodes ThreadSafe

    public int getFirstAvailableWay() throws InterruptedException {
        while(lock.isLocked()){
            Thread.sleep(50);
        }

        lock.lock();

        try {
            if(way1.isAvailable()) return 1;
            if(way2.isAvailable()) return 2;
            if(way3.isAvailable()) return 3;

            return 0;
        } finally {
            lock.unlock();
        }
    }

    public TrainLine getTrainLine() throws InterruptedException {
        while(lock.isLocked()){
            Thread.sleep(50);
        }

        lock.lock();

        try {
            return this.trainLine;
        } finally {
            lock.unlock();
        }
    }

    public Node getNode() throws InterruptedException {
        while(lock.isLocked()){
            Thread.sleep(50);
        }

        lock.lock();

        try {
            return this.node;
        } finally {
            lock.unlock();
        }
    }

    public Way getWay1() throws InterruptedException {
        while(lock.isLocked()){
            Thread.sleep(50);
        }

        lock.lock();

        try {
            return this.way1;
        } finally {
            lock.unlock();
        }
    }

    public Way getWay2() throws InterruptedException {
        while(lock.isLocked()){
            Thread.sleep(50);
        }

        lock.lock();

        try {
            return this.way2;
        } finally {
            lock.unlock();
        }
    }

    public Way getWay3() throws InterruptedException {
        while(lock.isLocked()){
            Thread.sleep(50);
        }

        lock.lock();

        try {
            return this.way3;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        try{
            TrainLine trainLine = getTrainLine();
            Node node = getNode();
            Way way1 = getWay1();
            Way way2 = getWay2();
            Way way3 = getWay3();

            sb.append("\n");

            sb.append("---------------------------------------------\n");
            sb.append("\n");
            sb.append("Voie #1            Voie #2            Voie 3#\n");

            if(way1.getTrain() != null) sb.append("[" + way1.getTrain().toString() + "]");
            else sb.append("[Libre]");

            sb.append("            ");

            if(way2.getTrain() != null) sb.append("[" + way2.getTrain().toString() + "]");
            else sb.append("[Libre]");

            sb.append("            ");

            if(way3.getTrain() != null) sb.append("[" + way3.getTrain().toString() + "]");
            else sb.append("[Libre]");

            sb.append("\n\n\n");

            sb.append("                   Noeud                     \n");

            sb.append("                   ");
            if(node.getTrain() != null) sb.append("[" + node.getTrain().toString() + "]");
            else sb.append("[Libre]");

            sb.append("\n\n\n");

            sb.append("                   Ligne                     \n");

            sb.append("                   ");
            if(trainLine.getTrain() != null) sb.append("[" + trainLine.getTrain().toString() + "]");
            else sb.append("[Libre]");

            sb.append("\n");
            sb.append("---------------------------------------------\n");
            
            sb.append("(1: Ajouter, 2: Sortir, 3: Sortie et d'entrée) \n");
            

            return sb.toString();
        }catch (InterruptedException e){
            return "";
        }
    }
}
