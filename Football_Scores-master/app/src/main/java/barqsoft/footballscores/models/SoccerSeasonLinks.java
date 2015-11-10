package barqsoft.footballscores.models;

import com.google.gson.annotations.SerializedName;

import barqsoft.footballscores.data.DBConstants;

/**
 * Created by Chris Hare on 10/30/2015.
 */
public class SoccerSeasonLinks {

    @SerializedName(DBConstants.SELF)
    LeagueLink mLeagueLink;

    public LeagueLink getLeagueLink() {
        return mLeagueLink;
    }


}
