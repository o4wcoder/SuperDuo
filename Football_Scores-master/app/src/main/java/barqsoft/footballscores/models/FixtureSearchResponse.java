package barqsoft.footballscores.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import barqsoft.footballscores.data.DBConstants;

/**
 * Created by Chris Hare on 10/27/2015.
 */
public class FixtureSearchResponse {

    @SerializedName(DBConstants.FIXTURES)
    private List<Fixture> mFixtureList;

    public List<Fixture> getFixtureList() {
        return mFixtureList;
    }
}
