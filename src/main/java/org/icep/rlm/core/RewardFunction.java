package org.icep.rlm.core;

/**
 * Created by Tony on 9/19/15.
 */
public interface RewardFunction {
    public abstract double reward(State s, Action a, State sPrime);
}
