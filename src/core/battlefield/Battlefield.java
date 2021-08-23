package core.battlefield;

import core.player.Player;
import core.player.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Battlefield {
    private Player playerOne;
    private Player playerTwo;
    private BattlefieldSide firstSide = new BattlefieldSide();
    private BattlefieldSide secondSide = new BattlefieldSide();
    private BattlefieldSide activeSide = firstSide;
    private BattlefieldSide opponentSide = secondSide;
    private Player activePlayer;
    private Player opponent;

    public Battlefield(Player p1, Player p2) {
        this.playerOne = p1;
        p1.setBattlefield(this);
        this.playerTwo = p2;
        p2.setBattlefield(this);
        activePlayer = p1;
        opponent = p2;
    }

    public List<Attackable> getAttackTargets() {
        List<Attackable> attackTargets = new ArrayList<>();
        attackTargets.add(this.getOpponent().getFace());
        attackTargets.addAll(this.getOpponentSide().getBattlefieldObjects().stream()
                .filter(obj -> obj instanceof Attackable)
                .filter(BattlefieldObject::isAttackable)
                .map(attackable -> (Attackable) attackable)
                .collect(Collectors.toList()));
        return attackTargets;
    }

    public Player swapActivePlayer() {
        this.activePlayer = opponent;
        this.opponent = this.getOpponent(activePlayer);
        BattlefieldSide lastActive = this.activeSide;
        this.activeSide = this.opponentSide;
        this.opponentSide = lastActive;
        return this.activePlayer;
    }

    public Player getOpponent(Player player) {
        if(player == playerOne) {
            return playerTwo;
        }
        if(player == playerTwo) {
            return playerOne;
        }
        return player;
    }

    public void afterAction() {

    }

    public List<BattlefieldObject> collectActiveSideCorpses() {
        List <BattlefieldObject> alive = new ArrayList<>();
        List <BattlefieldObject> corpses = new ArrayList<>();
        for(BattlefieldObject battlefieldObject : this.getActiveSide().getBattlefieldObjects()) {
            if (battlefieldObject.isDead()) {
                corpses.add(battlefieldObject);
            } else {
                alive.add(battlefieldObject);
            }
        }
        this.getActiveSide().set(alive);
        return corpses;
    }

    public List<BattlefieldObject> collectOpponentSideCorpses() {
        List <BattlefieldObject> alive = new ArrayList<>();
        List <BattlefieldObject> corpses = new ArrayList<>();
        for(BattlefieldObject battlefieldObject : this.getOpponentSide().getBattlefieldObjects()) {
            if (battlefieldObject.isDead()) {
                corpses.add(battlefieldObject);
            } else {
                alive.add(battlefieldObject);
            }
        }
        this.getActiveSide().set(alive);
        return corpses;
    }

    @Override
    public String toString() {
        //20 -, 4 *, 20 -
        return "--------------------*" + opponent.getCurrentHP() + "*---------------[" + opponent.getResourcePool().getResource(Resource.MANA) + "]-"
                + "\n\n" + opponentSide
                + "\n\n\n-------------------------------------------\n\n"
                + activeSide + "\n\n\n"
                + "--------------------*" + activePlayer.getCurrentHP() + "*---------------[" + activePlayer.getResourcePool().getResource(Resource.MANA) + "]-";
    }

    public BattlefieldSide getFirstSide() {
        return firstSide;
    }

    public BattlefieldSide getSecondSide() {
        return secondSide;
    }

    public BattlefieldSide getActiveSide() {
        return activeSide;
    }

    public BattlefieldSide getOpponentSide() {
        return opponentSide;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getOpponent() {
        return opponent;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }
}
