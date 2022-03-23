package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.PieceType;
import org.junit.jupiter.api.*;

public class PawnTest {

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
    @DisplayName("pawn should move two steps forward on first move")
    public void pawnDoubleMove() {
        //test double moves
        game.move("a2-a4");
        Coordinates isAtA4 = new Coordinates("a4");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtA4).getPieceType());

        game.move("a7-a5");
        Coordinates isAtA5 = new Coordinates("a5");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtA5).getPieceType());
    }

    @Test
    @DisplayName("pawn should move one step forward on first move")
    public void pawnSingleMove() {
        // test single move forward
        game.move("a2-a3");
        Coordinates isAtA3 = new Coordinates("a3");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtA3).getPieceType());

        game.move("a7-a6");
        Coordinates isAtA6 = new Coordinates("a6");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtA6).getPieceType());
    }

    @Test
    @DisplayName("pawn should not be able to move side ways")
    public void pawnShouldNotMoveSideWays() {
        // test illegal moves
        game.move("c2-d4");
        Coordinates isAtC2 = new Coordinates("c2");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtC2).getPieceType());

        game.move("h7-g6");
        Coordinates isAtH7 = new Coordinates("h7");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtH7).getPieceType());
    }

    @Test
    @DisplayName("pawn should not be able to move over a pawn")
    public void pawnShouldNotMoveOverAnotherPawn() {
        game.move("c2-c4");
        game.move("c4-c5");
        game.move("c5-c6");
        game.move("c7-c5");

        Coordinates isAtC7 = new Coordinates("c7");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtC7).getPieceType());

        game.move("d2-d4");
        game.move("d7-d5");
        game.move("d4-d5");
        Coordinates isAtD4 = new Coordinates("d4");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtD4).getPieceType());
    }

    @Test
    @DisplayName("pawn should be able to eat pawn")
    public void pawnShouldBeAbleToEatPawn() {
        game.move("a2-a4");
        game.move("b7-b5");
        game.move("a4-b5");
        Coordinates isAtB5 = new Coordinates("b5");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtB5).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtB5).getPlayer().getSymbol());

        game.move("f2-f4");
        game.move("e7-e5");
        game.move("f4-e5");
        Coordinates isAtE5 = new Coordinates("e5");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtE5).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtE5).getPlayer().getSymbol());

        game.move("d7-d5");
        game.move("c2-c4");
        game.move("d5-c4");
        Coordinates isAtC4 = new Coordinates("c4");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtC4).getPieceType());
        Assertions.assertEquals(Player.BLACK.getSymbol(), game.board.getPiece(isAtC4).getPlayer().getSymbol());

        game.move("g7-g5");
        game.move("h2-h4");
        game.move("g5-h4");
        Coordinates isAtH4 = new Coordinates("h4");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtC4).getPieceType());
        Assertions.assertEquals(Player.BLACK.getSymbol(), game.board.getPiece(isAtC4).getPlayer().getSymbol());
    }

    @Test
    @DisplayName("Pawn should not be able to eat more then one step frowardAcross")
    public void pawnShouldNotBeAbleToEatMoreThenOneStepForwardAndAcross() {
        game.move("b2-b4");
        game.move("a7-a6");
        game.move("b4-a6");
        Coordinates isAtB4 = new Coordinates("b4");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtB4).getPieceType());

        game.move("h2-g7");
        Coordinates isAtH2 = new Coordinates("h2");
        Assertions.assertEquals(PieceType.PAWN, game.board.getPiece(isAtH2).getPieceType());
    }
}
