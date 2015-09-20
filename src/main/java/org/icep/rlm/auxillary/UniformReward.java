package org.icep.rlm.auxillary;

import org.icep.rlm.core.Action;
import org.icep.rlm.core.RewardFunction;
import org.icep.rlm.core.State;

/**
 * Created by Tony on 9/19/15.
 */
public class UniformReward implements RewardFunction{
    public double reward(State s, Action a, State sPrime) {
        return -1;
    }
}
