package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.BoardUtils;
import edu.csf.oop.java.chess.board.Move;

import java.util.ArrayList;

import java.util.List;

import static edu.csf.oop.java.chess.board.Move.*;

public class Rook extends Piece{

    private final int[][] CANDIDATE_MOVE_COORDINATES = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public Rook(Alliance pieceAlliance) {
        super(pieceAlliance);
    }

    @Override
    public List<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int[] candidateMove : CANDIDATE_MOVE_COORDINATES) {
            int x = this.x;
            int y = this.y;
            while (BoardUtils.isValidTileCoordinate(x, y)) {
                if (isFirstColumnExclusion(candidateMove, x) ||
                        isTenColumnExclusion(candidateMove, x)) {
                    break;
                }
                x += candidateMove[0];
                y += candidateMove[1];
                if (BoardUtils.isValidTileCoordinate(x, y)) {
                    final Piece pieceAtDestination = board.getTile(x, y).getPiece();
                    if (pieceAtDestination == null) {
                        legalMoves.add(new MajorMove(board, this, x, y));
                    } else {
                        final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAtDestinationAllegiance) {
                            legalMoves.add(new AttackMove(board, this, x, y,
                                    pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return legalMoves;
    }

    private static boolean isFirstColumnExclusion(final int[] step,
                                                  final int x) {
        return BoardUtils.FIRST_COLUMN[x][0] && ((step == new int[]{0, -1}));
    }
    private static boolean isTenColumnExclusion(final int[] step,
                                                final int x) {
        return BoardUtils.TEN_COLUMN[x][9] && ((step == new int[]{0, 1}));
    }
}
