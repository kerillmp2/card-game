package core.player;

import core.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();

    public Hand remove(int index) {
        if(index < cards.size()) {
            cards.remove(index);
        }
        return this;
    }

    public Hand remove(Card card) {
        cards.remove(card);
        return this;
    }

    public Card get(int index) {
        if(index < cards.size()) {
            return cards.get(index);
        }
        return null;
    }

    public int size() {
        return this.cards.size();
    }

    public Hand add(Card card) {
        this.cards.add(card);
        return this;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "core.player.Hand{" +
                "cards=" + cards +
                '}';
    }
}
