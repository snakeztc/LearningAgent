package org.icep.rlm.core;

/**
 * Created by Tony on 9/19/15.
 */
public abstract class Action {
    protected String name;

    public Action(String name) {
        this.name = name;
    }
    public abstract void execute();
}
