package org.icep.rlm;

import org.icep.rlm.core.Choice;
import org.icep.rlm.core.ChoiceList;
import org.icep.rlm.core.Subroutine;
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
        RLM learner = RLM.getInstance();
        // make 4 choices
        ChoiceList cl = new ChoiceList("root");
        List<String> actionName = Arrays.asList("up", "down", "left", "right");
        for (int i = 0; i < 4; i++) {
            TaxiEnvironment.Movement m = new TaxiEnvironment.Movement(actionName.get(i));
            Choice c = new Choice(Constants.choiceType.ACTION, m);
            cl.add(c);
        }
        learner.startNewEpisode();
        while(!learner.env.tf.isTerminal()) {
            learner.choose(cl);
        }
        System.out.println("Cum reward " + learner.totalReward);
    }
}
