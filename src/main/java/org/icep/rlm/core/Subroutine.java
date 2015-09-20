package org.icep.rlm.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Tony on 9/17/15.
 */
public abstract class Subroutine extends Action{

    // a list of input arguments into the subroutine
    List<Objects> parameters;

    public Subroutine (String name) {
        super(name);
        parameters = new ArrayList<Objects>();
    }
}
