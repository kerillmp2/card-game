package core;

import core.battlefield.Battlefield;
import core.battlefield.BattlefieldCreatureFactory;
import core.card.Card;
import core.player.Deck;
import core.player.Player;
import core.actions.Action;
import core.player.Resource;
import core.player.ResourcePool;
import core.turn.TurnController;

public class Main {
    public static void main(String[] args) {
        Action heal1 = (player) -> {
            player.recover(1);
            player.getGraveyard().putCardOnTop(player.getPlayedCard());
            return player;
        };

        Action drain = (player) -> {
            player.getBattlefield().getOpponent(player).getDamage(1);
            player.recover(1);
            player.getGraveyard().putCardOnTop(player.getPlayedCard());
            return player;
        };

        Action heal_draw = (player) -> {
            player.recover(1);
            player.drawCard();
            player.getGraveyard().putCardOnTop(player.getPlayedCard());
            return player;
        };

        Action create_snake_token = player -> {
            player.getBattlefield().getActiveSide().addObject(BattlefieldCreatureFactory.create("Змея", 10, 15));
            player.getGraveyard().putCardOnTop(player.getPlayedCard());
            player.getHand().add(player.getPlayedCard());
            return player;
        };

        Card healingCard = new Card("Быстрое исцеление_1", heal1, 1, "Восстанавливает 1 хп");
        Card healingCard2 = new Card("Высасывание жизни", drain, 1, "Переливает 1 хп от оппонента");
        Card healingCard3 = new Card("Точное исцеление", heal_draw, 2, "Восстанавливает 1 хп. Вы берёте карту");
        Card snakeCard = new Card("Змея", create_snake_token, 0, "Призывает змею 10/15");

        Deck deck = new Deck().putCardOnTop(snakeCard);

        Player player = new Player("Kirill");
        player.setDeck(deck);
        player.setResourcePool(new ResourcePool().addResource(Resource.MANA, 2));

        Player player2 = new Player("Kida");
        player2.setDeck(new Deck().putCardOnBottom(snakeCard));

        Battlefield battlefield = new Battlefield(player, player2);
        TurnController turnController = new TurnController(battlefield);

        System.out.println(player.getDeck().shuffle());

        while (battlefield.getPlayerOne().isAlive() && battlefield.getPlayerTwo().isAlive()) {
            turnController.beginTurn();
        }

    }
}
