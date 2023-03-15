package org.example;

public class Main {
    public static void main(String[] args) {
        PexesoBoard board=new PexesoBoard(4);
        PexesoGameUI game=new PexesoGameUI(board);
        game.play();
    }
}