package tuke.game.pexeso.core;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.game.pexeso.consoleui.PexesoGameUI;
import sk.tuke.gamestudio.game.pexeso.core.BoardState;
import sk.tuke.gamestudio.game.pexeso.core.CardState;
import sk.tuke.gamestudio.game.pexeso.core.PexesoBoard;
import sk.tuke.gamestudio.game.pexeso.core.PexesoCard;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PexesoGameUITest {

    @Test
    public void testGameEndsWhenAllPairsFound() {
        // create a new Pexeso game
       PexesoBoard pexesoBoard=new PexesoBoard(2);

        // randomly flip all cards to get all pairs
        for (int i = 0; i < pexesoBoard.getSize(); i++) {
            for (int j = 0; j < pexesoBoard.getSize(); j++) {
                PexesoCard card = pexesoBoard.getCard(i, j);
                card.setState(CardState.MATCHED);
            }
        }
        pexesoBoard.isFinished();


        // ensure that the game state is now FINISHED
        assertEquals(BoardState.SOLVED, pexesoBoard.getBoardState());
    }
    @Test
    public void testFlipCardFuntion(){
        PexesoBoard pexesoBoard = new PexesoBoard(2);
        PexesoCard card=pexesoBoard.getCard(0,0);
        boolean flipped=pexesoBoard.flip(card);
        assertEquals(true,flipped);
        assertEquals(card.getState(),CardState.FACE_UP);
    }

    @Test
    public void testCompareCards(){
        PexesoBoard pexesoBoard=new PexesoBoard(2);

        PexesoCard card1=new PexesoCard("A");
        PexesoCard card2=new PexesoCard("A");

        boolean compared= pexesoBoard.compareCards(card1,card2);

        assertEquals(true,compared);

    }

    @Test
    public void testCompareCardsWrong(){
        PexesoBoard pexesoBoard=new PexesoBoard(2);

        PexesoCard card1=new PexesoCard("A");
        PexesoCard card2=new PexesoCard("B");

        boolean compared= pexesoBoard.compareCards(card1,card2);

        assertEquals(false,compared);

    }



    @Test
    public void testProcessInputValid() {
        // set up input
        ByteArrayInputStream inputStream = new ByteArrayInputStream("0 1".getBytes());
        System.setIn(inputStream);
        Scanner scanner = new Scanner(System.in);
        PexesoBoard pexesoBoard=new PexesoBoard(2);
        PexesoGameUI pexesoGame=new PexesoGameUI(pexesoBoard);
        // execute
        int[] result = pexesoGame.processInput(scanner);

        // check
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(0, result[0]);
        assertEquals(1, result[1]);
    }

    @Test
    public void testProcessInputInvalid() {
        // set up input
        ByteArrayInputStream inputStream = new ByteArrayInputStream("invalid input".getBytes());
        System.setIn(inputStream);
        Scanner scanner = new Scanner(System.in);
        PexesoBoard pexesoBoard=new PexesoBoard(2);
        PexesoGameUI pexesoGame=new PexesoGameUI(pexesoBoard);
        // execute
        int[] result = pexesoGame.processInput(scanner);

        // check
        assertNull(result);
    }











}