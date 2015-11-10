package barqsoft.footballscores.web;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import barqsoft.footballscores.models.FixtureLinks;

/**
 * Created by Chris Hare on 10/30/2015.
 */
public class LinksDeserializer  implements JsonDeserializer<FixtureLinks> {

    private static final String TAG = LinksDeserializer.class.getSimpleName();

    public FixtureLinks deserialize(
            JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

       // Log.e(TAG, "FixtureLinks json: " + json.toString());
        return new Gson().fromJson(json,FixtureLinks.class);

    }
}
