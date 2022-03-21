package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

public class King extends ChessPiece {

    public King(PieceType pieceType, Player player, Coordinates location) {
        super(pieceType, player, location);
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        if (this.location.getY() == destination.getY() || this.location.getX() == destination.getX()) {
            if (this.location.getY() + 1 == destination.getY()) {
                return true;
            }
            if (this.location.getX() + 1 == destination.getX()) {
                return true;
            }
            if (this.location.getY() - 1 == destination.getY()) {
                return true;
            }
            if (this.location.getX() - 1 == destination.getX()) {
                return true;
            }
        }

        if (this.location.getX() + 1 == destination.getX()) {
            if (this.location.getY() + 1 == destination.getY()) {
                return true;
            }
            if (this.location.getY() - 1 == destination.getY()) {
                return true;
            }
        }

        if (this.location.getX() - 1 == destination.getX()) {
            if (this.location.getY() + 1 == destination.getY()) {
                return true;
            }
            if (this.location.getY() - 1 == destination.getY()) {
                return true;
            }
        }

        return false;
    }
}
