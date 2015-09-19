package org.icep.rlm;

import java.util.List;
import java.util.Objects;
import java.util.Stack;
import org.icep.rlm.util.Constants;
import java.util.Random;

/**
 * Created by Tony on 9/17/15.
 */
public class RLM {

    private static volatile RLM instance = null;
    private Constants.pcState programCounter;
    private Stack<ChoiceFrame> choiceStack;
    long numSteps;
    long numEpisodes;
    Environment env;
    double lambda;
    double totalReward;

    protected RLM() {
        numSteps = 0;
        numEpisodes = 0;
        choiceStack = new Stack<ChoiceFrame>();
        programCounter = Constants.pcState.INTERNAL;
        env = new TaxiEnvironment();
        lambda = 0.9;
        totalReward = 0;
    }

    private Choice makeChoice(List<Choice> choices) {
        Random rand = new Random();
        int listSize = choices.size();
        int selectIndex = rand.nextInt(listSize-1 - 0 + 1) + 0;
        return choices.get(selectIndex);
    }


    /* Public methods */

    public static RLM getInstance() {
        if (instance == null) {
            instance = new RLM();
        }
        return instance;
    }

    public void startNewEpisode() {
        numSteps = 0;
        choiceStack.clear();
        programCounter = Constants.pcState.INTERNAL;
        env = new TaxiEnvironment();
        numEpisodes++;
        totalReward = 0;
    }

    public void action(Object a) {
        programCounter = Constants.pcState.ACTION;
        State s = this.env.applyAction(a);
        double r = this.env.getReward(s);
        totalReward += r;
        programCounter = Constants.pcState.ACTION_EXIT;
        numSteps++;
    }

    public void call(Subroutine subroutine) {
        programCounter = Constants.pcState.CALL;
        // TODO do something here
        programCounter = Constants.pcState.CALL_EXIT;

    }

    public void choose(List<Choice> choices) {
        programCounter = Constants.pcState.CHOOSE;
        // make a decision
        Choice decision = this.makeChoice(choices);
        decision.start();
        programCounter = Constants.pcState.CHOOSE_EXIT;
    }
}
