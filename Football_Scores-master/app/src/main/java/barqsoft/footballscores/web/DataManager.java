package barqsoft.footballscores.web;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import barqsoft.footballscores.BuildConfig;
import barqsoft.footballscores.data.DBConstants;
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

    private static DataManager sDataManager;
    private RestAdapter mBasicRestAdapter;

    /**
     * Static get method to ensure only one instance of the DataManger gets created
     * @param context Context of calling activity
     * @return        Static reference to the DataManager
     */
    public static DataManager get(Context context) {

        if (sDataManager == null) {
            Gson gson = new GsonBuilder().create();

            RestAdapter basicRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(DBConstants.FOOTBALL_BASE_URL)
                    .setConverter(new GsonConverter(gson))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
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
           request.addHeader(DBConstants.FOOTBALL_HEADER_KEY, BuildConfig.API_KEY);
        }
    };
}
