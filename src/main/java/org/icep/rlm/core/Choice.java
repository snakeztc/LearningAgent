package org.icep.rlm.core;
import org.icep.rlm.RLM;
import org.icep.rlm.util.Constants;
import java.util.List;

/**
 * Created by Tony on 9/17/15.
 */
public class Choice {
    Constants.choiceType type;
    Action a;

    public Choice(Constants.choiceType type, Action a) {
        this.type = type;
        this.a = a;
    }
    public void execute() {
        if (type == Constants.choiceType.ACTION) {
            RLM.getInstance().action(a);
        } else if (type == Constants.choiceType.CALL) {
            RLM.getInstance().call((Subroutine) a);
        } else if (type == Constants.choiceType.CHOOSE) {
            RLM.getInstance().choose((ChoiceList) a);
        }
    }
}
