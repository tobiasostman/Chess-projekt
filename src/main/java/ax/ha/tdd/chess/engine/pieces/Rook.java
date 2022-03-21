package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

public class Rook extends ChessPiece {
    public Rook(PieceType pieceType, Player player, Coordinates location) {
        super(pieceType, player, location);
    }

    @Override
    public String getSymbol() {
        return null;
    }

    public boolean isPieceInTheWay(Chessboard chessboard, Coordinates destination) {
        boolean isXMovement = destination.getY() == this.location.getY();

        if (isXMovement) {
            return movementLoop(chessboard, true, this.location.getX(), destination.getX());
        } else {
            return movementLoop(chessboard, false, this.location.getY(), destination.getY());
        }
    }

    private boolean movementLoop(Chessboard chessboard, boolean isXMovement, int startPos, int endPos) {
        if (startPos > endPos) {
            for (int i = startPos - 1; i != endPos; i--) {
                if (isXMovement) {
                    if (chessboard.tileHasPieceOnIt(new Coordinates(i, this.location.getY()))) {
                        return true;
                    }
                } else {
                    if (chessboard.tileHasPieceOnIt(new Coordinates(this.location.getX(), i))) {
                        return true;
                    }
                }

            }
        } else {
            for (int i = startPos + 1; i != endPos; i++) {
                if (isXMovement) {
                    if (chessboard.tileHasPieceOnIt(new Coordinates(i, this.location.getY()))) {
                        return true;
                    }
                } else {
                    if (chessboard.tileHasPieceOnIt(new Coordinates(this.location.getX(), i))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {

        if (this.location.getX() != destination.getX() && this.location.getY() != destination.getY()) return false;

        return !isPieceInTheWay(chessboard, destination);
    }
}
