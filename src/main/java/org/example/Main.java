package org.example;

import org.example.consoleui.PexesoGameUI;
import org.example.core.PexesoBoard;

public class Main {
    public static void main(String[] args) {
        PexesoBoard board=new PexesoBoard(6);
        PexesoGameUI game=new PexesoGameUI(board);
        game.play();
    }
}