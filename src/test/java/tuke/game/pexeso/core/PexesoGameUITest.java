package tuke.game.pexeso.core;

import org.tuke.core.BoardState;
import org.tuke.core.CardState;
import org.tuke.core.PexesoBoard;
import org.tuke.core.PexesoCard;

import static org.junit.jupiter.api.Assertions.*;

class PexesoGameUITest {

    @org.junit.jupiter.api.Test
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


}