package sk.tuke.gamestudio.game.pexeso.consoleui;

import sk.tuke.gamestudio.game.pexeso.core.BoardState;
import sk.tuke.gamestudio.game.pexeso.core.CardState;
import sk.tuke.gamestudio.game.pexeso.core.PexesoBoard;
import sk.tuke.gamestudio.game.pexeso.core.PexesoCard;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.*;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PexesoGameUI {
    public static final ScoreService scoreService = new ScoreServiceJDBC();
    public static final RatingsService ratingsService = new RatingsServiceJDBC();
    public static final CommentsService commentsService = new CommentsServiceJDBC();
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

        System.out.println("\n");
        System.out.println("You have won ... GG !!!");
        System.out.println("Number of tries ... " + tries);

        saveScore();

        askForRating();
        askForComment();
        printTopScores();
        printRatings();
        printComments();
    }

    private void saveScore() {
        scoreService.addScore(new Score(System.getProperty("user.name"), "pexeso", tries, new Date()));
    }


    public void playTurn() {
        cheatDisplayBoard();
        System.out.println("\n");
        displayBoard();

        int[] positions = null;

        positions = getInput();

        int row1 = positions[0];
        int col1 = positions[1];
        PexesoCard firstCard = pexesoBoard.getCard(row1, col1);
        boolean flip1 = pexesoBoard.flip(firstCard);

        displayBoard();

        positions = getInput();

        int row2 = positions[0];
        int col2 = positions[1];
        PexesoCard secondCard = pexesoBoard.getCard(row2, col2);
        boolean flip2 = pexesoBoard.flip(secondCard);

        displayBoard();

        if (flip1 && flip2) {
            pexesoBoard.compareCards(firstCard, secondCard);
        }
        System.out.println("Tries: " + tries);
    }

    public int[] getInput() {
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
        System.out.println("---------------------------------------");
        System.out.println("cheat board (not part of actual game..only for video)");
        System.out.println("---------------------------------------");
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
        System.out.println("---------------------------------");
    }

    public int[] processInput(Scanner scanner) {
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
                if (num < 0 || num > this.pexesoBoard.getSize() - 1) {
                    System.out.println("Error: Invalid input - Values must be between 0 and " + (this.pexesoBoard.getSize() - 1));
                    return null;
                }
                numbers[i] = num;
            }
            PexesoCard testsecondCard = pexesoBoard.getCard(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
            if (!pexesoBoard.flip(testsecondCard)) {
                return null;
            }
            pexesoBoard.flipBack(testsecondCard);

            return numbers;
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input - Please enter two digits with spaces between them");
            return null;
        }
    }

    private void printTopScores() {
        System.out.println("                      Top Scores                        ");
        System.out.println("-------------------------------------------------------");
        var scores = scoreService.getTopScores("pexeso");
        for (int i = 0; i < scores.size(); i++) {
            var score = scores.get(i);
            System.out.printf("%d. %s %d\n", (i + 1), score.getPlayer(), score.getPoints());
        }
        System.out.println("-------------------------------------------------------");
    }

    private void printRatings() {
        System.out.println("                      Past Ratings                        ");
        System.out.println("----------------------------------------------------------");
        var ratings = ratingsService.getTopRatings("pexeso");
        for (int i = 0; i < ratings.size(); i++) {
            var score = ratings.get(i);
            System.out.printf("%d. %s %d\n", (i + 1), score.getPlayer(), score.getRating());
        }
        System.out.println("------------------------------------------------------------");
    }

    private void printComments() {
        System.out.println("                      Past Comments                        ");
        System.out.println("----------------------------------------------------------");
        var comments = commentsService.getComments("pexeso");
        for (int i = 0; i < comments.size(); i++) {
            var comment = comments.get(i);
            System.out.printf("%s %s\n", comment.getPlayer(), comment.getComment());
        }
        System.out.println("-----------------------------------------------------------");
    }

    private void askForRating() {
        System.out.println("\n");
        System.out.println("Rate the game from 0-5 stars(write a number from 0 - 5)");
        int input = scanner.nextInt();
        if (!(input >= 0 && input <= 5)) {
            boolean validInput = false;
            while (!validInput) {
                System.out.println("U can only rate game between 0 - 5");
                input = scanner.nextInt();
                if ((input >= 0 && input <= 5)) {
                    validInput = true;
                }
            }
        }
        ratingsService.addRating(new Rating(System.getProperty("user.name"), "pexeso", input));
    }

    private void askForComment() {
        System.out.println("Would you like to leave a comment? (yes/no)");
        String answer = scanner.nextLine();
        while (!answer.equals("yes") && !answer.equals("no")) {
            System.out.println("Please enter a valid answer (yes/no):");
            answer = scanner.nextLine();
        }
        if (answer.equals("yes")) {
            System.out.println("Enter a comment:");
            String comment = scanner.nextLine();
            commentsService.addComment(new Comment(System.getProperty("user.name"), "pexeso", comment));
        }
    }

}
