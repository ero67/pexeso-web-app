package org.tuke.consoleui;

import org.tuke.core.BoardState;
import org.tuke.core.CardState;
import org.tuke.core.PexesoBoard;
import org.tuke.core.PexesoCard;
import org.tuke.entity.Score;
import org.tuke.service.RatingsService;
import org.tuke.service.RatingsServiceJDBC;
import org.tuke.service.ScoreService;
import org.tuke.service.ScoreServiceJDBC;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PexesoGameUI {
    public static final ScoreService scoreService= new ScoreServiceJDBC();
    public static final RatingsService ratingsService= new RatingsServiceJDBC();
    private final PexesoBoard pexesoBoard;
    Scanner scanner = new Scanner(System.in);
    private int tries;

    public PexesoGameUI(PexesoBoard pexesoBoard) {
        this.pexesoBoard = pexesoBoard;
    }

    public void play() {
        printTopScores();
        while (pexesoBoard.getBoardState() == BoardState.PLAYING) {
            tries++;
            playTurn();
            pexesoBoard.isFinished();
        }

        System.out.println("You have won ... GG !!!");
        System.out.println("Number of tries ... " + tries);
        saveScore();
        printRatings();

    }

    private void saveScore() {
        scoreService.addScore(new Score(System.getProperty("user.name"),"pexeso",tries,new Date()));
    }


    public void playTurn() {
        cheatDisplayBoard();
        System.out.println("\n");
        displayBoard();

        int[] positions = null;

        positions = getPositions();

        int row1 = positions[0];int col1 = positions[1];
        PexesoCard firstCard = pexesoBoard.getCard(row1, col1);
        boolean flip1= pexesoBoard.flip(firstCard);

        displayBoard();

        positions=getPositions();

        int row2 = positions[0];int col2 = positions[1];
        PexesoCard secondCard = pexesoBoard.getCard(row2, col2);
        boolean flip2= pexesoBoard.flip(secondCard);

        displayBoard();

        if (flip1 && flip2) {
            pexesoBoard.compareCards(firstCard, secondCard);
        }
        System.out.println("Tries: " + tries);
    }

    private int[] getPositions() {
        int[] positions;
        while (true) {
            System.out.print("Enter two card positions (e.g. 0 1 2 3): ");
            positions = processInput(scanner);
            if (positions != null) {
                break;
            }
        }
        return positions;
    }


    private void displayBoard() {
        int size = pexesoBoard.getSize();
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print("_" + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < size; j++) {
                PexesoCard card = pexesoBoard.getCard(i, j);
                if (card.getState() == CardState.FACE_UP || card.getState() == CardState.MATCHED) {
                    System.out.print(card.getValue() + " ");
                } else {
                    System.out.print("%" + " ");
                }
            }
            System.out.println();
        }
    }


    private void cheatDisplayBoard() {
        int size = pexesoBoard.getSize();
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print("_" + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < size; j++) {
                PexesoCard card = pexesoBoard.getCard(i, j);
                    System.out.print(card.getValue() + " ");

            }
            System.out.println();
        }
    }

    private int[] processInput(Scanner scanner) {
        String input = scanner.nextLine();
        String pattern = "^(\\d+)\\s+(\\d+)$";
        Pattern regex = Pattern.compile(pattern);

        if (!regex.matcher(input).matches()) {
            System.out.println("Error: Invalid input - Please enter two digits with spaces between them");
            return null;
        }

        String[] values = input.trim().split(" ");
        int[] numbers = new int[2];

        try {
            for (int i = 0; i < 2; i++) {
                int num = Integer.parseInt(values[i]);
                if (num < 0 || num > this.pexesoBoard.getSize()-1) {
                    System.out.println("Error: Invalid input - Values must be between 0 and "+(this.pexesoBoard.getSize()-1));
                    return null;
                }
                numbers[i] = num;
            }
            PexesoCard testsecondCard=pexesoBoard.getCard(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
            if(!pexesoBoard.flip(testsecondCard)){
                return null;
            }
            pexesoBoard.flipBack(testsecondCard);

            return numbers;
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input - Please enter two digits with spaces between them");
            return null;
        }
    }
    private void printTopScores(){
        System.out.println("-------------------------------------------------------");
        var scores=scoreService.getTopScores("pexeso");
        for (int i=0;i<scores.size();i++){
            var score=scores.get(i);
            System.out.printf("%d. %s %d\n",(i+1),score.getPlayer(),score.getPoints());
        }
        System.out.println("-------------------------------------------------------");
    }

    private void printRatings(){
        System.out.println("                      Past Ratings                        ");
        System.out.println("----------------------------------------------------------");
        var ratings=ratingsService.getTopRatings("pexeso");
        for (int i=0;i<ratings.size();i++){
            var score=ratings.get(i);
            System.out.printf("%d. %s %d\n",(i+1),score.getPlayer(),score.getRating());
        }
    }
}
