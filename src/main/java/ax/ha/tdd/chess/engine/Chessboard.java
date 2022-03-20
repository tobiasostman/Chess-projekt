package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.*;

import java.util.Iterator;
import java.util.List;

public class Chessboard implements Iterable<ChessPiece[]> {
    // This could just as easily be replaced with a List or Set,
    // since the ChessPieces right now keep track of their own location.
    // Feel free to change this however you like
    // [y][x]
    private final ChessPiece[][] board = new ChessPiece[8][8];

    public static Chessboard startingBoard() {
        final Chessboard chessboard = new Chessboard();

        chessboard.withMirroredPiece(PieceType.PAWN, List.of(0, 1, 2, 3, 4, 5, 6, 7), 1)
                .withMirroredPiece(PieceType.ROOK, List.of(0, 7), 0)
                .withMirroredPiece(PieceType.KNIGHT, List.of(1, 6), 0)
                .withMirroredPiece(PieceType.BISHOP, List.of(2, 5), 0)
                .withMirroredPiece(PieceType.QUEEN, List.of(3), 0)
                .withMirroredPiece(PieceType.KING, List.of(4), 0);
        return chessboard;
    }

    public ChessPiece getPiece(final Coordinates coordinates) {
        return board[coordinates.getY()][coordinates.getX()];
    }

    public boolean tileHasPieceOnIt(Coordinates position) {
        if (board[position.getY()][position.getX()] == null) return false;
        return true;
    }

    public void addPiece(final ChessPiece chessPiece) {
        board[chessPiece.getLocation().getY()][chessPiece.getLocation().getX()] = chessPiece;
    }

    public void removePiece(final ChessPiece chessPiece) {
        board[chessPiece.getLocation().getY()][chessPiece.getLocation().getX()] = null;
    }

    /**
     * Helper method to initialize chessboard with {@link ChessPieceStub}.
     * Basically mirrors all added pieces for both players.
     * When all pieces has been implemented, this should be replaced with the proper implementations.
     *
     * @param pieceType    pieceType
     * @param xCoordinates xCoordinates
     * @param yCoordinate  yCoordinateOffset
     * @return itself, like a builder pattern
     */
    private Chessboard withMirroredPiece(final PieceType pieceType,
                                         final List<Integer> xCoordinates, final int yCoordinate) {
        xCoordinates.forEach(xCoordinate -> {
            if (pieceType == PieceType.PAWN) {
                addPiece(new Pawn(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
                addPiece(new Pawn(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
            } else if (pieceType == PieceType.ROOK) {
                addPiece(new Rook(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
                addPiece(new Rook(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
            } else if (PieceType.KNIGHT == pieceType) {
                addPiece(new Knight(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
                addPiece(new Knight(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
            } else if (PieceType.BISHOP == pieceType) {
                addPiece(new Bishop(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
                addPiece(new Bishop(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
            } else if (PieceType.QUEEN == pieceType) {
                addPiece(new Queen(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
                addPiece(new Queen(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
            } else {
                addPiece(new ChessPieceStub(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
                addPiece(new ChessPieceStub(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
            }
        });
        return this;
    }

    @Override
    public Iterator<ChessPiece[]> iterator() {
        return List.of(board).iterator();
    }
}
