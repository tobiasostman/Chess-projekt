package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

public class Knight extends ChessPiece {
    public Knight(PieceType pieceType, Player player, Coordinates location) {
        super(pieceType, player, location);
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {

        int startX = this.location.getX();
        int startY = this.location.getY();

        boolean moveOneStepLeftOrRight = (startX + 1 == destination.getX() || startX - 1 == destination.getX());
        boolean moveOneStepUpOrDown = (startY + 1 == destination.getY() || startY - 1 == destination.getY());

        if (startY + 2 == destination.getY() && moveOneStepLeftOrRight) return true;
        if (startY - 2 == destination.getY() && moveOneStepLeftOrRight) return true;
        if (startX + 2 == destination.getX() && moveOneStepUpOrDown) return true;
        return startX - 2 == destination.getX() && moveOneStepUpOrDown;
    }
}
