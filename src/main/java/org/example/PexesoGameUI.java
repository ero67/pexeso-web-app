package org.example;

import java.util.Scanner;
import java.util.regex.Pattern;

public class PexesoGameUI {
    private final PexesoBoard pexesoBoard;
    Scanner scanner = new Scanner(System.in);
    private int tries;

    public PexesoGameUI(PexesoBoard pexesoBoard) {
        this.pexesoBoard = pexesoBoard;
    }

    public void play() {
        while (pexesoBoard.getBoardState() == BoardState.PLAYING) {
            tries++;
            playTurn();
            pexesoBoard.isFinished();
        }

        System.out.println("You have won ... GG !!!");
        System.out.println("Number of tries ... " + tries);

    }


    public void playTurn() {
        System.out.println("\n");
       /* cheatDisplayBoard();
        displayBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write 2 numbers of cards you want to flip in format: row1 col1 row2 col2");
        int row1 = scanner.nextInt();
        int col1 = scanner.nextInt();
        int row2 = scanner.nextInt();
        int col2 = scanner.nextInt();
*/
        displayBoard();
        boolean validInput = false;
        int[] positions = null;

        do {
            System.out.print("Enter two card positions (e.g. 0 1 2 3): ");
            positions = processInput(scanner);
            if (positions != null) {
                validInput = true;
            }
        } while (!validInput);

        int row1 = positions[0];
        int col1 = positions[1];
        int row2 = positions[2];
        int col2 = positions[3];

        PexesoCard firstCard = pexesoBoard.getCard(row1, col1);
        PexesoCard secondCard = pexesoBoard.getCard(row2, col2);

        pexesoBoard.flip(firstCard, secondCard);

        displayBoard();
        if (pexesoBoard.flip(firstCard, secondCard)) {
            pexesoBoard.compareCards(firstCard, secondCard);
        }

        System.out.println("Tries: " + tries);
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
                    System.out.print("*" + " ");
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
        String pattern = "^(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)$";
        Pattern regex = Pattern.compile(pattern);

        if (!regex.matcher(input).matches()) {
            System.out.println("Error: Invalid input - Please enter four digits with spaces between each number");
            return null;
        }

        String[] values = input.trim().split(" ");
        int[] numbers = new int[4];

        try {
            for (int i = 0; i < 4; i++) {
                int num = Integer.parseInt(values[i]);
                if (num < 0 || num > this.pexesoBoard.getSize()) {
                    System.out.println("Error: Invalid input - Values must be between 0 and "+(this.pexesoBoard.getSize()-1));
                    return null;
                }
                numbers[i] = num;

            }

            if (numbers[0] == numbers[2] && numbers[1] == numbers[3]){
                System.out.println("Error: You put coordinates of the 1 card twice");
                return null;
            }

            return numbers;
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input - Please enter four digits with spaces between each number");
            return null;
        }
    }
}
