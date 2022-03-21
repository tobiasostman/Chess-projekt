package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.King;
import ax.ha.tdd.chess.engine.pieces.PieceType;
import ax.ha.tdd.chess.engine.pieces.Rook;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Game {

    Chessboard board = Chessboard.startingBoard();

    Map<String, Coordinates> rookAndKingCoordinates = new HashMap<>() {
        {
            put("WK", new Coordinates("e1"));
            put("BK", new Coordinates("e8"));
            put("WRL", new Coordinates("a1"));
            put("WRR", new Coordinates("h1"));
            put("BRL", new Coordinates("a8"));
            put("BRR", new Coordinates("h8"));
        }
    };

    Map<String, Coordinates> castledRookAndKingCoordinates = new HashMap<>() {
        {
            put("WKL", new Coordinates("c1"));
            put("BKL", new Coordinates("c8"));
            put("WKR", new Coordinates("g1"));
            put("BKR", new Coordinates("g8"));
            put("WRL", new Coordinates("d1"));
            put("WRR", new Coordinates("f1"));
            put("BRL", new Coordinates("d8"));
            put("BRR", new Coordinates("f8"));
        }
    };

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

        if (move.startsWith("o") || move.startsWith("O")) {
            //castling

            if (isCastlingLegal(move)) {
                castleKing(move);
            } else {
                System.out.println("Cannot castle right now");
            }

        } else {
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

    private void castleKing(String move) {
        if (move.equalsIgnoreCase("o-o-o")) {
            King king = (King) board.getPiece(rookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "K"));
            Rook rook = (Rook) board.getPiece(rookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "RL"));
            if (!king.getHasMoved() && !rook.getHasMoved()) {
                king.movePiece(board, castledRookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "KL"));
                rook.movePiece(board, castledRookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "RL"));
            } else {
                System.out.println("Cannot castle anymore piece has moved");
            }
        }

        if (move.equalsIgnoreCase("o-o")) {
            King king = (King) board.getPiece(rookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "K"));
            Rook rook = (Rook) board.getPiece(rookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "RR"));
            if (!king.getHasMoved() && !rook.getHasMoved()) {
                king.movePiece(board, castledRookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "KR"));
                rook.movePiece(board, castledRookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "RR"));
            } else {
                System.out.println("Cannot castle anymore piece has moved");
            }
        }
    }

    private boolean isCastlingLegal(String move) {
        if (getPlayerToMove() == Player.WHITE) {
            if (move.equalsIgnoreCase("o-o-o")) {
                if (board.tileHasPieceOnIt(new Coordinates("e1")) && board.tileHasPieceOnIt(new Coordinates("a1"))) {
                    if (board.getPiece(new Coordinates("e1")).getPieceType() != PieceType.KING) return false;
                    if (board.getPiece(new Coordinates("a1")).getPieceType() != PieceType.ROOK) return false;
                    if (board.tileHasPieceOnIt(new Coordinates("d1"))) return false;
                    if (board.tileHasPieceOnIt(new Coordinates("c1"))) return false;
                    if (board.tileHasPieceOnIt(new Coordinates("b1"))) return false;
                    return !canAnyPieceMoveHere(getPlayerToMove(), new Coordinates("c1"));
                }
            } else {
                if (board.tileHasPieceOnIt(new Coordinates("e1")) && board.tileHasPieceOnIt(new Coordinates("h1"))) {
                    if (board.getPiece(new Coordinates("e1")).getPieceType() != PieceType.KING) return false;
                    if (board.getPiece(new Coordinates("h1")).getPieceType() != PieceType.ROOK) return false;
                    if (board.tileHasPieceOnIt(new Coordinates("f1"))) return false;
                    if (board.tileHasPieceOnIt(new Coordinates("g1"))) return false;
                    return !canAnyPieceMoveHere(getPlayerToMove(), new Coordinates("c1"));
                }
            }
        }
        return false;
    }

    private boolean canAnyPieceMoveHere(Player currentPlayer, Coordinates kingNewPosition) {

        for (int x = 0; x != 8; x++) {
            for (int y = 0; y != 8; y++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (board.tileHasPieceOnIt(coordinates)) {
                    ChessPiece piece = board.getPiece(coordinates);
                    if (!Objects.equals(piece.getPlayer().getSymbol(), currentPlayer.getSymbol())) {
                        if (piece.canMove(board, kingNewPosition)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
