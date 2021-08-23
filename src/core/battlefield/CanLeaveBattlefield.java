package core.battlefield;

import core.actions.Resolvable;
import core.player.Player;

public interface CanLeaveBattlefield {
    Resolvable onLeaveBattlefield(Player owner);
}
