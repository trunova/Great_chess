package edu.csf.oop.java.chess.board;


import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int BOARD_LENGTH = 10;
    private Tile[][] board = new Tile[BOARD_LENGTH][BOARD_LENGTH];

    public Tile getTile(int i, int j){ return board[i][j];}

    public Board(){
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                board[i][j] = new Tile(i, j);
            }
        }
        board[0][0].setPiece(new Rook(Alliance.BLACK));
        board[0][1].setPiece(new Knight(Alliance.BLACK));
        board[0][2].setPiece(new Bishop(Alliance.BLACK));
        board[0][3].setPiece(new Queen(Alliance.BLACK));
        board[0][4].setPiece(new King(Alliance.BLACK));
        board[0][5].setPiece(new Giraffe(Alliance.BLACK));
        board[0][6].setPiece(new Vizier(Alliance.BLACK));
        board[0][7].setPiece(new Bishop(Alliance.BLACK));
        board[0][8].setPiece(new Knight(Alliance.BLACK));
        board[0][9].setPiece(new Rook(Alliance.BLACK));

        for (int i = 0; i < 4; i++) {
            board[1][i].setPiece(new Pawn(Alliance.BLACK));
            board[1][i+6].setPiece(new Pawn(Alliance.BLACK));
        }
        board[1][4].setPiece(new WM(Alliance.BLACK));
        board[1][5].setPiece(new WM(Alliance.BLACK));
        board[2][4].setPiece(new Pawn(Alliance.BLACK));
        board[2][5].setPiece(new Pawn(Alliance.BLACK));
        ////////////////
        board[9][9].setPiece(new Rook(Alliance.WHITE));
        board[9][8].setPiece(new Knight(Alliance.WHITE));
        board[9][7].setPiece(new Bishop(Alliance.WHITE));
        board[9][6].setPiece(new Queen(Alliance.WHITE));
        board[9][5].setPiece(new King(Alliance.WHITE));
        board[9][4].setPiece(new Giraffe(Alliance.WHITE));
        board[9][3].setPiece(new Vizier(Alliance.WHITE));
        board[9][2].setPiece(new Bishop(Alliance.WHITE));
        board[9][1].setPiece(new Knight(Alliance.WHITE));
        board[9][0].setPiece(new Rook(Alliance.WHITE));

        for (int i = 0; i < 4; i++) {
            board[8][i].setPiece(new Pawn(Alliance.WHITE));
            board[8][i+6].setPiece(new Pawn(Alliance.WHITE));
        }
        board[8][4].setPiece(new WM(Alliance.WHITE));
        board[8][5].setPiece(new WM(Alliance.WHITE));
        board[7][4].setPiece(new Pawn(Alliance.WHITE));
        board[7][5].setPiece(new Pawn(Alliance.WHITE));
    }

    public void buildGraphConnections(){
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                if(board[i][j] != null) {
                    buildConnectionsFor1Field(board[i][j]);
                }
            }
        }
    }

    private void buildConnectionsFor1Field(Tile tile) {
        List<Tile> list = new ArrayList<>();
        List<Move> moveList = tile.getPiece().calculateLegalMoves(this);
        for (Move value: moveList){
            list.add(board[value.x][value.y]);
        }
        tile.setCanGoFromThis(list);
    }
}