package core.battlefield;

import core.actions.ActionWithDescription;
import core.utils.Selectable;
import core.utils.WithDescription;

import java.util.List;

public interface BattlefieldObject extends Selectable, WithDescription, CanLeaveBattlefield {
    public String getBattlefieldView();
    public String getMainView();
    public List<ActionWithDescription> getActions();
    public boolean isAttackable();
    public boolean isDead();
}