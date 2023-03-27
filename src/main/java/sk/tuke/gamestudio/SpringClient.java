package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.game.pexeso.consoleui.PexesoGameUI;
import sk.tuke.gamestudio.game.pexeso.core.PexesoBoard;
import sk.tuke.gamestudio.service.*;

@SpringBootApplication
@Configuration
public class SpringClient {
    public static void main(String[] args) {
        SpringApplication.run(SpringClient.class);
    }
    @Bean
    public CommandLineRunner runner(PexesoGameUI console){
        return s -> console.play();
    }

    @Bean
    public PexesoGameUI console(){
        return new PexesoGameUI(new PexesoBoard(2));
    }

    @Bean
    public ScoreService scoreService(){
        return new ScoreServiceJPA();
    }

    @Bean
    public CommentsService commentsService(){
        return new CommentsServiceJPA();
    }

    @Bean
    public RatingsService ratingsService(){
        return new RatingServiceJPA();
    }
}
