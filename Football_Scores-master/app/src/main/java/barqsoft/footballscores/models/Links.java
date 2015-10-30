package barqsoft.footballscores.models;

import com.google.gson.annotations.SerializedName;

import barqsoft.footballscores.data.DBConstants;

/**
 * Created by Chris Hare on 10/30/2015.
 */
public class Links {

    @SerializedName(DBConstants.LEAGUE)
    League mLeague;

    @SerializedName(DBConstants.MATCH_ID)
    MatchId mMatchId;

    public League getLeague() {
        return mLeague;
    }

    public MatchId getMatchId() {
        return mMatchId;
    }
}
