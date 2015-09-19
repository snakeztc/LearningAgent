package org.icep.rlm;

import org.icep.rlm.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Subroutine navigation = new Subroutine("navi");
        // make 4 choices
        List<Choice> cl = new ArrayList<Choice>();
        List<String> actionName = Arrays.asList("up", "down", "left", "right");
        for (int i = 0; i < 4; i++) {
            Choice c = new Choice();
            c.type = Constants.choiceType.ACTION;
            c.content = actionName.get(i);
            cl.add(c);
        }
        RLM learner = RLM.getInstance();
        learner.startNewEpisode();
        while(!learner.env.isTerminal(learner.env.getCurrentState())) {
            learner.choose(cl);
            TaxiEnvironment.TaxiState s = (TaxiEnvironment.TaxiState)learner.env.getCurrentState();
            System.out.println("x is " + s.x + " y is " + s.y + " with cum reward " + learner.totalReward);
        }
    }
}
