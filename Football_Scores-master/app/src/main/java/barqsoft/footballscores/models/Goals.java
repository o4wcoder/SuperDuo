package barqsoft.footballscores.models;

import com.google.gson.annotations.SerializedName;

import barqsoft.footballscores.data.DBConstants;

/**
 * Created by Chris Hare on 10/28/2015.
 */
public class Goals {

    @SerializedName(DBConstants.HOME_GOALS)
    private String mHomeGoals;

    @SerializedName(DBConstants.AWAY_GOALS)
    private String mAwayGoals;

    public String getHomeGoals() {
        return mHomeGoals;
    }

    public String getAwayGoals() {
        return mAwayGoals;
    }
}
