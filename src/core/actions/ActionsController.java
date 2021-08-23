package core.actions;

import core.battlefield.Battlefield;
import core.battlefield.BattlefieldObject;

import java.util.List;
import java.util.Stack;

public class ActionsController {
    private static final int STACK_LIMIT = 1000;
    private static final int ITERATIONS_LIMIT = 1500;
    private final Stack<Action> actionStack = new Stack<>();
    private final Battlefield battlefield;
    private int size = 0;

    public ActionsController(Battlefield battlefield) {
        this.battlefield = battlefield;
    }

    public void addAction(Action action) {
        if (size >= STACK_LIMIT) {
            return;
        }
        this.actionStack.add(action);
        size += 1;
    }

    public void afterActionResolve() {
        List<BattlefieldObject> activeSideCorpses = battlefield.collectActiveSideCorpses();
        List<BattlefieldObject> opponentSideCorpses = battlefield.collectOpponentSideCorpses();

        for(BattlefieldObject corpse : activeSideCorpses) {
            this.addAction(corpse.onLeaveBattlefield(battlefield.getActivePlayer()));
        }
        for(BattlefieldObject corpse : opponentSideCorpses) {
            this.addAction(corpse.onLeaveBattlefield(battlefield.getOpponent()));
        }
        this.re
    }

    public void resolveNextOnActivePlayer() {
        this.actionStack.pop().resolve(battlefield.getActivePlayer());
        afterActionResolve();
        size -= 1;
    }

    public void resolveNextOnOpponent() {
        this.actionStack.pop().resolve(battlefield.getOpponent());
        afterActionResolve();
        size -= 1;
    }

    public void resolveAllOnActivePlayer() {
        int stackOverflow = 0;
        while (!actionStack.empty()) {
            resolveNextOnActivePlayer();
            stackOverflow += 1;
            if (stackOverflow >= ITERATIONS_LIMIT) {
                return;
            }
        }
    }

    public void resolveAllOnOpponent() {
        int stackOverflow = 0;
        while (!actionStack.empty()) {
            resolveNextOnOpponent();
            stackOverflow += 1;
            if (stackOverflow >= ITERATIONS_LIMIT) {
                return;
            }
        }
    }
}
