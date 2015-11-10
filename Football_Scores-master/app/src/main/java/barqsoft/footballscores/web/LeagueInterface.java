package barqsoft.footballscores.web;

import java.util.List;

import barqsoft.footballscores.models.League;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Chris Hare on 11/7/2015.
 */
public interface LeagueInterface {

    @GET("/soccerseasons")
    List<League> fetchLeagues();

}
