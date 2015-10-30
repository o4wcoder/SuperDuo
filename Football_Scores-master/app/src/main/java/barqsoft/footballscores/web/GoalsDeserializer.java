package barqsoft.footballscores.web;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import barqsoft.footballscores.data.DBConstants;
import barqsoft.footballscores.models.FixtureSearchResponse;
import barqsoft.footballscores.models.Goals;

/**
 * Created by Chris Hare on 10/28/2015.
 */
public class GoalsDeserializer implements JsonDeserializer<Goals> {

    private static final String TAG = GoalsDeserializer.class.getSimpleName();

    public Goals deserialize(
            JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {

       // Log.e(TAG,"Goals json: " + json.toString());
        return new Gson().fromJson(json,Goals.class);

    }


}
