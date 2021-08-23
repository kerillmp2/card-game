package core.actions;

import core.player.Player;

public class ActionWithDescriptionFactory {
    public static ActionWithDescription create(Action action, String description) {
        return new ActionWithDescription() {
            @Override
            public Player resolve(Player player) {
                return action.resolve(player);
            }

            @Override
            public String getNameWithDescription() {
                return description;
            }
        };
    }
}
