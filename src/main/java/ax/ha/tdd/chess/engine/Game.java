package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.King;
import ax.ha.tdd.chess.engine.pieces.PieceType;
import ax.ha.tdd.chess.engine.pieces.Rook;

import java.util.*;

public class Game {

    Chessboard board = Chessboard.startingBoard();
    //Feel free to delete this stuff. Just for initial testing.
    boolean isNewGame = true;
    GameState gameState = GameState.Playing;
    Player player = Player.WHITE;
    Coordinates checkedPlayerKingCoordinates;
    boolean isTesting = false;
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

    public Game() {

    }

    public Game(boolean isTesting) {
        this.isTesting = isTesting;
    }

    public Player getPlayerToMove() {
        //TODO this should reflect the current state.
        if (isTesting) return Player.WHITE;
        return player;
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
        if (isTesting) {
            gameState = GameState.Playing;
        }
        isNewGame = false;
        System.out.println("Player tried to perform move: " + move);
        String[] moves = move.split("-");
        if (GameState.Playing == gameState) {
            gameStateIsPlaying(move, moves);
        } else if (GameState.Check == gameState) {
            gameStateIsCheck(moves);
        }
        if (GameState.CheckMate == gameState) {
            //TODO add player wins
            System.out.println("White wins");
        }


    }

    private void gameStateIsPlaying(String move, String[] moves) {
        if (move.startsWith("o") || move.startsWith("O")) {
            //castling
            if (isCastlingLegal(move)) {
                castleKing(move);
                updatePlayer();
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
                updatePlayer();
                if (isEnemyInCheck(piece)) {
                    gameState = GameState.Check;
                    getValidKingMoves();
                }

            } else if (canMove && !board.tileHasPieceOnIt(endPos)) {
                piece.movePiece(board, endPos);
                updatePlayer();
                if (isEnemyInCheck(piece)) {
                    gameState = GameState.Check;
                    getValidKingMoves();
                }
            } else {
                System.out.println("invalid move");
            }
        }
    }

    private void gameStateIsCheck(String[] moves) {
        Coordinates startPos = new Coordinates(moves[0]);
        Coordinates endPos = new Coordinates(moves[1]);

        List<Coordinates> possibleMoves = getValidKingMoves();
        if (possibleMoves.contains(endPos)) {
            ChessPiece piece = board.getPiece(startPos);
            if (board.tileHasPieceOnIt(endPos)) {
                piece.takePiece(board, endPos);
                updatePlayer();
                gameState = GameState.Playing;
            } else if (!board.tileHasPieceOnIt(endPos)) {
                piece.movePiece(board, endPos);
                updatePlayer();
                gameState = GameState.Playing;
            }
        } else {
            System.out.println("invalid move");
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
                return castleQueenSide(new Coordinates("d1"), new Coordinates("c1"), new Coordinates("b1"));
            } else {
                return castleKingSide(new Coordinates("f1"), new Coordinates("g1"));
            }
        } else {
            if (move.equalsIgnoreCase("o-o-o")) {
                return castleQueenSide(new Coordinates("d8"), new Coordinates("c8"), new Coordinates("b8"));
            } else {
                return castleKingSide(new Coordinates("f8"), new Coordinates("g8"));
            }
        }
    }

    private boolean castleKingSide(Coordinates bishop, Coordinates knight) {
        Coordinates king = rookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "K");
        Coordinates rook = rookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "RR");

        if (board.tileHasPieceOnIt(king) && board.tileHasPieceOnIt(rook)) {
            if (board.getPiece(king).getPieceType() != PieceType.KING) return false;
            if (board.getPiece(rook).getPieceType() != PieceType.ROOK) return false;
            if (board.tileHasPieceOnIt(bishop)) return false;
            if (board.tileHasPieceOnIt(knight)) return false;

            List<Coordinates> positionsToCheck = new ArrayList<>() {
                {
                    add(bishop);
                    add(knight);
                    add(castledRookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "KR"));
                    add(castledRookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "RR"));
                }
            };
            for (Coordinates coordinates : positionsToCheck) {
                boolean isLegal = canAnyPieceMoveHere(getPlayerToMove(), coordinates);
                if (isLegal) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean castleQueenSide(Coordinates queen, Coordinates bishop, Coordinates knight) {
        Coordinates king = rookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "K");
        Coordinates rook = rookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "RL");
        if (board.tileHasPieceOnIt(king) && board.tileHasPieceOnIt(rook)) {
            if (board.getPiece(king).getPieceType() != PieceType.KING)
                return false;
            if (board.getPiece(rook).getPieceType() != PieceType.ROOK)
                return false;
            if (board.tileHasPieceOnIt(queen)) return false;
            if (board.tileHasPieceOnIt(bishop)) return false;
            if (board.tileHasPieceOnIt(knight)) return false;
            List<Coordinates> positionsToCheck = new ArrayList<>() {
                {
                    add(queen);
                    add(bishop);
                    add(knight);
                    add(castledRookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "KL"));
                    add(castledRookAndKingCoordinates.get(getPlayerToMove().getSymbol() + "RL"));
                }
            };
            for (Coordinates coordinates : positionsToCheck) {
                boolean isLegal = canAnyPieceMoveHere(getPlayerToMove(), coordinates);
                if (isLegal) {
                    return false;
                }
            }
            return true;

        }
        return false;
    }


    private boolean canAnyPieceMoveHere(Player currentPlayer, Coordinates position) {

        for (int x = 0; x != 8; x++) {
            for (int y = 0; y != 8; y++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (board.tileHasPieceOnIt(coordinates)) {
                    ChessPiece piece = board.getPiece(coordinates);
                    if (!Objects.equals(piece.getPlayer().getSymbol(), currentPlayer.getSymbol())) {
                        if (piece.canMove(board, position)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isEnemyInCheck(ChessPiece piece) {
        Coordinates kingCoords = null;
        for (int x = 0; x != 8; x++) {
            for (int y = 0; y != 8; y++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (board.tileHasPieceOnIt(coordinates)) {
                    if (board.getPiece(coordinates).getPieceType() == PieceType.KING &&
                            Objects.equals(player, board.getPiece(coordinates).getPlayer())) {
                        kingCoords = coordinates;
                        break;
                    }
                }
            }
        }

        if (piece.canMove(board, kingCoords)) {
            checkedPlayerKingCoordinates = kingCoords;
            return true;
        }
        for (int x = 0; x != 8; x++) {
            for (int y = 0; y != 8; y++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (board.tileHasPieceOnIt(coordinates)) {
                    ChessPiece chessPiece = board.getPiece(coordinates);
                    if (chessPiece.canMove(board, kingCoords) &&
                            !Objects.equals(chessPiece.getPlayer(), player)) {
                        checkedPlayerKingCoordinates = kingCoords;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<Coordinates> getValidKingMoves() {
        King king = (King) board.getPiece(checkedPlayerKingCoordinates);
        int kingYPos = king.getLocation().getY();
        int kingXPos = king.getLocation().getX();
        List<Coordinates> kingMoves = new ArrayList<>();
        if (kingXPos + 1 < 7) kingMoves.add(new Coordinates(kingXPos + 1, kingYPos));
        if (kingXPos - 1 > 0) kingMoves.add(new Coordinates(kingXPos - 1, kingYPos));
        if (kingYPos + 1 < 7) kingMoves.add(new Coordinates(kingXPos, kingYPos + 1));
        if (kingYPos - 1 > 0) kingMoves.add(new Coordinates(kingXPos, kingYPos - 1));
        if (kingXPos + 1 < 7 && kingYPos + 1 < 7) kingMoves.add(new Coordinates(kingXPos + 1, kingYPos + 1));
        if (kingXPos + 1 < 7 && kingYPos - 1 > 0) kingMoves.add(new Coordinates(kingXPos + 1, kingYPos - 1));
        if (kingXPos - 1 > 0 && kingYPos + 1 < 7) kingMoves.add(new Coordinates(kingXPos - 1, kingYPos + 1));
        if (kingXPos - 1 > 0 && kingYPos - 1 > 0) kingMoves.add(new Coordinates(kingXPos - 1, kingYPos - 1));

        kingMoves.removeIf(possibleMove -> canAnyPieceMoveHere(getPlayerToMove(), possibleMove));
        kingMoves.removeIf(possibleMove -> {
            if (board.tileHasPieceOnIt(possibleMove)) {
                if (board.getPiece(possibleMove).getPlayer() == king.getPlayer()) {
                    return true;
                }
            }
            return false;
        });

        if (kingMoves.isEmpty()) {
            gameState = GameState.CheckMate;
        }
        return kingMoves;
    }

    private void updatePlayer() {
        if (Player.WHITE == player) {
            player = Player.BLACK;
        } else if (Player.BLACK == player) {
            player = Player.WHITE;
        }
    }
}
