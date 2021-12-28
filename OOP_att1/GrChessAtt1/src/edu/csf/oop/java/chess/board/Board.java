package edu.csf.oop.java.chess.board;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.pieces.*;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int BOARD_LENGTH = 10;
    private final Tile[][] board = new Tile[BOARD_LENGTH][BOARD_LENGTH];

    public Tile getTile(int i, int j){ return board[i][j];}

    public Board(){
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                board[i][j] = new Tile(i, j);
            }
        }
        board[0][0].setPiece(new Rook(Alliance.BLACK, 0, 0));
        board[0][1].setPiece(new Knight(Alliance.BLACK, 0, 1));
        board[0][2].setPiece(new Bishop(Alliance.BLACK, 0, 2));
        board[0][3].setPiece(new Queen(Alliance.BLACK, 0, 3));
        board[0][4].setPiece(new King(Alliance.BLACK, 0, 4));
        board[0][5].setPiece(new Giraffe(Alliance.BLACK, 0, 5));
        board[0][6].setPiece(new Vizier(Alliance.BLACK, 0, 6));
        board[0][7].setPiece(new Bishop(Alliance.BLACK, 0, 7));
        board[0][8].setPiece(new Knight(Alliance.BLACK, 0, 8));
        board[0][9].setPiece(new Rook(Alliance.BLACK, 0, 9));

        for (int i = 0; i < 4; i++) {
            board[1][i].setPiece(new Pawn(Alliance.BLACK, 1, i));
            board[1][i + 6].setPiece(new Pawn(Alliance.BLACK, 1, i + 6));
        }
        board[1][4].setPiece(new WM(Alliance.BLACK, 1, 4));
        board[1][5].setPiece(new WM(Alliance.BLACK, 1, 5));
        board[2][4].setPiece(new Pawn(Alliance.BLACK, 2, 4));
        board[2][5].setPiece(new Pawn(Alliance.BLACK, 2, 5));
        ////////////////
        board[9][9].setPiece(new Rook(Alliance.WHITE, 9, 9));
        board[9][8].setPiece(new Knight(Alliance.WHITE, 9, 8));
        board[9][7].setPiece(new Bishop(Alliance.WHITE, 9, 7));
        board[9][6].setPiece(new Queen(Alliance.WHITE, 9, 6));
        board[9][5].setPiece(new King(Alliance.WHITE, 9, 5));
        board[9][4].setPiece(new Giraffe(Alliance.WHITE, 9, 4));
        board[9][3].setPiece(new Vizier(Alliance.WHITE, 9, 3));
        board[9][2].setPiece(new Bishop(Alliance.WHITE, 9, 2));
        board[9][1].setPiece(new Knight(Alliance.WHITE, 9, 1));
        board[9][0].setPiece(new Rook(Alliance.WHITE, 9, 0));

        for (int i = 0; i < 4; i++) {
            board[8][i].setPiece(new Pawn(Alliance.WHITE, 8, i));
            board[8][i+6].setPiece(new Pawn(Alliance.WHITE, 8, i + 6));
        }
        board[8][4].setPiece(new WM(Alliance.WHITE, 8, 4));
        board[8][5].setPiece(new WM(Alliance.WHITE, 8, 5));
        board[7][4].setPiece(new Pawn(Alliance.WHITE, 7, 4));
        board[7][5].setPiece(new Pawn(Alliance.WHITE, 7, 5));
    }

    public void buildGraphConnections(){
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                if(board[i][j].getPiece() != null) {
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