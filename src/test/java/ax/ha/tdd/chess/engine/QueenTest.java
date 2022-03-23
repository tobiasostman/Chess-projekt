package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.PieceType;
import org.junit.jupiter.api.*;

public class QueenTest {
    Game game;

    @BeforeEach
    public void init() {
        game = new Game(true);
    }

    @AfterEach
    public void destroy() {
        game = null;
    }

    @Test
    @DisplayName("Queen should move like a rook")
    public void queenShouldMoveLikeARook() {
        game.board.removePiece(game.board.getPiece(new Coordinates("d2")));

        game.move("d1-d5");
        Coordinates isAtD5 = new Coordinates("d5");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtD5).getPieceType());

        game.move("d5-a5");
        Coordinates isAtA5 = new Coordinates("a5");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtA5).getPieceType());

        game.move("a5-h5");
        Coordinates isAtH5 = new Coordinates("h5");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtH5).getPieceType());

        game.move("h5-h3");
        Coordinates isAtH3 = new Coordinates("h3");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtH3).getPieceType());

        game.move("h3-h2");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtH3).getPieceType());
    }

    @Test
    @DisplayName("Queen should move like a bishop")
    public void queenShouldMoveLikeABishop() {
        game.board.removePiece(game.board.getPiece(new Coordinates("d2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("e2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("c2")));

        game.move("d1-h5");
        Coordinates isAtH5 = new Coordinates("h5");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtH5).getPieceType());

        game.move("h5-d1");
        Coordinates isAtD1 = new Coordinates("d1");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtD1).getPieceType());

        game.move("d1-a4");
        Coordinates isAtA4 = new Coordinates("a4");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtA4).getPieceType());

        game.move("a4-d1");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtD1).getPieceType());

        game.move("d1-d2");
        Coordinates isAtD2 = new Coordinates("d2");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtD2).getPieceType());

        game.move("d2-a5");
        Coordinates isAtA5 = new Coordinates("a5");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtA5).getPieceType());

        game.move("a5-d2");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtD2).getPieceType());

        game.move("d2-h6");
        Coordinates isAtH6 = new Coordinates("h6");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtH6).getPieceType());
    }

    @Test
    @DisplayName("Queen should be able to take pieces")
    public void queenShouldBeAbleToTakePieces() {
        game.board.removePiece(game.board.getPiece(new Coordinates("d2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("e2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("c2")));

        game.move("d1-h5");
        game.move("h5-f7");

        Coordinates isAtF7 = new Coordinates("f7");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtF7).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtF7).getPlayer().getSymbol());


        game.move("f7-e7");

        Coordinates isAtE7 = new Coordinates("e7");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtE7).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtE7).getPlayer().getSymbol());

        game.move("e7-d8");

        Coordinates isAtD8 = new Coordinates("d8");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtD8).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtD8).getPlayer().getSymbol());

        game.move("d8-c7");

        Coordinates isAtC7 = new Coordinates("c7");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtC7).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtC7).getPlayer().getSymbol());
    }

    @Test
    @DisplayName("Queen should only move like a rook and a bishop")
    public void queenShouldOnlyMoveLikeARookAndABishop() {
        game.board.removePiece(game.board.getPiece(new Coordinates("d2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("e2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("c2")));

        game.move("d1-e5");
        Coordinates isAtd1 = new Coordinates("d1");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtd1).getPieceType());

        game.move("d1-c5");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtd1).getPieceType());

        game.move("d1-b5");
        Assertions.assertEquals(PieceType.QUEEN, game.board.getPiece(isAtd1).getPieceType());

    }
}
