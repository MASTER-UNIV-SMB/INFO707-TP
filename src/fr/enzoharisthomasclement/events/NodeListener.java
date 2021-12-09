package fr.enzoharisthomasclement.events;

import fr.enzoharisthomasclement.objects.Node;

public interface NodeListener {

    void onTrainEnterNode(Node node);
    void onTrainLeaveNode(Node node);

}
