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

    }

    @Test
    @DisplayName("should be in check state")
    public void shouldBeInCheckState() {

    }

    @Test
    @DisplayName("should be in checkmate state")
    public void shouldBeInCheckMateState() {

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
