package barqsoft.footballscores.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import barqsoft.footballscores.BuildConfig;
import barqsoft.footballscores.data.DBConstants;
import barqsoft.footballscores.models.Fixture;
import barqsoft.footballscores.models.FixtureLinks;
import barqsoft.footballscores.models.FixtureSearchResponse;
import barqsoft.footballscores.models.Goals;
import barqsoft.footballscores.models.League;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Chris Hare on 10/26/2015.
 */
public class DataManager implements DBConstants {

    /*********************************************************************/
    /*                          Constants                                */
    /*********************************************************************/
    private static final String TAG = DataManager.class.getSimpleName();

    private static final String FOOTBALL_BASE_URL = "http://api.football-data.org/alpha/";
    private static final String FOOTBALL_HEADER_KEY = "X-Auth-Token";


    private static DataManager sDataManager;
    private RestAdapter mBasicRestAdapter;


    /**
     * Static get method to ensure only one instance of the DataManger gets created
     * @return        Static reference to the DataManager
     */
    public static DataManager get() {

        if (sDataManager == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Goals.class,new GoalsDeserializer())
                    .registerTypeAdapter(FixtureLinks.class,new LinksDeserializer())
                    .create();

            RestAdapter basicRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(FOOTBALL_BASE_URL)
                    .setConverter(new GsonConverter(gson))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setRequestInterceptor(sRequestInterceptor)
                            .build();
            sDataManager = new DataManager(basicRestAdapter);
        }
        return sDataManager;
    }

    protected DataManager(RestAdapter basicRestAdapter) {
        mBasicRestAdapter = basicRestAdapter;
    }

    public static RequestInterceptor sRequestInterceptor = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
           request.addHeader(FOOTBALL_HEADER_KEY, BuildConfig.API_KEY);
         //  request.addQueryParam(PARAM_TIME_FRAME,TIME_FRAME);
        }
    };


    public List<Fixture> fetchFixtures(String timeFrame) {

        FixtureInterface fixtureInterface = mBasicRestAdapter.create(FixtureInterface.class);
        FixtureSearchResponse response = fixtureInterface.searchFixtures(timeFrame);
        return response.getFixtureList();
    }

    public List<League> fetchLeagues() {

        LeagueInterface leagueInterface = mBasicRestAdapter.create(LeagueInterface.class);

        return leagueInterface.fetchLeagues();


    }
}
