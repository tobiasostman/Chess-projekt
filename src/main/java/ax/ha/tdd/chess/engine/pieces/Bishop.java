package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

public class Bishop extends ChessPiece {

    public Bishop(PieceType pieceType, Player player, Coordinates location) {
        super(pieceType, player, location);
    }

    @Override
    public String getSymbol() {
        return null;
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {

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

        return !isPieceInTheWay(chessboard, destination);
    }

    private boolean isPieceInTheWay(Chessboard chessboard, Coordinates destination) {

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
