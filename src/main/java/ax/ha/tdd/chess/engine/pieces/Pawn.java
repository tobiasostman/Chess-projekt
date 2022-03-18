package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;

import java.util.Objects;

public class Pawn extends ChessPiece {

    boolean hasMoved = false;

    public Pawn(PieceType pieceType, Player player, Coordinates location) {
        super(pieceType, player, location);
    }

    private boolean isMoveLegal(Coordinates destination) {

        if (this.location.getX() != destination.getX()) return false;
        if (!isDoubleMoveValid(destination)) return false;

        return isSingleMoveValid(destination);
    }

    private boolean isTakingPieceLegal(Chessboard chessboard, Coordinates destination) {

        if (this.location.getX() + 1 != destination.getX() && this.location.getX() - 1 != destination.getX())
            return false;

        if (Objects.equals(this.getPlayer().getSymbol(), "W")) {
            if (this.location.getY() - 1 != destination.getY()) return false;
        }

        if (Objects.equals(this.getPlayer().getSymbol(), "B")) {
            if (this.location.getY() + 1 != destination.getY()) return false;
        }

        if (!chessboard.tileHasPieceOnIt(destination)) return false;
        return isSingleMoveValid(destination);
    }

    private boolean isSingleMoveValid(Coordinates destination) {
        if (Objects.equals(this.getPlayer().getSymbol(), "W") && hasMoved) {
            if (this.location.getY() - 1 != destination.getY()) return false;
        }

        if (Objects.equals(this.getPlayer().getSymbol(), "B") && hasMoved) {
            if (this.location.getY() + 1 != destination.getY()) return false;
        }
        return true;
    }

    private boolean isDoubleMoveValid(Coordinates destination) {
        if (Objects.equals(this.getPlayer().getSymbol(), "W") && !hasMoved) {
            if (this.location.getY() - 1 != destination.getY() && this.location.getY() - 2 != destination.getY())
                return false;
        }
        if (Objects.equals(this.getPlayer().getSymbol(), "B") && !hasMoved) {
            if (this.location.getY() + 1 != destination.getY() && this.location.getY() + 2 != destination.getY())
                return false;
        }
        return true;
    }

    private boolean isPieceInTheWay(Chessboard chessboard, Coordinates destination) {

        if (chessboard.tileHasPieceOnIt(destination)) return true;

        if (Objects.equals(this.getPlayer().getSymbol(), "W")) {
            int movement = this.location.getY() - destination.getY();
            for (int i = movement; i != 1; i--) {
                if (chessboard.tileHasPieceOnIt(new Coordinates(this.location.getX(), this.location.getY() - i)))
                    return true;
            }
        } else {
            int movement = destination.getY() - this.location.getY();
            for (int i = 1; i != movement; i++) {
                if (chessboard.tileHasPieceOnIt(new Coordinates(this.location.getX(), this.location.getY() + i)))
                    return true;
            }
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return null;
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {

        if (this.location.getX() != destination.getX()) {
            if (isTakingPieceLegal(chessboard, destination)) {
                return true;
            } else {
                return false;
            }
        }
        if (isPieceInTheWay(chessboard, destination)) return false;

        if (isMoveLegal(destination)) {
            movePiece(chessboard, destination);
            return true;
        }
        return false;
    }

    @Override
    public void takePiece(Chessboard chessboard, Coordinates destination) {
        chessboard.removePiece(chessboard.getPiece(destination));
        chessboard.removePiece(this);
        this.location = destination;
        chessboard.addPiece(this);
    }

    @Override
    public void movePiece(Chessboard chessboard, Coordinates destination) {
        chessboard.removePiece(this);
        this.location = destination;
        chessboard.addPiece(this);
        this.hasMoved = true;
    }
}
