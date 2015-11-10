package barqsoft.footballscores.models;

import com.google.gson.annotations.SerializedName;

import barqsoft.footballscores.data.DBConstants;

/**
 * Created by Chris Hare on 10/30/2015.
 */
public class FixtureLinks {

    @SerializedName(DBConstants.SOCCERSEASON)
    LeagueLink mLeagueLink;

    @SerializedName(DBConstants.SELF)
    MatchId mMatchId;

    public LeagueLink getLeagueLink() {
        return mLeagueLink;
    }

    public MatchId getMatchId() {
        return mMatchId;
    }
}
