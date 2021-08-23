package core.battlefield;

import core.actions.Action;
import core.player.Player;

public interface CanLeaveBattlefield {
    Action onLeaveBattlefield(Player owner);
}
