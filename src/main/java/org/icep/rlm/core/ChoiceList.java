package org.icep.rlm.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Tony on 9/19/15.
 */
public class ChoiceList extends Action{
    // a list of input arguments into the subroutine
    List<Choice> choices;

    public ChoiceList (String name) {
        super(name);
        choices = new ArrayList<Choice>();
    }
    public void add(Choice c) {
        choices.add(c);
    }
    public Choice get(int index) {
        return choices.get(index);
    }
    public int size() {
        return choices.size();
    }

    private Choice makeChoice(List<Choice> choices) {
        Random rand = new Random();
        int listSize = choices.size();
        int selectIndex = rand.nextInt(listSize-1 - 0 + 1) + 0;
        return choices.get(selectIndex);
    }

    public void execute() {
        Choice decision = makeChoice(choices);
        decision.execute();
    }
}
