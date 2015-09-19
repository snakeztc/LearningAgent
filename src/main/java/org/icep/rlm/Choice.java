package org.icep.rlm;
import org.icep.rlm.util.Constants;
import java.util.List;

/**
 * Created by Tony on 9/17/15.
 */
public class Choice {
    Constants.choiceType type;
    Object content;
    public void start() {
        if (type == Constants.choiceType.ACTION) {
            RLM.getInstance().action(content);
        } else if (type == Constants.choiceType.CALL) {
            RLM.getInstance().call((Subroutine)content);
        } else if (type == Constants.choiceType.CHOOSE) {
            RLM.getInstance().choose((List<Choice>) content);
        }
    }
}
