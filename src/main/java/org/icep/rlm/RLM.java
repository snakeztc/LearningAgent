package org.icep.rlm;

import java.util.Stack;

import org.icep.rlm.core.*;
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

    public void learnPolicy(Choice c) {
        ChoiceFrame cf = new ChoiceFrame(c, env.getCurrentState());
        // first encounter
        if (choiceStack.isEmpty()) {
            choiceStack.push(cf);
            return;
        }
        ChoiceFrame pcf = choiceStack.pop();
        Double tdTarget = discountReward;
        // update the q value of previous choice point
        if (c != null) {
            tdTarget = discountReward + Math.pow(lambda, numSteps)*qTable.getQValue(cf.state, cf.choice.content);
        }
        Double tdError = tdTarget - qTable.getQValue(pcf.state, pcf.choice.content);
        Double newQv = qTable.getQValue(pcf.state, pcf.choice.content) + learningRate /numEpisodes * tdError;
        qTable.putQValue(pcf.state, pcf.choice.content, newQv);
        // reset
        choiceStack.push(cf);
        numSteps = 0;
        discountReward = 0;
    }

    public void action(Action a) {
        programCounter = Constants.pcState.ACTION;
        a.execute();
        TaxiEnvironment.TaxiState s = (TaxiEnvironment.TaxiState)this.env.getCurrentState();
        double r = this.env.rf.reward(s, a, null);
        totalReward += r;

        int curX = (Integer) s.getValue("X");
        int curY = (Integer) s.getValue("Y");
        Boolean isOnCar = (Boolean) s.getValue("isOnCar");
        System.out.println(curX + "_" + curY + "_" + isOnCar );
        programCounter = Constants.pcState.ACTION_EXIT;
        numSteps++;
    }

    public void call(Subroutine subroutine) {
        programCounter = Constants.pcState.CALL;
        subroutine.execute();
        programCounter = Constants.pcState.CALL_EXIT;

    }

    public void choose(ChoiceList choices) {
        programCounter = Constants.pcState.CHOOSE;
        choices.execute();
        programCounter = Constants.pcState.CHOOSE_EXIT;
    }
}
