package core.actions;

import core.player.Player;

public class ActionWithDescriptionFactory {
    public static ResolvableWithDescription create(Resolvable resolvable, String description) {
        return new ResolvableWithDescription() {
            @Override
            public Player resolve(Player player) {
                return resolvable.resolve(player);
            }

            @Override
            public String getNameWithDescription() {
                return description;
            }
        };
    }
}
