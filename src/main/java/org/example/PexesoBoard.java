package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class PexesoBoard {
    public final ArrayList <PexesoCard> pexesoCardList;
    private BoardState boardState=BoardState.PLAYING;


    public PexesoBoard(int size) {
        pexesoCardList=new ArrayList<>(size);
        ArrayList<String> pictures = new ArrayList<>();
        char letter='A';
        for (int i = 0; i < size / 2; i++) {
            pictures.add(String.valueOf(letter));
            pictures.add(String.valueOf(letter));
            letter++;
        }
        shuffle(pictures);
        for (String picture : pictures) {
            pexesoCardList.add(new PexesoCard(picture));
        }
    }

    void shuffle(ArrayList pictures){
        Collections.shuffle(pictures);
    }

    public PexesoCard getCard(int index) {
        return pexesoCardList.get(index);
    }

    public int getSize() {
        return pexesoCardList.size();
    }


    public void setBoardState(BoardState state){
        this.boardState=state;
    }

    public BoardState getBoardState() {
        return boardState;
    }
}
