package fr.enzoharisthomasclement.objects;

import java.io.Serializable;
import java.util.UUID;

public class Train implements Serializable {
    private static int index = 1;

    private int id;
    private int name;

    public Train(int name) {
        this.id = index++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Train #" + this.getId();
    }
}
