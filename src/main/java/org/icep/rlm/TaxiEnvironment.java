package org.icep.rlm;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by Tony on 9/17/15.
 */
public class TaxiEnvironment extends Environment{

    public class TaxiState extends State{
        int mapHeight;
        int mapWidth;
        int x;
        int y;
        int px;
        int py;
        int destX;
        int destY;
        Boolean pOnCar;
    }

    TaxiState state;

    public TaxiEnvironment() {
        state = new TaxiState();
        state.mapHeight = 2;
        state.mapWidth = 2;
        state.x = 0;
        state.y = 0;
        state.px = state.mapHeight-1;
        state.py = state.mapHeight-1;
        state.destX = state.mapHeight-1;
        state.destY = 0;
        state.pOnCar = false;
    }

    @Override
    public TaxiState applyAction(Object action) {
        String a = (String) action;
        //check if the move is legal
        return applyLegalMove(state, a);
    }
    @Override
    public double getReward(State state) {
        TaxiState s = (TaxiState) state;
        if (s.pOnCar && s.x == s.destX && s.y == s.destY) {
            return 10;
        } else {
            return -1;
        }
    }

    @Override
    public Boolean isTerminal(State state) {
        TaxiState s = (TaxiState) state;
        if (s.pOnCar && s.x == s.destX && s.y == s.destY) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public State getCurrentState() {
        return state;
    }

    /* Helpers */
    TaxiState applyLegalMove(TaxiState s, String a) {
        //check boundary
        if (a.equals("up") && s.x - 1 >= 0) {
            s.x--;
        } else if (a.equals("down") && s.x + 1 <= s.mapHeight-1) {
            s.x++;
        } else if (a.equals("left") && s.y - 1 >= 0) {
            s.y--;
        } else if (a.equals("right") && s.y + 1 <= s.mapWidth-1) {
            s.y++;
        }
        // check if we should pick up passenger
        if (!s.pOnCar && s.y == s.py && s.x == s.px) {
            s.pOnCar = true;
        }
        return s;
    }

}
