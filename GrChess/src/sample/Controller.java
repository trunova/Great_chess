package sample;

import edu.csf.oop.java.chess.Alliance;
import edu.csf.oop.java.chess.Game;
import edu.csf.oop.java.chess.board.Board;
import edu.csf.oop.java.chess.pieces.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    GridPane gridPane;

    private Button[][] buttons;
    public static Game game = new Game();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new Button[10][10];
    }

    @FXML
    public void handleStartBtnClick() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = but(i, j);

                String beigeStyle = "-fx-background-color: #e3d2af;",
                        brownStyle = "-fx-background-color: #61523c;";

                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        buttons[i][j].setStyle(beigeStyle);
                    } else buttons[i][j].setStyle(brownStyle);
                } else {
                    if (j % 2 != 0) {
                        buttons[i][j].setStyle(beigeStyle);
                    } else {
                        buttons[i][j].setStyle(brownStyle);
                    }
                }
                buttons[i][j].setPrefSize(60.0, 60.0);
                gridPane.add(buttons[i][j], j, i);
            }
        }
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 10; j++) {
//                piecesName(i, j);
//                piecesName(i+8, j);
//            }
//        }
//        piecesName(2, 4);
//        piecesName(2, 5);
//        piecesName(7, 4);
//        piecesName(7, 5);

    }

    private void piecesNames(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setText(name2(game.getBoard().getTile(i, j).getPiece()));
            }
        }
    }

    private static String name2(Piece piece){
        String res = "";
        if(piece == null) return res;
        if(piece.getPieceAlliance() == Alliance.WHITE) {
            res += "white\n   ";
        }else{
            res += "black\n   ";
        }
        if(piece instanceof Pawn) return res + "pawn";
        if(piece instanceof Knight) return res + "knight";
        if(piece instanceof King) return res + "king";
        if(piece instanceof Queen) return res + "queen";
        if(piece instanceof Bishop) return res + "bishop";
        if(piece instanceof Giraffe) return res + "giraffe";
        if(piece instanceof Rook) return res + "rook";
        if(piece instanceof Vizier) return res + "vizier";
        if(piece instanceof WM) return res + "wm";
        else return " ";
    }

    private Button but(int i, int j){
        Button b = new Button(name2(game.getBoard().getTile(i, j).getPiece()));
        b.setOnMouseReleased(event ->  {
            game.MouseClick(game.getBoard().getTile(i, j));
            piecesNames();
        });
        return b;
    }
}
