package edu.csf.oop.java.chess.board;

import edu.csf.oop.java.chess.pieces.Piece;

import java.util.List;

public class Tile {
    private List<Tile> canGoFromThis;
    private Piece piece;
    private int x;
    private int y;

    public Tile(List<Tile> canGoFromThis, Piece piece) {
        this.canGoFromThis = canGoFromThis;
        this.piece = piece;
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Piece getPiece() {
        return piece;
    }

    public List<Tile> getCanGoFromThis() {
        return canGoFromThis;
    }

    public void setCanGoFromThis(List<Tile> canGoFromThis) {
        this.canGoFromThis = canGoFromThis;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
