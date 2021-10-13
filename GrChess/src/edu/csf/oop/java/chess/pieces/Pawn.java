package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.BoardUtils;
import edu.csf.oop.java.chess.board.Move;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    private static int[][] CANDIDATE_MOVE_COORDINATES = new int[4][2];
    private boolean firstMove = true;
    private int allianceConst = -1; // белые


    public Pawn(final Alliance allegiance) {
        super(allegiance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        if (this.getPieceAlliance() == Alliance.BLACK) {
            CANDIDATE_MOVE_COORDINATES = new int[][]{/*{1, 0}, /*{2, 0},*/ {1, 1}, {1, -1}};
            allianceConst *= -1;
        }
        else CANDIDATE_MOVE_COORDINATES = new int[][]{/*{-1, 0}, /*{-2, 0},*/ {-1, -1}, {-1, 1}};

        for (final int[] candidateMove : CANDIDATE_MOVE_COORDINATES) {
            int x = this.x;
            int y = this.y;
            if(isFirstColumnExclusion(candidateMove, x) ||
                    isTenColumnExclusion(candidateMove, x)) {
                continue;
            }
            x = this.x + candidateMove[0];
            y = this.y + candidateMove[1];
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
        } else {
            int x = this.x + 1 * allianceConst;
            int y = this.y;
            if (BoardUtils.isValidTileCoordinate(x, y)) {
                final Piece pieceAtDestination = board.getTile(x, y).getPiece();
                if (pieceAtDestination == null) {
                    legalMoves.add(new Move.MajorMove(board, this, x, y));
                }
            }
        }
        return legalMoves;
    }


    private boolean isFirstColumnExclusion(final int[] step,
                                                final int x) {
        return BoardUtils.FIRST_COLUMN[x][0] && (((step == new int[]{1, -1})
                && this.getPieceAlliance() == Alliance.BLACK) || ((step == new int[]{-1, -1})
                && this.getPieceAlliance() == Alliance.WHITE));
    }
    private boolean isTenColumnExclusion(final int[] step,
                                                final int x) {
        return BoardUtils.FIRST_COLUMN[x][0] && (((step == new int[]{-1, 1})
                && this.getPieceAlliance() == Alliance.BLACK) || ((step == new int[]{1, 1})
                && this.getPieceAlliance() == Alliance.WHITE));
    }
}
