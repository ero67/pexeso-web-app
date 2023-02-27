package Game;

import java.util.Scanner;

public class PexesoGameUI {
    private final PexesoBoard pexesoBoard;

    private int tries;

    public PexesoGameUI(PexesoBoard pexesoBoard) {
        this.pexesoBoard = pexesoBoard;
        this.tries = tries;

    }

    public void play() {
        while (pexesoBoard.getBoardState() == BoardState.PLAYING) {
            tries++;
            playTurn();
            isFinished();
        }

        System.out.println("You have won ... GG !!!");
        System.out.println("Number of tries ... " + tries);

    }

    public void playTurn() {
        cheatDisplayBoard();
        System.out.println("\n");
        displayBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write 2 numbers of cards you want to flip in format:X Y");
        int firstIndex = scanner.nextInt();
        int secondIndex = scanner.nextInt();

        PexesoCard firstCard = pexesoBoard.getCard(firstIndex);
        PexesoCard secondCard = pexesoBoard.getCard(secondIndex);

        flip(firstCard,secondCard);

        displayBoard();
        if (!flip(firstCard,secondCard)) {
            compareCards(firstCard,secondCard);
        }

        System.out.println("Tries: " + tries);


    }

    private void displayBoard() {
        for (int i = 0; i < pexesoBoard.getSize(); i++) {
            System.out.print(i + " ");
            PexesoCard card = pexesoBoard.getCard(i);
            if (card.getState() == CardState.FACE_UP) {
                System.out.println(card.getValue());
            } else if (card.getState() == CardState.MATCHED) {
                System.out.println(card.getValue());
            } else {
                System.out.println("*");
            }

        }
    }

    private void cheatDisplayBoard() {
        for (int i = 0; i < pexesoBoard.getSize(); i++) {
            System.out.print(i + " ");
            PexesoCard card = pexesoBoard.getCard(i);
            System.out.println(card.getValue());
        }
    }


    private void isFinished() {
        for (PexesoCard card : pexesoBoard.pexesoCardList) {
            if (card.getState() == CardState.HIDDEN) {
                pexesoBoard.setBoardState(BoardState.PLAYING);
                return;
            }
        }

        pexesoBoard.setBoardState(BoardState.SOLVED);
    }

    private void compareCards(PexesoCard firstCard,PexesoCard secondCard){

        if (firstCard.getValue().trim().equals(secondCard.getValue().trim())) {//testing if values of card are equal
            System.out.println("Cards MATCH ....keep going!!!");
            firstCard.setState(CardState.MATCHED);
            secondCard.setState(CardState.MATCHED);
        } else {
            System.out.println("Cards do not match...keep trying!!!");
            firstCard.setState(CardState.HIDDEN);
            secondCard.setState(CardState.HIDDEN);
        }

    }
    private boolean flip(PexesoCard firstCard, PexesoCard secondCard){
        if(firstCard.getState()==CardState.MATCHED || secondCard.getState()==CardState.MATCHED){
            System.out.println("One of the cards is already MATCHED !!!");
            return true;
        }
        else{
            firstCard.setState(CardState.FACE_UP);
            secondCard.setState((CardState.FACE_UP));
            return false;
        }
    }
}
