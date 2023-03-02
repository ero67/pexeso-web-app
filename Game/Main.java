package Game.Game;

public class Main {
    public static void main(String[] args) {
        PexesoBoard board=new PexesoBoard(10);
        PexesoGameUI game=new PexesoGameUI(board);
        game.play();
    }
}