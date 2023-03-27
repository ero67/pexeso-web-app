package sk.tuke.gamestudio.game.pexeso;

import sk.tuke.gamestudio.game.pexeso.consoleui.PexesoGameUI;
import sk.tuke.gamestudio.game.pexeso.core.PexesoBoard;

public class Main {
    public static void main(String[] args) {

        PexesoBoard board = new PexesoBoard(2);
        PexesoGameUI game = new PexesoGameUI(board);
        game.play();
    }
}