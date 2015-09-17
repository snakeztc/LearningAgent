package org.icep.rlm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Tony on 9/17/15.
 */
public class Subroutine {
    String label;
    List<Objects> arguments;

    public Subroutine (String label) {
        this.label = label;
        arguments = new ArrayList<Objects>();
    }

    public void start() {
        // default subroutine simple does nothing
        return;
    }
}
