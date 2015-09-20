package org.icep.rlm.core;

import com.sun.tools.doclint.Env;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tony on 9/19/15.
 */
public class EnvState implements State{

    protected List<ObjectInstance> stateObjects;
    protected Map<String, Object> valueMap;

    public EnvState() {
        stateObjects = new ArrayList<ObjectInstance>();
        valueMap = new HashMap<String, Object>();
    }


    public void addObject(ObjectInstance o) {
        stateObjects.add(o);
        valueMap.put(o.name, o.value);
    }

    public void removeObject(ObjectInstance o) {
        stateObjects.remove(o);
        valueMap.remove(o.name);
    }

    public Object getValue(String name) {
        return valueMap.get(name);
    }

    public void setValue(String name, Object value) {
        valueMap.put(name, value);
    }
}
