package core.battlefield;

import java.util.ArrayList;

public class BattlefieldCreatureFactory {

    public static BattlefieldCreature create(String name, int attack, int hp) {
        return new BattlefieldCreature(name, hp, hp, attack, true, true, new ArrayList<>());
    }
}
