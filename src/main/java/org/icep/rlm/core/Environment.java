package org.icep.rlm.core;

import com.sun.tools.doclint.Env;
import org.icep.rlm.core.State;

/**
 * Created by Tony on 9/17/15.
 */
public abstract class Environment {

    public TerminalFunction tf;
    public RewardFunction rf;
    public EnvState envState;

    public Environment() {

    }

    public Environment(EnvState s, TerminalFunction tf, RewardFunction rf) {
        this.tf = tf;
        this.rf = rf;
        envState = s;
    }
    public abstract State getCurrentState();
    public abstract State applyAction(Object action);
}
