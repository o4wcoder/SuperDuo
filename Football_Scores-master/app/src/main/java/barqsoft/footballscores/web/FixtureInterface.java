package barqsoft.footballscores.web;

import barqsoft.footballscores.models.FixtureSearchResponse;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Chris Hare on 10/27/2015.
 */
public interface FixtureInterface {

    String PARAM_TIME_FRAME = "timeFrame"; //Time Frame parameter to determine days

    @GET("/fixtures")
    FixtureSearchResponse searchFixtures(@Query(PARAM_TIME_FRAME) String timeFrame);
}
