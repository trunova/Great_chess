package edu.csf.oop.java.chess.pieces;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Move;

import java.util.List;

public abstract class Piece {
    protected int x;
    protected int y;
    protected final Alliance pieceAlliance;

    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }

    protected Piece(Alliance pieceAlliance, int x, int y) {
        this.pieceAlliance = pieceAlliance;
        this.x = x;
        this.y = y;
    }
    protected Piece(Alliance pieceAlliance) {
        this.pieceAlliance = pieceAlliance;
    }

    public abstract List<Move> calculateLegalMoves(final Board board);
    public abstract String getName();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}