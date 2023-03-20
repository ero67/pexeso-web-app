package sk.tuke.gamestudio;


import sk.tuke.gamestudio.service.*;

public class JDBCTEST {
    public static void main(String[] args) throws Exception {
        ScoreService service = new ScoreServiceJDBC();
        service.reset();
        //service.addScore(new Score("peter","pexeso",10,new Date()));
      //  service.addScore(new Score("peter","pexeso",11,new Date()));
        //service.addScore(new Score("peter","pexeso",12,new Date()));
       // service.addScore(new Score("peter","pexeso",13,new Date()));
      //  service.addScore(new Score("peter","pexeso",14,new Date()));

        var scores=service.getTopScores("pexeso");
        System.out.println(scores);

        RatingsService service1=new RatingsServiceJDBC();
        service1.reset();


        var sratings=service1.getTopRatings("pexeso");
        System.out.println(scores);


        CommentsService service2=new CommentsServiceJDBC();
        service2.reset();

       // service2.addComment(new Comment("erik","pexeso","Velmi dobra hra !"));
       // service2.addComment(new Comment("erik","pexeso","Velmi dobra hra !"));
       // service2.addComment(new Comment("erik","pexeso","Velmi dobra hra !"));
       // service2.addComment(new Comment("erik","pexeso","Velmi dobra hra !"));
    }
}
