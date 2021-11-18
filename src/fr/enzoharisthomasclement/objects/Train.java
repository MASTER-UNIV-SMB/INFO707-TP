package fr.enzoharisthomasclement.objects;

import java.util.UUID;

public class Train {

    private UUID id;
    private int name;

    public Train(UUID id, int name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
