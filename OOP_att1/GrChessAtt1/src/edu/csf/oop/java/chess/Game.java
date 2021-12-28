package edu.csf.oop.java.chess;

import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.board.Tile;
import edu.csf.oop.java.chess.pieces.Piece;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.util.logging.;
;
//import java.util.logging.

public class Game {
    private Board gBoard = new Board();
    private Alliance alliance = Alliance.WHITE;
    private Tile tile = null;
    private final Logger logger = LoggerFactory.getLogger(Game.class);


    public Board getBoard() {
        return gBoard;
    }

    public void MouseClick(Tile tile){
        if(this.tile == null) {
            if(tile.getPiece() != null){
                if (tile.getPiece().getPieceAlliance() == alliance) {
                    this.tile = tile;
                }
            }
        }
        else{
            if(tile.getPiece() != null){
                if (tile.getPiece().getPieceAlliance() == alliance) {
                    this.tile = tile;
                }
            }

            for (Tile ff: this.tile.getCanGoFromThis()) {
//                System.out.print("tile " + tile.getX());
//                System.out.print(" " + tile.getY() + " == ");
//                System.out.print("ff " + ff.getX());
//                System.out.println(" " + ff.getY() + " ?");
//                System.out.println("_______________________");
                if(ff.equals(tile)){
                    logger.info(String.format("Походили %s, перемещение фигуры %s : (%d, %d) ---> (%d, %d)",
                            alliance.toStr(), this.tile.getPiece().getName(),
                            this.tile.getX(), this.tile.getY(), ff.getX(), ff.getY()));
                    Turn(this.tile, tile);
                    this.tile = null;
                    break;
                }
            }
        }
    }

    private void Turn (Tile from, Tile to){
        Piece piece;
        to.setPiece(from.getPiece());
        from.setPiece(null);
        gBoard.buildGraphConnections();
        if (alliance.isBlack())
            alliance = Alliance.WHITE;
        else alliance = Alliance.BLACK;
    }
}
