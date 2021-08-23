package core.actions;
import core.player.Player;

public interface Action {
    public Player resolve(Player player);
}
