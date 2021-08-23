package core.turn;

import core.actions.ActionWithDescription;
import core.actions.ActionWithDescriptionFactory;
import core.battlefield.Battlefield;
import core.battlefield.BattlefieldObject;
import core.utils.*;

import java.util.ArrayList;
import java.util.List;

public class TurnController {
    private static final int MAX_MANA = 10;
    private final Battlefield battlefield;

    public TurnController(Battlefield battlefield) {
        this.battlefield = battlefield;
    }

    public void beginTurn() {
        if (battlefield.getActivePlayer().getMaxMana() < MAX_MANA) {
            battlefield.getActivePlayer().incrementMaxMana();
        }
        battlefield.getActivePlayer().restoreMana();
        battlefield.getActivePlayer().drawCard();
        int selectedOption = -2;

        while (selectedOption != 1) {
            System.out.println("\n\n\n\n\n\n");
            System.out.println(battlefield);
            selectedOption = Selector.selectWithDescription(battlefield.getActivePlayer().getTurnOptions());
            if (selectedOption == 2) {
                this.battlefieldReview();
            }
            if (selectedOption != 1 && selectedOption != 2) {
                battlefield.getActivePlayer().playCardFromHand(selectedOption - 3);
            }
            if (!battlefield.getPlayerOne().isAlive()) {
                if (!battlefield.getPlayerTwo().isAlive()) {
                    System.out.println("Игра окончена! Ничья!");
                } else {
                    System.out.println("Игра окончена! Победил " + battlefield.getPlayerTwo().getName() + "!");
                }
                return;
            } else {
                if (!battlefield.getPlayerTwo().isAlive()) {
                    System.out.println("Игра окончена! Победил " + battlefield.getPlayerOne().getName() + "!");
                    return;
                }
            }
        }

        this.endTurn();
    }

    public void endTurn() {
        battlefield.swapActivePlayer();
    }

    public void battlefieldReview() {
        int selectedOption = -2;
        System.out.println("\n\n\n\n");
        System.out.println(battlefield);
        List<WithDescription> onFieldOptions = new ArrayList<>();
        onFieldOptions.add(() -> "Назад");
        onFieldOptions.addAll(battlefield.getActiveSide().getBattlefieldObjects());
        selectedOption = Selector.selectWithDescription(onFieldOptions);
        if (selectedOption == 1) {
            return;
        }
        BattlefieldObject selectedObject = battlefield.getActiveSide().get(selectedOption - 2);
        System.out.println("\n" + selectedObject.getMainView() + "\n");
        System.out.println(this.battlefield);
        int selectedInCardOption = -2;
        List<ActionWithDescription> onCardOptions = new ArrayList<>();
        onCardOptions.add(ActionWithDescriptionFactory.create(p -> null, "Назад"));
        onCardOptions.addAll(selectedObject.getActions());
        selectedInCardOption = Selector.selectWithDescription(onCardOptions);
        if (selectedInCardOption != 1) {
            onCardOptions.get(selectedInCardOption - 1).resolve(battlefield.getActivePlayer());
        }
    }
}
