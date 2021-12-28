package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Move;

import java.util.ArrayList;
import java.util.List;

public class Vizier extends Piece { //конь + слон

    private final int[][] CANDIDATE_MOVE_COORDINATES_KNIGHT = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2},
                                                                {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
    private final int[][] CANDIDATE_MOVE_COORDINATES_BISHOP = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

    public Vizier(Alliance pieceAlliance) {
        super(pieceAlliance);
    }

    public Vizier(Alliance pieceAlliance, int x, int y) {
        super(pieceAlliance, x, y);
    }

    @Override
    public List<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int[] candidateMove : CANDIDATE_MOVE_COORDINATES_KNIGHT) {
            int x = this.x;
            int y = this.y;
            if(isFirstColumnExclusion(candidateMove, x) ||
                    isSecondColumnExclusion(candidateMove, x) ||
                    isNineColumnExclusion(candidateMove, x) ||
                    isTenColumnExclusion(candidateMove, x)) {
                continue;
            }
            x = this.x + candidateMove[0];
            y = this.y + candidateMove[1];
            if (BoardUtils.isValidTileCoordinate(x, y)) {
                final Piece pieceAtDestination = board.getTile(x, y).getPiece();
                if (pieceAtDestination == null) {
                    legalMoves.add(new Move.MajorMove(board, this, x, y));
                } else {
                    final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAtDestinationAllegiance) {
                        legalMoves.add(new Move.AttackMove(board, this, x, y,
                                pieceAtDestination));
                    }
                }
            }
        }
        for (final int[] candidateMove : CANDIDATE_MOVE_COORDINATES_BISHOP) {
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
                        legalMoves.add(new Move.MajorMove(board, this, x, y));
                    }
                    else {
                        final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAtDestinationAllegiance) {
                            legalMoves.add(new Move.AttackMove(board, this, x, y,
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
        return "Визирь";
    }

    private static boolean isFirstColumnExclusion(final int[] step,
                                                  final int x) {
        return BoardUtils.FIRST_COLUMN[x][0] && ((step == new int[]{-2, -1})
                || (step == new int[]{2, -1}) || (step == new int[]{1, -2}) || (step == new int[]{-1, -2})
                || (step == new int[]{-1, -1}) || (step == new int[]{1, -1}));
    }

    private static boolean isSecondColumnExclusion(final int[] step,
                                                   final int x) {
        return BoardUtils.SECOND_COLUMN[x][0] && ((step == new int[]{1, -2})
                || (step == new int[]{-1, -2}));
    }

    private static boolean isNineColumnExclusion(final int[] step,
                                                 final int x) {
        return BoardUtils.NINE_COLUMN[x][0] && ((step == new int[]{-1, 2})
                || (step == new int[]{1, 2}));
    }

    private static boolean isTenColumnExclusion(final int[] step,
                                                final int x) {
        return BoardUtils.FIRST_COLUMN[x][0] && ((step == new int[]{-2, 1})
                || (step == new int[]{-1, 2}) || (step == new int[]{1, 2}) || (step == new int[]{2, 1})
                || (step == new int[]{-1, 1}) || (step == new int[]{1, 1}));
    }
}
