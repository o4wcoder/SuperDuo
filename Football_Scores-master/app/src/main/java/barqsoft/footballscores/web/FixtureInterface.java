package barqsoft.footballscores.web;

import barqsoft.footballscores.models.FixtureSearchResponse;
import retrofit.http.GET;

/**
 * Created by Chris Hare on 10/27/2015.
 */
public interface FixtureInterface {

    @GET("/fixtures")
    FixtureSearchResponse searchFixtures();
}
