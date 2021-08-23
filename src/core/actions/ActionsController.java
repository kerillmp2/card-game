package core.actions;

import core.battlefield.Battlefield;
import core.battlefield.BattlefieldObject;

import java.util.List;
import java.util.Stack;

public class ActionsController {
    private static final int STACK_LIMIT = 1000;
    private static final int ITERATIONS_LIMIT = 1500;
    private final Stack<Resolvable> resolvableStack = new Stack<>();
    private final Battlefield battlefield;
    private int size = 0;

    public ActionsController(Battlefield battlefield) {
        this.battlefield = battlefield;
    }

    public void addAction(Resolvable resolvable) {
        if (size >= STACK_LIMIT) {
            return;
        }
        this.resolvableStack.add(resolvable);
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
qwe
    }

    public void resolveNextOnActivePlayer() {
        this.resolvableStack.pop().resolve(battlefield.getActivePlayer());
        afterActionResolve();
        size -= 1;
    }

    public void resolveNextOnOpponent() {
        this.resolvableStack.pop().resolve(battlefield.getOpponent());
        afterActionResolve();
        size -= 1;
    }

    public void resolveAllOnActivePlayer() {
        int stackOverflow = 0;
        while (!resolvableStack.empty()) {
            resolveNextOnActivePlayer();
            stackOverflow += 1;
            if (stackOverflow >= ITERATIONS_LIMIT) {
                return;
            }
        }
    }

    public void resolveAllOnOpponent() {
        int stackOverflow = 0;
        while (!resolvableStack.empty()) {
            resolveNextOnOpponent();
            stackOverflow += 1;
            if (stackOverflow >= ITERATIONS_LIMIT) {
                return;
            }
        }
    }
}
