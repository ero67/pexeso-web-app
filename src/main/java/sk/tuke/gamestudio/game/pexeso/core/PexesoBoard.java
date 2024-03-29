package sk.tuke.gamestudio.game.pexeso.core;

import java.util.ArrayList;
import java.util.Collections;

public class PexesoBoard {
    public final PexesoCard[][] pexesoCardList;
    private BoardState boardState=BoardState.PLAYING;




    public PexesoBoard(int numRow, int numCol ) {
        // Calculate the size of the board (rounded down to the nearest even number)
        int boardSize = numRow*numCol;

        // Calculate the number of cards needed (half the board size)
        int cardsNeeded = boardSize / 2;

        // Create an ArrayList to hold the values for the cards
        ArrayList<String> cardValues = new ArrayList<String>(cardsNeeded);

        // Add pairs of values to the ArrayList
        char letter = 'A';
        for (int i = 0; i < cardsNeeded; i++) {
            cardValues.add(Character.toString(letter));
            cardValues.add(Character.toString(letter));
            letter++;
        }
        // Shuffle the ArrayList of card values
        shuffle(cardValues);

        // Create the board
//        pexesoCardList = new PexesoCard[numRow][numCol];
//        int index = 0;
//        for (int row = 0; row < numRow; row++) {
//            for (int col = 0; col < numCol; col++) {
//                pexesoCardList[row][col] = new PexesoCard(cardValues.get(index));
//                index++;
//                if (index >= cardValues.size()) {
//                    index = 0;
//                }
//            }
//        }

        pexesoCardList = new PexesoCard[numRow][numCol];
        int index = 0;
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                pexesoCardList[row][col] = new PexesoCard(cardValues.get(index));
                index++;
                if (index >= cardValues.size()) {
                    index = 0;
                }
              //  if (row >= pexesoCardList.length || col >= pexesoCardList[row].length) {
                //    throw new IllegalArgumentException("Invalid row or column parameter for PexesoBoard");
                //}
            }
        }
    }

    void shuffle(ArrayList pictures){
        Collections.shuffle(pictures);
    }

    public PexesoCard getCard(int row, int col) {
        return pexesoCardList[row][col];
    }

//    public int getSize() {
//        return pexesoCardList.length;
//    }

    public int getSize(){
        return pexesoCardList.length * pexesoCardList[0].length;
    }

    public void setBoardState(BoardState state){
        this.boardState=state;
    }

    public BoardState getBoardState() {
        return boardState;
    }



    public boolean flip(PexesoCard firstCard) {
        if (firstCard.getState() == CardState.MATCHED) {
            return false;

        }else if(firstCard.getState()==CardState.FACE_UP){
            return false;
        }
        else {
            firstCard.setState(CardState.FACE_UP);
            return true;
        }
    }
    public void flipBack(PexesoCard firstCard) {

        if(firstCard.getState()==CardState.FACE_UP){
            firstCard.setState(CardState.HIDDEN);

        }

    }

    public void isFinished() {
        // int size = this.getSize() ;
        int numRow = pexesoCardList.length;
        int numCol = pexesoCardList[0].length;
        //System.out.println(size);
        int matchedPairs = 0;
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                PexesoCard card = this.getCard(row, col);
                if (card.getState() == CardState.MATCHED) {
                    matchedPairs++;
                } else if (card.getState() == CardState.HIDDEN) {
                    this.setBoardState(BoardState.PLAYING);
                    return;
                }
            }
        }
        if (matchedPairs == numRow * numCol) {
            this.setBoardState(BoardState.SOLVED);
        }
    }

    public boolean compareCards(PexesoCard firstCard,PexesoCard secondCard){

        if (firstCard.getValue().trim().equals(secondCard.getValue().trim())) {//testing if values of card are equal
            //  System.out.println("Cards MATCH ....keep going!!!");
            firstCard.setState(CardState.MATCHED);
            secondCard.setState(CardState.MATCHED);
            return true;
        } else {
            //System.out.println("Cards do not match...keep trying!!!");
            firstCard.setState(CardState.HIDDEN);
            secondCard.setState(CardState.HIDDEN);
            return false;
        }

    }


}
