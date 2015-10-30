package barqsoft.footballscores.web;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import barqsoft.footballscores.models.Links;

/**
 * Created by Chris Hare on 10/30/2015.
 */
public class LinksDeserializer  implements JsonDeserializer<Links> {

    private static final String TAG = LinksDeserializer.class.getSimpleName();

    public Links deserialize(
            JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        Log.e(TAG, "Links json: " + json.toString());
        return new Gson().fromJson(json,Links.class);

    }
}
