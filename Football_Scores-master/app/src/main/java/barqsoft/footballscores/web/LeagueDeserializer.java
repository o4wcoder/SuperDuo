package barqsoft.footballscores.web;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import barqsoft.footballscores.data.DBConstants;
import barqsoft.footballscores.models.League;

/**
 * Created by Chris Hare on 10/28/2015.
 */
public class LeagueDeserializer implements JsonDeserializer<League> {

    private static final String TAG = LeagueDeserializer.class.getSimpleName();

    public League deserialize(
            JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {


        JsonElement jsonElement = json.getAsJsonObject().get(DBConstants.LEAGUE);
        String strLeague = jsonElement.getAsString();
        strLeague = strLeague.replace(DBConstants.SEASON_LINK,"");
        Log.e(TAG,"LEAGUE: " + strLeague);
        return new Gson().fromJson(json,League.class);

    }
}
