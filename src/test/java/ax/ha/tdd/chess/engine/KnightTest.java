package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.PieceType;
import org.junit.jupiter.api.*;

public class KnightTest {

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
    @DisplayName("Knight should be able to move in a L shape forwards and backwards")
    public void knightShouldMoveInLShapeForwardsAndBackwards() {
        game.move("g1-f3");
        Coordinates isAtF3 = new Coordinates("f3");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtF3).getPieceType());

        game.move("f3-e5");
        Coordinates isAtE5 = new Coordinates("e5");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtE5).getPieceType());

        game.move("e5-d3");
        Coordinates isAtD3 = new Coordinates("d3");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtD3).getPieceType());

        game.move("d3-e5");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtE5).getPieceType());

        game.move("e5-f3");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtF3).getPieceType());
    }

    @Test
    @DisplayName("Knight should be able to move in L shape sideways")
    public void knightShouldMoveInLShapeSideways() {
        game.move("g1-f3");
        Coordinates isAtF3 = new Coordinates("f3");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtF3).getPieceType());

        game.move("f3-d4");
        Coordinates isAtD4 = new Coordinates("d4");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtD4).getPieceType());

        game.move("d4-f5");
        Coordinates isAtF5 = new Coordinates("f5");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtF5).getPieceType());

        game.move("f5-h6");
        Coordinates isAtH6 = new Coordinates("h6");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtH6).getPieceType());
    }

    @Test
    @DisplayName("Invalid knight moves")
    public void knightShouldNotMoveInOtherShapesThenL() {
        game.move("g1-g2");
        Coordinates isAtG1 = new Coordinates("g1");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtG1).getPieceType());

        game.move("g1-g3");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtG1).getPieceType());

        game.move("g1-h4");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtG1).getPieceType());

        game.move("g1-e3");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtG1).getPieceType());
    }

    @Test
    @DisplayName("Knight should be able to take other pieces")
    public void knightShouldBeAbleToTakeOtherPieces() {
        game.move("g1-f3");
        game.move("f3-g5");
        game.move("g5-f7");

        Coordinates isAtF7 = new Coordinates("f7");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtF7).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtF7).getPlayer().getSymbol());

        game.move("f7-d8");

        Coordinates isAtD8 = new Coordinates("d8");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtD8).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtD8).getPlayer().getSymbol());

        game.move("g8-f6");
        game.move("f6-g4");
        game.move("g4-f2");

        Coordinates isAtF2 = new Coordinates("f2");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtF2).getPieceType());
        Assertions.assertEquals(Player.BLACK.getSymbol(), game.board.getPiece(isAtF2).getPlayer().getSymbol());

        game.move("f2-d1");

        Coordinates isAtD1 = new Coordinates("d1");
        Assertions.assertEquals(PieceType.KNIGHT, game.board.getPiece(isAtD1).getPieceType());
        Assertions.assertEquals(Player.BLACK.getSymbol(), game.board.getPiece(isAtD1).getPlayer().getSymbol());
    }
}
