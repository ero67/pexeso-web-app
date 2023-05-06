package sk.tuke.gamestudio.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.User;
import sk.tuke.gamestudio.game.pexeso.core.CardState;
import sk.tuke.gamestudio.game.pexeso.core.PexesoBoard;
import sk.tuke.gamestudio.game.pexeso.core.PexesoCard;
import sk.tuke.gamestudio.service.CommentsService;
import sk.tuke.gamestudio.service.RatingsService;
import sk.tuke.gamestudio.service.ScoreService;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/pexeso")
public class PexesoController {
    private PexesoBoard pexesoBoard = new PexesoBoard(3, 2);
    private List<PexesoCard> flippedCards = new ArrayList<>();
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CommentsService commentsService;
    @Autowired
    private RatingsService ratingsService;

    @Autowired
    @Lazy
    private UserController userController;

    @GetMapping
    public String pexeso(Model model) {
        model.addAttribute("pexesoController", this);
        /*model.addAttribute("scores",scoreService.getTopScores("pexeso"));*/
       /* model.addAttribute("comments",commentsService.getComments("pexeso"));*/
        return "pexeso";
    }

    @GetMapping("/new")
    public String newGame(Model model) {
        pexesoBoard = new PexesoBoard(4, 4);
        model.addAttribute("pexesoController", this);
        model.addAttribute("scores",scoreService.getTopScores("pexeso"));
        return "pexeso";
    }



    public String getHtmlPexesoBoard() {
        int rowCount = pexesoBoard.pexesoCardList.length;
        int columnCount = pexesoBoard.pexesoCardList[0].length;

        StringBuilder sb = new StringBuilder();
        //sb.append("<section class='memory-game'>\n");
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                PexesoCard card = pexesoBoard.getCard(row, col);

                sb.append("<div class='memory-card' data-row='" + row + "' data-col='" + col + "' onclick='flipCard(this)'>\n");
                //sb.append("<div class='memory-card' data-row='" + row + "' data-col='" + col + "' onclick='flipCard(event.target)'>\n");

                sb.append("<img class='front-face' src='/images/pexeso/" + getCardImg(card) + "' alt='" + getCardText(card) + "' />\n");
                sb.append("<img class='back-face' src='/images/pexeso/back.png"  + "' alt='back' />\n");
                sb.append("</div>\n");
            }
        }
        //sb.append("</section>\n");
        return sb.toString();
    }






    private String getCardClass(PexesoCard card) {
        switch (card.getState()) {
            case HIDDEN:
                return "hidden";
            case FACE_UP:
                return "face-up";
            case MATCHED:
                return "matched";
            default:
                throw new IllegalArgumentException("Not valid card state: " + card.getState());
        }
    }



    private String getCardText(PexesoCard card) {
        return card.getValue();
       /* switch (card.getState()) {
            case HIDDEN:
                return "?";
            case FACE_UP:
                return String.valueOf(card.getValue());
            case MATCHED:
                return String.valueOf(card.getValue());
            default:
                throw new IllegalArgumentException("Not valid card state: " + card.getState());
        }*/
    }
    /*private String getCardImg(PexesoCard card) {
        switch (card.getState()) {
            case HIDDEN:
                return "H.png";
            case FACE_UP:
                return String.valueOf(card.getValue()) + ".png";
            case MATCHED:
                return String.valueOf(card.getValue()) + ".png";
            default:
                throw new IllegalArgumentException("Not valid card state: " + card.getState());
        }
    }*/
    private String getCardImg(PexesoCard card) {
        return card.getValue() + ".png";
        }

    @GetMapping("/scores")
    public String highestScores(Model model) {
        model.addAttribute("scores",scoreService.getTopScores("pexeso"));
        return "highestscores";
    }

    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("comments",commentsService.getComments("pexeso"));
        return "comments";
    }


    /*@PostMapping("/ratings")
    public ResponseEntity<Void> rate(@RequestBody Map<String, Integer> ratingData, HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser != null) {
//            String game = "pexeso";
//            String player = loggedUser.getLogin();

                String game = loggedUser.getLogin();
                String player = "pexeso";

            int ratingValue = ratingData.get("rating");

            Rating rating = new Rating(game, player, ratingValue, new Date());
            ratingsService.setRating(rating);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }*/

   /* @GetMapping("/ratings")
    public String ratings(Model model, HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser != null) {
            model.addAttribute("userRating", ratingsService.getRating("pexeso", loggedUser.getLogin()));
        }
        model.addAttribute("averageRating", ratingsService.getAverageRating("pexeso"));
        return "rating";
    }*/

    @GetMapping("/ratings")
    public String ratings(Model model, HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser != null) {
            model.addAttribute("userRating", ratingsService.getRating("pexeso", loggedUser.getLogin()));
        }
        model.addAttribute("averageRating", ratingsService.getAverageRating("pexeso"));
        return "rating";
    }

    /*@GetMapping("/ratings")
    public String ratings(Model model, HttpSession session) {
        String game = "pexeso";
        model.addAttribute("averageRating", ratingsService.getAverageRating(game));
        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser != null) {
            model.addAttribute("userRating", ratingsService.getRating(loggedUser.getLogin(), game));
        } else {
            model.addAttribute("userRating", null);
        }
        return "rating";
    }*/








}
