package core.card;

import core.player.Player;
import core.player.Resource;
import core.actions.Action;
import core.utils.Selectable;
import core.utils.WithDescription;

import java.util.Map;
import java.util.TreeMap;

public class Card implements Selectable, WithDescription {
    private String name = "core.card.Card";
    private Action onPlay;
    private String description = "...";
    private Map<Resource, Integer> cost = new TreeMap<>();

    public Card(String name, Action onPlay, int manacost, String description) {
        this.name = name;
        this.onPlay = onPlay;
        this.cost.put(Resource.MANA, manacost);
        this.description = description;
    }

    public Card(String name, Action onPlay, int manacost) {
        this.name = name;
        this.onPlay = onPlay;
        this.cost.put(Resource.MANA, manacost);
    }

    public Card(String name, Action onPlay, Map<Resource, Integer> cost) {
        this.name = name;
        this.onPlay = onPlay;
        this.cost = cost;
    }

    public Card(String name, Action onPlay) {
        this.name = name;
        this.onPlay = onPlay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Resource, Integer> getCost() {
        return cost;
    }

    public void setCost(Map<Resource, Integer> cost) {
        this.cost = cost;
    }

    public Player play(Player player) {
        return this.onPlay.resolve(player);
    }

    public Card(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getNameWithDescription() {
        return this.name + " [" + this.description + "]";
    }

    @Override
    public String toString() {
        return this.name;
    }
}
