package core.battlefield;

import core.actions.Resolvable;
import core.actions.ResolvableWithDescription;
import core.actions.ActionWithDescriptionFactory;
import core.player.Player;
import core.utils.*;

import java.util.ArrayList;
import java.util.List;

public class BattlefieldCreature implements BattlefieldObject, Attackable, Attacker {

    private String name;
    private int maxHp;
    private int currentHp;
    private int currentAttack;
    private int attack;
    private boolean isAttacker;
    private boolean isAttackable;
    private final List<ResolvableWithDescription> actions;

    public BattlefieldCreature(
            String name,
            int maxHp,
            int currentHp,
            int attack,
            boolean isAttackable,
            boolean isAttacker,
            List<ResolvableWithDescription> actions) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.currentAttack = attack;
        this.attack = attack;
        this.isAttackable = isAttackable;
        this.isAttacker = isAttacker;
        this.actions = actions;
        if (isAttacker) {
            addAttackAction();
        }
    }

    @Override
    public List<ResolvableWithDescription> getActions() {
        return actions;
    }

    @Override
    public String getBattlefieldView() {
        return "[" + name + " " + currentAttack + "/" + currentHp + "]";
    }

    @Override
    public String getMainView() {
        StringBuilder view = new StringBuilder("-----------------\n");
        view.append("| ").append(name);
        view.append(" ".repeat(Math.max(0, 15 - (1 + name.length()))));
        view.append("|\n");
        view.append("|---------------|\n");
        view.append("|               |\n");
        view.append("|               |\n");
        view.append("|               |\n");
        view.append("|               |\n");
        view.append("| ").append(this.currentAttack);
        view.append(" ".repeat(Math.max(0, 15 - (2 + Calculator.digits(currentAttack) + Calculator.digits(currentHp)))));
        view.append(this.currentHp).append(" |\n");
        view.append("-----------------\n");
        return view.toString();
    }

    @Override
    public void onTakeAttack(Attacker from, int amount) {
        if (isAttackable) {
            System.out.println(this.name + " получает " + amount + " урона!\n");
            this.currentHp -= amount;
        }
    }

    @Override
    public void onAttack(Attackable to, int amount) {
        if (isAttacker) {
            System.out.println(this.name + " атакует " + to.getName() + "!\n");
        }
    }

    @Override
    public Resolvable onLeaveBattlefield(Player owner) {
        return player -> {
            System.out.println("Существо " + name + " покинуло поле");
            return player;
        };
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getNameWithDescription() {
        return this.name + "[" + "Существо " + currentAttack + "/" + currentHp + "]";
    }

    public int getAttack() {
        return attack;
    }

    public boolean isAttacker() {
        return isAttacker;
    }

    @Override
    public boolean isAttackable() {
        return isAttackable;
    }

    @Override
    public boolean isDead() {
        return this.currentHp <= 0;
    }

    public void addAction(ResolvableWithDescription action) {
        this.actions.add(action);
    }

    private void addAttackAction() {
        this.actions.add(ActionWithDescriptionFactory.create(player -> {
            if (!this.isAttacker) {
                System.out.println(this.name + " не может атаковать!\n");
                return player;
            }
            Player opponent = player.getBattlefield().getOpponent(player);
            int selectedOption = -2;
            while (true) {
                System.out.println("Выберите цель, чтобы атаковать на " + currentAttack);
                List<Attackable> targets = new ArrayList<>();
                targets.add(new Attackable() {
                    @Override
                    public void onTakeAttack(Attacker from, int amount) {
                    }

                    @Override
                    public String getName() {
                        return "Назад";
                    }

                    @Override
                    public boolean isAttackable() {
                        return false;
                    }

                    @Override
                    public String getNameWithDescription() {
                        return "Назад";
                    }
                });
                targets.addAll(player.getBattlefield().getAttackTargets());
                selectedOption = Selector.selectWithDescription(targets);
                if (selectedOption == 1) {
                    break;
                } else {
                    Attackable target = targets.get(selectedOption - 1);
                    if (target.isAttackable()) {
                        int attackDamage = this.currentAttack;
                        this.onAttack(target, attackDamage);
                        target.onTakeAttack(this, attackDamage);
                        break;
                    } else {
                        System.out.println("Невозможно атаковать цель " + target.getNameWithDescription() + "\n");
                    }
                }
            }
            return player;
        }, "Атака"));
    }
}