package org.icep.rlm;

import com.sun.tools.doclint.Env;
import org.icep.rlm.core.*;
import org.icep.rlm.auxillary.*;

/**
 * Created by Tony on 9/17/15.
 */
public class TaxiEnvironment extends Environment implements TerminalFunction{

    int mapHeight;
    int mapWidth;
    int px;
    int py;
    int destX;
    int destY;
    int maxStep;
    public int totalSteps;
    public String name;

    public class TaxiState extends EnvState {
        public TaxiState() {
            super();
            ObjectInstance x = new ObjectInstance("X", 0);
            addObject(x);
            ObjectInstance y = new ObjectInstance("Y", 0);
            addObject(y);
            ObjectInstance isOnCar = new ObjectInstance("isOnCar", false);
            addObject(isOnCar);
        }
    }

    public static class Movement extends Action {
        public Movement(String name) {
            super(name);
        }
        public void execute() {
            TaxiEnvironment env = (TaxiEnvironment)RLM.getInstance().env;
            env.applyAction(name);
        }

    }
    public TaxiEnvironment() {
        super();
        int size = 2;
        mapHeight = size;
        mapWidth = size;
        px = mapHeight-1;
        py = 0;
        destX = mapHeight-1;
        destY = mapHeight-1;
        envState = new TaxiState();
        rf = new UniformReward();
        tf = this;
        maxStep = size*10;
        totalSteps = 0;
        name = "Taxi";

    }

    @Override
    public TaxiState applyAction(Object action) {
        String a = (String) action;
        //check if the move is legal
        return applyLegalMove(a);
    }

    @Override
    public State getCurrentState() {
        return envState;
    }

    /* Helpers */
    TaxiState applyLegalMove(String a) {
        int curX = (Integer) envState.getValue("X");
        int curY = (Integer) envState.getValue("Y");
        Boolean isOnCar = (Boolean) envState.getValue("isOnCar");
        //check boundary
        if (a.equals("up") && curX - 1 >= 0) {
            envState.setValue("X", curX-1);
        } else if (a.equals("down") && curX + 1 <= mapHeight-1) {
            envState.setValue("X", curX+1);
        } else if (a.equals("left") && curY - 1 >= 0) {
            envState.setValue("Y", curY-1);
        } else if (a.equals("right") &&  curY+ 1 <= mapWidth-1) {
            envState.setValue("Y", curY+1);
        }
        // check if we should pick up passenger
        if (!isOnCar && curY == py && curX == px) {
            envState.setValue("isOnCar", true);
        }
        totalSteps++;
        return (TaxiState)envState;
    }

    public boolean isTerminal() {
        int curX = (Integer) envState.getValue("X");
        int curY = (Integer) envState.getValue("Y");
        Boolean isOnCar = (Boolean) envState.getValue("isOnCar");

        return totalSteps > maxStep || (isOnCar && curX == destX && curY == destY);
    }

}
