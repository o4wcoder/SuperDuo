package barqsoft.footballscores.models;

import com.google.gson.annotations.SerializedName;

import barqsoft.footballscores.data.DBConstants;

/**
 * Created by Chris Hare on 10/28/2015.
 */
public class LeagueLink {

    @SerializedName(DBConstants.HREF)
    private String mLeagueLink;

    public String getLeagueLink() {
        return mLeagueLink;
    }
    public String getLeagueId() {
        return mLeagueLink.replace(DBConstants.SEASON_LINK,"");

    }
}
