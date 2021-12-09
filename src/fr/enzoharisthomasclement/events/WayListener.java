package fr.enzoharisthomasclement.events;

import fr.enzoharisthomasclement.objects.Node;
import fr.enzoharisthomasclement.objects.Way;

public interface WayListener {

    void onTrainEnterWay(Way way);
    void onTrainLeaveWay(Way way);

}
