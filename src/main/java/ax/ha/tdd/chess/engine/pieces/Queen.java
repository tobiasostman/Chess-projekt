package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

public class Queen extends ChessPiece {
    public Queen(PieceType pieceType, Player player, Coordinates location) {
        super(pieceType, player, location);
    }

    @Override
    public String getSymbol() {
        return null;
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {

        if (this.location.getX() == destination.getX() || this.location.getY() == destination.getY()) {
            return !isPieceInTheWayRookMove(chessboard, destination);
        }

        if (this.location.getX() < destination.getX() || this.location.getX() > destination.getX()) {

            int x = this.location.getX() - destination.getX();
            int y = this.location.getY() - destination.getY();

            if (this.location.getX() < destination.getX()) {
                x = destination.getX() - this.location.getX();
            }

            if (this.location.getY() < destination.getY()) {
                y = destination.getY() - this.location.getY();
            }

            if (x != y) {
                return false;
            }

            return !isPieceInTheWayBishopMove(chessboard, destination);
        }

        return false;
    }

    public boolean isPieceInTheWayRookMove(Chessboard chessboard, Coordinates destination) {
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

    private boolean isPieceInTheWayBishopMove(Chessboard chessboard, Coordinates destination) {

        if (this.location.getX() < destination.getX()) {
            int y = this.location.getY();
            for (int i = this.location.getX() + 1; i != destination.getX(); i++) {
                if (this.location.getY() < destination.getY()) {
                    if (chessboard.tileHasPieceOnIt(new Coordinates(i, y + 1))) return true;
                    y++;
                } else {
                    if (chessboard.tileHasPieceOnIt(new Coordinates(i, y - 1))) return true;
                    y--;
                }
            }
        } else if (this.location.getX() > destination.getX()) {
            int y = this.location.getY();
            for (int i = this.location.getX() - 1; i != destination.getX(); i--) {
                if (this.location.getY() < destination.getY()) {
                    if (chessboard.tileHasPieceOnIt(new Coordinates(i, y + 1))) return true;
                    y++;
                } else {
                    if (chessboard.tileHasPieceOnIt(new Coordinates(i, y - 1))) return true;
                    y--;
                }
            }
        } else {
            return true;
        }
        return false;
    }
}
