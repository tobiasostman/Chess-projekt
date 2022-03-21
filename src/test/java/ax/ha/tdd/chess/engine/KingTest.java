package ax.ha.tdd.chess.engine;


import ax.ha.tdd.chess.engine.pieces.PieceType;
import org.junit.jupiter.api.*;

public class KingTest {
    Game game;

    @BeforeEach
    public void init() {
        game = new Game();
        game.board.removePiece(game.board.getPiece(new Coordinates("d2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("d1")));
        game.board.removePiece(game.board.getPiece(new Coordinates("e2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("f1")));
        game.board.removePiece(game.board.getPiece(new Coordinates("f2")));
    }

    @AfterEach
    public void destroy() {
        game = null;
    }

    @Test
    @DisplayName("King should move in a square around himself")
    public void kingShouldMoveInASquare() {
        game.move("e1-e2");
        Coordinates isAtE2 = new Coordinates("e2");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtE2).getPieceType());

        game.move("e2-d2");
        Coordinates isAtD2 = new Coordinates("d2");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtD2).getPieceType());

        game.move("d2-e3");
        Coordinates isAtE3 = new Coordinates("e3");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtE3).getPieceType());

        game.move("e3-e2");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtE2).getPieceType());

        game.move("e2-f1");
        Coordinates isAtF1 = new Coordinates("f1");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtF1).getPieceType());

        game.move("f1-e1");
        Coordinates isAtE1 = new Coordinates("e1");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtE1).getPieceType());

        game.move("e1-f1");
        game.move("f1-g1");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtF1).getPieceType());
    }

    @Test
    @DisplayName("King should be able to take pieces")
    public void kingShouldBeAbleToTakePieces() {
        game.move("d7-d5");
        game.move("d5-d4");
        game.move("d4-d3");
        game.move("d3-d2");

        game.move("e1-d2");
        Coordinates isAtD2 = new Coordinates("d2");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtD2).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtD2).getPlayer().getSymbol());

        game.move("d8-d3");
        game.move("d2-d3");
        Coordinates isAtD3 = new Coordinates("d3");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtD3).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtD3).getPlayer().getSymbol());

        game.move("c8-f5");
        game.move("f5-e4");
        game.move("d3-e4");
        Coordinates isAtE4 = new Coordinates("e4");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtE4).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtE4).getPlayer().getSymbol());
    }

    @Test
    @DisplayName("King should not move more then one square")
    public void kingShouldNotMoveMoreThenOneSquare() {
        game.move("e1-e3");
        Coordinates isAtE1 = new Coordinates("e1");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtE1).getPieceType());

        game.move("e1-d3");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtE1).getPieceType());

        game.move("e1-c1");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtE1).getPieceType());

        game.move("e1-f3");
        Assertions.assertEquals(PieceType.KING, game.board.getPiece(isAtE1).getPieceType());
    }
}
