package barqsoft.footballscores.models;

import com.google.gson.annotations.SerializedName;

import barqsoft.footballscores.data.DBConstants;

/**
 * Created by Chris Hare on 10/30/2015.
 */
public class MatchId {

    @SerializedName(DBConstants.HREF)
    String mMatchId;

    public String getMatchId() {
        return mMatchId.replace(DBConstants.MATCH_LINK,"");
    }
}
