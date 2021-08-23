package core.player;

import core.card.Card;

import java.util.*;

public class Graveyard {
    private Deque<Card> cards;

    public Graveyard(Collection<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    public Graveyard() {
        this.cards = new ArrayDeque<>();
    }

    public Graveyard putCardOnTop(Card card) {
        cards.addFirst(card);
        return this;
    }

    public Graveyard putCardOnBottom(Card card) {
        cards.addLast(card);
        return this;
    }

    public Graveyard shuffle() {
        List<Card> cardList = new ArrayList<>(cards);
        Collections.shuffle(cardList);
        this.cards.clear();
        cards.addAll(cardList);
        return this;
    }

    public Deque<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "core.player.Graveyard{" +
                "cards=" + cards +
                '}';
    }
}
