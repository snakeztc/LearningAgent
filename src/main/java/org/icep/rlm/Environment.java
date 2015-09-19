package org.icep.rlm;

import com.sun.tools.doclint.Env;

/**
 * Created by Tony on 9/17/15.
 */
public abstract class Environment {

    public Environment() {
    }
    public abstract State getCurrentState();
    public abstract State applyAction(Object action);
    public abstract double getReward(State state);
    public abstract Boolean isTerminal(State state);
}
