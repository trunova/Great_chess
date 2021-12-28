package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Move;

import java.util.ArrayList;

import java.util.List;

import static edu.csf.oop.java.chess.board.Move.*;

public class Queen extends Piece {

    private final int[][] CANDIDATE_MOVE_COORDINATES = {{- 1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    public Queen(Alliance pieceAlliance) {
        super(pieceAlliance);
    }
    public Queen(Alliance pieceAlliance, int x, int y){
        super(pieceAlliance, x, y);
    }

    @Override
    public List<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int[] candidateMove : CANDIDATE_MOVE_COORDINATES) {
            int x = this.x;
            int y = this.y;

            while (true) {
                if (isFirstColumnExclusion(candidateMove, x) ||
                        isTenColumnExclusion(candidateMove, x)) {
                    break;
                }
                x += candidateMove[0];
                y += candidateMove[1];

                if (!BoardUtils.isValidTileCoordinate(x, y)) {
                    break;
                } else {
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

    @Override
    public String getName() {
        return "Ферзь";
    }

    private static boolean isFirstColumnExclusion(final int[] step,
                                                  final int x) {
        return BoardUtils.FIRST_COLUMN[x][0] && ((step == new int[]{-1, -1})
                || (step == new int[]{0, -1}) || (step == new int[]{1, -1}));
    }

    private static boolean isTenColumnExclusion(final int[] step,
                                                  final int x) {
        return BoardUtils.TEN_COLUMN[x][9] && ((step == new int[]{-1, 1})
                || (step == new int[]{0, 1}) || (step == new int[]{1, 1}));
    }

}
