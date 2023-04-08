package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.game.pexeso.consoleui.PexesoGameUI;
import sk.tuke.gamestudio.game.pexeso.core.PexesoBoard;
import sk.tuke.gamestudio.service.*;

@SpringBootApplication
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
        pattern = "sk.tuke.gamestudio.server.*"))

public class SpringClient {
    public static void main(String[] args) {
        //SpringApplication.run(SpringClient.class).;
        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
    }
   // @Bean
    public CommandLineRunner runner(PexesoGameUI console){
        return s -> console.play();
    }

    @Bean
    public PexesoGameUI console(){
        return new PexesoGameUI(new PexesoBoard(2));
    }

   /* @Bean
    public ScoreService scoreService(){
        return new ScoreServiceJPA();
    }*/
    @Bean
    public ScoreService scoreService() {
       return new ScoreServiceRestClient();
   }

    @Bean
    public CommentsService commentsService(){
        return new CommentServiceRestClient();
    }

    @Bean
    public RatingsService ratingsService(){
        return new RatingServiceRestClient();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
