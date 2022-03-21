package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.King;
import ax.ha.tdd.chess.engine.pieces.PieceType;
import ax.ha.tdd.chess.engine.pieces.Queen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChessboardTest {

    @Test
    public void Chessboard_fromBeginning_isEmpty() {
        final Chessboard chessboard = new Chessboard();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Assertions.assertNull(chessboard.getPiece(new Coordinates(x, y)));
            }
        }
    }

    @Test
    public void fullboard_whitePieces_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        for (int x = 0; x < 8; x++) {
            for (int y = 6; y < 8; y++) {
                Assertions.assertEquals(Player.WHITE, chessboard.getPiece(new Coordinates(x, y)).getPlayer());
            }
        }
    }

    @Test
    public void fullboard_BlackPieces_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 2; y++) {
                Assertions.assertEquals(Player.BLACK, chessboard.getPiece(new Coordinates(x, y)).getPlayer());
            }
        }
    }

    @Test
    public void fullboard_Pawns_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        for (int x = 0; x < 8; x++) {
            Assertions.assertEquals("P", chessboard.getPiece(new Coordinates(x, 1)).getSymbol());
            Assertions.assertEquals("P", chessboard.getPiece(new Coordinates(x, 6)).getSymbol());
        }
    }

    @Test
    public void fullboard_Rooks_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        Assertions.assertEquals("R", chessboard.getPiece(new Coordinates(0, 0)).getSymbol());
        Assertions.assertEquals("R", chessboard.getPiece(new Coordinates(7, 0)).getSymbol());
        Assertions.assertEquals("R", chessboard.getPiece(new Coordinates(0, 7)).getSymbol());
        Assertions.assertEquals("R", chessboard.getPiece(new Coordinates(7, 7)).getSymbol());
    }

    @Test
    public void fullboard_Knights_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        Assertions.assertEquals("K", chessboard.getPiece(new Coordinates(1, 0)).getSymbol());
        Assertions.assertEquals("K", chessboard.getPiece(new Coordinates(6, 0)).getSymbol());
        Assertions.assertEquals("K", chessboard.getPiece(new Coordinates(1, 7)).getSymbol());
        Assertions.assertEquals("K", chessboard.getPiece(new Coordinates(6, 7)).getSymbol());
    }

    @Test
    public void fullboard_Bishops_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        Assertions.assertEquals("B", chessboard.getPiece(new Coordinates(2, 0)).getSymbol());
        Assertions.assertEquals("B", chessboard.getPiece(new Coordinates(5, 0)).getSymbol());
        Assertions.assertEquals("B", chessboard.getPiece(new Coordinates(2, 7)).getSymbol());
        Assertions.assertEquals("B", chessboard.getPiece(new Coordinates(5, 7)).getSymbol());
    }

    @Test
    public void fullboard_Kings_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        Assertions.assertEquals(new King(PieceType.KING, Player.BLACK, new Coordinates("e8")), chessboard.getPiece(new Coordinates(4, 0)));
        Assertions.assertEquals(new King(PieceType.KING, Player.WHITE, new Coordinates("e1")), chessboard.getPiece(new Coordinates(4, 7)));
    }

    @Test
    public void fullboard_Queens_isInCorrectSpot() {
        final Chessboard chessboard = Chessboard.startingBoard();
        Assertions.assertEquals(new Queen(PieceType.QUEEN, Player.BLACK, new Coordinates("d8")), chessboard.getPiece(new Coordinates(3, 0)));
        Assertions.assertEquals(new Queen(PieceType.QUEEN, Player.WHITE, new Coordinates("d1")), chessboard.getPiece(new Coordinates(3, 7)));
    }
}
