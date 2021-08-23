package core.battlefield;

import core.utils.Selectable;

public interface Attacker extends Selectable {
    void onAttack(Attackable to, int amount);
}
