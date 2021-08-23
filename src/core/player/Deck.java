package core.player;

import core.card.Card;

import java.util.*;

public class Deck {
    private Deque<Card> cards;

    public Deck(Collection<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    public Deck() {
        this.cards = new ArrayDeque<>();
    }

    public Card draw() {
        if (cards.size() > 0) {
            return cards.pollFirst();
        } else {
            return null;
        }
    }

    public Deck putCardOnTop(Card card) {
        cards.addFirst(card);
        return this;
    }

    public Deck putCardOnBottom(Card card) {
        cards.addLast(card);
        return this;
    }

    public Deck shuffle() {
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
        return "core.player.Deck{" +
                "cards=" + cards +
                '}';
    }
}
