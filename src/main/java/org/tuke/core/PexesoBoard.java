package org.tuke.core;

import java.util.ArrayList;
import java.util.Collections;

public class PexesoBoard {
    public final PexesoCard[][] pexesoCardList;
    private BoardState boardState=BoardState.PLAYING;




    public PexesoBoard(int size) {
        // Calculate the size of the board (rounded down to the nearest even number)
        int boardSize = size / 2 * 2;

        // Calculate the number of cards needed (half the board size)
        int cardsNeeded = boardSize * boardSize / 2;

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
        pexesoCardList = new PexesoCard[boardSize][boardSize];
        int index = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                pexesoCardList[i][j] = new PexesoCard(cardValues.get(index));
                index++;
                if (index >= cardValues.size()) {
                    index = 0;
                }
            }
        }
    }

    void shuffle(ArrayList pictures){
        Collections.shuffle(pictures);
    }

    public PexesoCard getCard(int row, int col) {
        return pexesoCardList[row][col];
    }

    public int getSize() {
        return pexesoCardList.length;
    }

    public void setBoardState(BoardState state){
        this.boardState=state;
    }

    public BoardState getBoardState() {
        return boardState;
    }



    public boolean flip(PexesoCard firstCard) {
        if (firstCard.getState() == CardState.MATCHED) {
            System.out.println("Card is already MATCHED !!!");
            return false;
        }else if(firstCard.getState()==CardState.FACE_UP){
            System.out.println("Card is already flipped");
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
        int size = this.getSize() ;
        //System.out.println(size);
        int matchedPairs = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                PexesoCard card = this.getCard(i, j);
                if (card.getState() == CardState.MATCHED) {
                    matchedPairs++;
                } else if (card.getState() == CardState.HIDDEN) {
                    this.setBoardState(BoardState.PLAYING);
                    return;
                }
            }
        }
        if (matchedPairs == size * size) {
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
            System.out.println("Cards do not match...keep trying!!!");
            firstCard.setState(CardState.HIDDEN);
            secondCard.setState(CardState.HIDDEN);
            return false;
        }

    }


}
