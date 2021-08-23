package core.player;

import core.battlefield.Attackable;
import core.battlefield.Attacker;
import core.battlefield.Battlefield;
import core.card.Card;
import core.utils.WithDescription;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int currentHP = 15;
    private int number = 1;
    private String name = "Игрок";
    private boolean alive = true;
    private Deck deck = new Deck();
    private Hand hand = new Hand();
    private Graveyard graveyard = new Graveyard();
    private int maxMana = 0;
    private ResourcePool resourcePool = new ResourcePool();
    private Battlefield battlefield;
    private Card playedCard = null;

    public void playCardFromHand(int position) {
        if (position < hand.size()) {
            boolean canPlay = true;
            Card playedCard = hand.get(position);
            for (Resource resource : playedCard.getCost().keySet()) {
                if (!this.resourcePool.haveResource(resource, playedCard.getCost().get(resource))) {
                    System.out.println(name + " не хватает ресурса " + resource.getName());
                    canPlay = false;
                }
            }
            if (canPlay) {
                for (Resource resource : playedCard.getCost().keySet()) {
                    this.resourcePool.takeResource(resource, playedCard.getCost().get(resource));
                    System.out.println(name + " тратит " + playedCard.getCost().get(resource) + " ресурса " + resource.getName());
                }
                this.hand.remove(playedCard);
                this.playedCard = playedCard;
                System.out.println(name + " разыгрывает карту " + playedCard.getName());
                playedCard.play(this);
                this.playedCard = null;
            }
        }
    }

    public void drawCard() {
        Card takenCard = deck.draw();
        if (takenCard != null) {
            hand.add(takenCard);
        } else {
            System.out.println("В колоде " + name + " больше нет карт!");
            this.getDamage(1);
        }
    }

    public List<? extends WithDescription> getTurnOptions() {
        List<WithDescription> turnOptions = new ArrayList<>();
        turnOptions.add(() -> "Закончить ход");
        turnOptions.add(() -> "Поле боя");
        turnOptions.addAll(this.getHand().getCards());
        return turnOptions;
    }

    public Attackable getFace() {
        return new Attackable() {
            @Override
            public String getNameWithDescription() {
                return name + " HP: " + currentHP;
            }

            @Override
            public void onTakeAttack(Attacker from, int amount) {
                currentHP -= amount;
            }

            @Override
            public String getName() {
                return name + " HP: " + currentHP;
            }

            @Override
            public boolean isAttackable() {
                return true;
            }
        };
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }

    public void setBattlefield(Battlefield battlefield) {
        this.battlefield = battlefield;
    }

    public Player(String name) {
        this.name = name;
    }

    public Graveyard getGraveyard() {
        return graveyard;
    }

    public void setGraveyard(Graveyard graveyard) {
        this.graveyard = graveyard;
    }

    public Deck getDeck() {
        return deck;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public ResourcePool getResourcePool() {
        return resourcePool;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setResourcePool(ResourcePool resourcePool) {
        this.resourcePool = resourcePool;
    }

    public void getDamage(int amount) {
        if (amount > 0) {
            this.currentHP = this.currentHP - amount;
            System.out.println("Игрок " + name + " получил " + amount + " урона!");
        } else {
            System.out.println("Игрок " + name + " не получает урона!");
        }
        checkAlive();
    }

    public void recover(int amount) {
        this.currentHP = this.currentHP + amount;
        System.out.println("Игрок " + name + " восстанавливает " + amount + " здоровья!");

    }

    private void checkAlive() {
        if (this.currentHP <= 0) {
            this.alive = false;
            System.out.println("Игрок " + name + " выбывает из игры!");
        }
    }

    public boolean isAlive() {
        return this.alive;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
        this.checkAlive();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void restoreMana() {
        this.getResourcePool().setResource(Resource.MANA, this.maxMana);
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void incrementMaxMana() {
        this.maxMana += 1;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public Card getPlayedCard() {
        return playedCard;
    }
}
