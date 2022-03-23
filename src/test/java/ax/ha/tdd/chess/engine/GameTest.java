package ax.ha.tdd.chess.engine;

import org.junit.jupiter.api.*;

public class GameTest {

    Game game;

    @BeforeEach
    public void init() {
        game = new Game();
    }

    @AfterEach
    public void destroy() {
        game = null;
    }

    @Test
    @DisplayName("should be in playing state")
    public void shouldBeInPlayingState() {
        game.move("d2-d4");
        Assertions.assertEquals(GameState.Playing, game.gameState);
    }

    @Test
    @DisplayName("White should be in check state")
    public void whiteShouldBeInCheckState() {
        game.move("d2-d4");
        game.move("c7-c6");
        game.move("e2-e3");
        game.move("d8-a5");
        Assertions.assertEquals(GameState.Check, game.gameState);
        game.move("e1-e2");
        Assertions.assertEquals(GameState.Playing, game.gameState);
        game.move("a5-d2");
        Assertions.assertEquals(GameState.Check, game.gameState);
        game.move("e2-d2");
        Assertions.assertEquals(GameState.Playing, game.gameState);
    }

    @Test
    @DisplayName("White should be in checkmate state")
    public void whiteShouldBeInCheckMateState() {
        game.move("d2-d4");
        game.move("c7-c6");
        game.move("a2-a3");
        game.move("d8-a5");
        Assertions.assertEquals(GameState.CheckMate, game.gameState);
    }

    @Test
    @DisplayName("Black should be in check state")
    public void blackShouldBeInCheckState() {
        game.move("c2-c3");
        game.move("d7-d5");
        game.move("e2-e3");
        game.move("e7-e6");
        game.move("d1-a4");
        Assertions.assertEquals(GameState.Check, game.gameState);
        game.move("e8-e7");
        Assertions.assertEquals(GameState.Playing, game.gameState);
        game.move("a4-d7");
        Assertions.assertEquals(GameState.Check, game.gameState);
        game.move("e7-d7");
        Assertions.assertEquals(GameState.Playing, game.gameState);
    }

    @Test
    @DisplayName("Black should be in checkmate state")
    public void blackShouldBeInCheckMateState() {
        game.move("c2-c3");
        game.move("d7-d5");
        game.move("d1-a4");
        Assertions.assertEquals(GameState.CheckMate, game.gameState);
    }

    @Test
    @DisplayName("game should alternate between black and white")
    public void gameShouldAlternateBetweenBlackAndWhite() {
        Assertions.assertEquals(Player.WHITE, game.getPlayerToMove());
        game.move("a2-a4");
        Assertions.assertEquals(Player.BLACK, game.getPlayerToMove());
        game.move("d7-d5");
        Assertions.assertEquals(Player.WHITE, game.getPlayerToMove());
        game.move("b2-c3");
        Assertions.assertEquals(Player.WHITE, game.getPlayerToMove());
        game.move("b2-b3");
        Assertions.assertEquals(Player.BLACK, game.getPlayerToMove());
    }
}
