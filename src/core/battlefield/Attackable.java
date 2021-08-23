package core.battlefield;

import core.utils.Selectable;
import core.utils.WithDescription;

public interface Attackable extends Selectable, WithDescription {
    void onTakeAttack(Attacker from, int amount);
    boolean isAttackable();
}
