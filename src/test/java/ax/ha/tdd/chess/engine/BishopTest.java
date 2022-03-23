package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.PieceType;
import org.junit.jupiter.api.*;

public class BishopTest {

    Game game;

    @BeforeEach
    public void init() {
        game = new Game(true);
        game.board.removePiece(game.board.getPiece(new Coordinates("d2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("b2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("e2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("g2")));
    }

    @AfterEach
    public void destroy() {
        game = null;
    }

    @Test
    @DisplayName("Bishop should be able to move diagonally")
    public void bishopShouldBeAbleToMoveDiagonally() {
        game.move("f1-b5");
        Coordinates isAtB5 = new Coordinates("b5");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtB5).getPieceType());

        game.move("b5-c6");
        Coordinates isAtC6 = new Coordinates("c6");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtC6).getPieceType());

        game.move("c6-e4");
        Coordinates isAtE4 = new Coordinates("e4");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtE4).getPieceType());

        game.move("e4-d3");
        Coordinates isAtD3 = new Coordinates("d3");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtD3).getPieceType());

        game.move("c1-a3");
        Coordinates isAtA3 = new Coordinates("a3");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtA3).getPieceType());

        game.move("a3-d6");
        Coordinates isAtD6 = new Coordinates("d6");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtD6).getPieceType());

        game.move("d6-f4");
        Coordinates isAtF4 = new Coordinates("f4");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtF4).getPieceType());

        game.move("f4-c1");
        Coordinates isAtC1 = new Coordinates("c1");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtC1).getPieceType());
    }

    @Test
    @DisplayName("Bishop should only move diagonally")
    public void bishopShouldOnlyMoveDiagonally() {

        game.move("f1-f2");
        Coordinates isAtF1 = new Coordinates("f1");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtF1).getPieceType());

        game.move("f1-g3");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtF1).getPieceType());

        game.move("f1-d1");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtF1).getPieceType());

        game.move("f1-c3");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtF1).getPieceType());
    }

    @Test
    @DisplayName("Bishop should not move over pieces")
    public void bishopShouldNotMoveOverPieces() {
        game.move("f8-h3");
        Coordinates isAtF8 = new Coordinates("f8");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtF8).getPieceType());

        game.move("f8-a3");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtF8).getPieceType());

        game.move("c8-e6");
        Coordinates isAtC8 = new Coordinates("c8");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtC8).getPieceType());

        game.move("c8-a6");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtC8).getPieceType());
    }

    @Test
    @DisplayName("Bishop should be able to take pieces")
    public void bishopShouldBeAbleToTakePieces() {
        game.move("f1-a6");
        game.move("a6-b7");
        Coordinates isAtB7 = new Coordinates("b7");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtB7).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtB7).getPlayer().getSymbol());

        game.move("b7-c8");
        Coordinates isAtC8 = new Coordinates("c8");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtC8).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtC8).getPlayer().getSymbol());

        game.move("c8-d7");
        Coordinates isAtD7 = new Coordinates("d7");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtD7).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtD7).getPlayer().getSymbol());

        game.move("d7-c6");
        game.move("c6-a8");
        Coordinates isAtA8 = new Coordinates("a8");
        Assertions.assertEquals(PieceType.BISHOP, game.board.getPiece(isAtA8).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtA8).getPlayer().getSymbol());
    }
}
