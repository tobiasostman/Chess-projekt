package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPiece;

public class Game {

    Chessboard board = Chessboard.startingBoard();

    //Feel free to delete this stuff. Just for initial testing.
    boolean isNewGame = true;

    public Player getPlayerToMove() {
        //TODO this should reflect the current state.
        return Player.WHITE;
    }

    public Chessboard getBoard() {
        return board;
    }

    public String getLastMoveResult() {
        //TODO this should be used to show the player what happened
        //Illegal move, correct move, e2 moved to e4 etc.
        if (isNewGame) {
            return "Game hasn't begun";
        }
        return "Last move was successful (default reply, change this)";
    }

    public void move(String move) {
        //TODO this should trigger your move logic.
        isNewGame = false;
        System.out.println("Player tried to perform move: " + move);
        String[] moves = move.split("-");

        Coordinates startPos = new Coordinates(moves[0]);
        Coordinates endPos = new Coordinates(moves[1]);

        ChessPiece piece = board.getPiece(startPos);

        boolean canMove = piece.canMove(board, endPos);

        if (canMove && board.tileHasPieceOnIt(endPos) && !piece.isTakingPieceFriendly(board, endPos)) {
            piece.takePiece(board, endPos);
        } else if (canMove && !board.tileHasPieceOnIt(endPos)) {
            piece.movePiece(board, endPos);
        } else {
            System.out.println("invalid move");
        }
    }
}
