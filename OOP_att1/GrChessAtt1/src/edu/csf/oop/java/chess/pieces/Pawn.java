package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Move;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static int[][] CANDIDATE_MOVE_COORDINATES;
    private boolean firstMove = true;
    private int allianceConst = -1; // белые

    public Pawn(final Alliance allegiance) {
        super(allegiance);
    }

    public Pawn(Alliance pieceAlliance, int x, int y) {
        super(pieceAlliance, x, y);
        if (pieceAlliance.isBlack())
            allianceConst = 1;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
//        System.out.println(this.firstMove);
        final List<Move> legalMoves = new ArrayList<>();

        CANDIDATE_MOVE_COORDINATES = new int[][]{{allianceConst, allianceConst}, {allianceConst, -allianceConst}};

        for (final int[] candidateMove : CANDIDATE_MOVE_COORDINATES) {
            int x = this.x + candidateMove[0];
            int y = this.y + candidateMove[1];
            if (BoardUtils.isValidTileCoordinate(x, y)) {
                final Piece pieceAtDestination = board.getTile(x, y).getPiece();
                if (pieceAtDestination != null) {
                    final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAtDestinationAllegiance) {
                        legalMoves.add(new Move.AttackMove(board, this, x, y,
                                pieceAtDestination));
                    }
                }
            }
        }
        if (firstMove){
            int x = this.x + 2 * allianceConst;
            int y = this.y;
            final Piece pieceAtDestination = board.getTile(x, y).getPiece();
            if (pieceAtDestination == null) {
                legalMoves.add(new Move.MajorMove(board, this, x, y));
            }
            firstMove = false;
        }
        int x = this.x + allianceConst;
        int y = this.y;
        if (BoardUtils.isValidTileCoordinate(x, y)) {
            final Piece pieceAtDestination = board.getTile(x, y).getPiece();
            if (pieceAtDestination == null) {
                legalMoves.add(new Move.MajorMove(board, this, x, y));
            }
        }
        return legalMoves;
    }

    @Override
    public String getName() {
        return "Пешка";
    }
}
