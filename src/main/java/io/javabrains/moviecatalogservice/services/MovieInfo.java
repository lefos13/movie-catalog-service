package io.javabrains.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        //for each movie id, call movie info service and get details
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);

        //Put them all together
        return new CatalogItem(movie.getName(),movie.getOverview(), rating.getRating());
    }

    //fallback method
    public CatalogItem getFallbackCatalogItem(Rating rating) {
        CatalogItem catalogItem = new CatalogItem("no name","no desc", 0);
        return catalogItem;
    }
}
