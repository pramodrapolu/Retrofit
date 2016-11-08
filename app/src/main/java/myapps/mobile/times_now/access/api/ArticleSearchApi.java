package myapps.mobile.times_now.access.api;

import myapps.mobile.times_now.access.models.Articles;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ArticleSearchApi {

//    http://api.nytimes.com/svc/search/v2/articlesearch.json?q=obama&api-key=a8457610b68381085a3fff38d6a36337:6:74255139
    @GET("/svc/search/v2/articlesearch.json")
    void getArticles(
            @Query("q") String searchTerm,
            @Query("api-key") String apiKey,
            Callback<Articles> callback
    );
}
