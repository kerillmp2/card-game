package core.battlefield;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BattlefieldSide {
    private List<BattlefieldObject> battlefieldObjects = new ArrayList<>();

    public BattlefieldSide addObject(BattlefieldObject battlefieldObject) {
        this.battlefieldObjects.add(battlefieldObject);
        return this;
    }

    public BattlefieldSide removeObject(BattlefieldObject battlefieldObject) {
        this.battlefieldObjects.remove(battlefieldObject);
        return this;
    }

    public BattlefieldSide removeObject(int index) {
        if(index < this.battlefieldObjects.size()) {
            this.battlefieldObjects.remove(index);
        }
        return this;
    }

    public BattlefieldObject get(int index) {
        if(index < this.battlefieldObjects.size()) {
            return this.battlefieldObjects.get(index);
        }
        return null;
    }

    public List<BattlefieldObject> getBattlefieldObjects() {
        return battlefieldObjects;
    }

    public void set(Collection<? extends BattlefieldObject> collection) {
        this.clear();
        this.addAll(collection);
    }

    public void clear() {
        this.battlefieldObjects.clear();
    }

    public void addAll(Collection<? extends BattlefieldObject> collection) {
        this.battlefieldObjects.addAll(collection);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        StringBuilder row = new StringBuilder();
        for(BattlefieldObject bf : this.battlefieldObjects) {
            if (row.length() + bf.getBattlefieldView().length() >= 44) {
                s.append("\n").append(row);
                row = new StringBuilder();
            }
            row.append(bf.getBattlefieldView()).append(" ");
        }
        s.append("\n").append(row);
        return s.toString();
    }
}
