package org.tuke;

import org.tuke.consoleui.PexesoGameUI;
import org.tuke.core.PexesoBoard;

public class Main {
    public static void main(String[] args) {
        PexesoBoard board=new PexesoBoard(2);
        PexesoGameUI game=new PexesoGameUI(board);
        game.play();
    }
}