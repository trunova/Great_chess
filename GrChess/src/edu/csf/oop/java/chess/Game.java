package edu.csf.oop.java.chess;

import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Tile;

public class Game {
    private Board gBoard = new Board();
    private Alliance alliance = Alliance.WHITE;
    private Tile tile = null;

    public Board getBoard() {
        return gBoard;
    }

    public void MouseClick(Tile f){
        if(tile == null) {
            if(f.getPiece() != null){
                if (f.getPiece().getPieceAlliance() == alliance) {
                    tile = f;
                }
            }
        }
        else{
            if(f.getPiece() != null){
                if (f.getPiece().getPieceAlliance() == alliance) {
                    tile = f;
                }
            }

            for (Tile ff: tile.getCanGoFromThis()) {
                if(ff.equals(f)){
                    Turn(tile, f);
                    tile = null;
                    break;
                }
            }
        }
    }

    private void Turn (Tile from, Tile to){
        to.setPiece(from.getPiece());
        from.setPiece(null);
        gBoard.buildGraphConnections();
        if (alliance.isBlack())
            alliance = Alliance.WHITE;
        else alliance = Alliance.BLACK;
    }
}
