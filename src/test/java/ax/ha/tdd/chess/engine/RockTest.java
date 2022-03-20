package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.PieceType;
import org.junit.jupiter.api.*;

public class RockTest {

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
    @DisplayName("rook should move horizontally or vertically")
    public void rookShouldMoveHorizontalOrVertically() {
        game.board.removePiece(game.board.getPiece(new Coordinates("h2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("h7")));

        game.move("h1-h2");
        Coordinates isAtH2 = new Coordinates("h2");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtH2).getPieceType());

        game.move("h2-h5");
        Coordinates isAtH5 = new Coordinates("h5");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtH5).getPieceType());

        game.move("h5-g5");
        Coordinates isAtG5 = new Coordinates("g5");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtG5).getPieceType());

        game.move("g5-e5");
        Coordinates isAtE5 = new Coordinates("e5");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtE5).getPieceType());

        game.move("e5-b5");
        Coordinates isAtB5 = new Coordinates("b5");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtB5).getPieceType());

        game.move("h8-h3");
        Coordinates isAtH3 = new Coordinates("h3");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtH3).getPieceType());

        game.move("h3-a3");
        Coordinates isAtA3 = new Coordinates("a3");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtA3).getPieceType());
    }

    @Test
    @DisplayName("rook should move forwards and backwards")
    public void rookShouldMoveForwardsAndBackwards() {
        game.board.removePiece(game.board.getPiece(new Coordinates("h2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("h7")));

        game.move("h1-h5");
        Coordinates isAtH5 = new Coordinates("h5");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtH5).getPieceType());

        game.move("h5-h1");
        Coordinates isAtH1 = new Coordinates("h1");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtH1).getPieceType());

        game.move("h8-h5");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtH5).getPieceType());

        game.move("h5-h8");
        Coordinates isAtH8 = new Coordinates("h8");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtH8).getPieceType());
    }

    @Test
    @DisplayName("rook should take piece")
    public void rookShouldTakePiece() {
        game.board.removePiece(game.board.getPiece(new Coordinates("h2")));
        game.board.removePiece(game.board.getPiece(new Coordinates("h7")));

        game.move("h1-h8");
        Coordinates isAtH8 = new Coordinates("h8");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtH8).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtH8).getPlayer().getSymbol());

        game.move("h8-e8");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtH8).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtH8).getPlayer().getSymbol());

        game.move("h8-g8");
        Coordinates isAtG8 = new Coordinates("g8");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtG8).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtG8).getPlayer().getSymbol());

        game.move("g8-g6");
        Assertions.assertEquals(PieceType.ROOK, game.board.getPiece(isAtG8).getPieceType());
        Assertions.assertEquals(Player.WHITE.getSymbol(), game.board.getPiece(isAtG8).getPlayer().getSymbol());
    }
}
