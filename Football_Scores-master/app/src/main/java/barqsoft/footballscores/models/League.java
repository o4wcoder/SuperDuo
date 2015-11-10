package barqsoft.footballscores.models;

import com.google.gson.annotations.SerializedName;

import barqsoft.footballscores.data.DBConstants;

/**
 * Created by Chris Hare on 11/7/2015.
 */
public class League {

    @SerializedName(DBConstants.LINKS)
    private SoccerSeasonLinks mSoccerSeasonLinks;

    @SerializedName(DBConstants.LEAGUE_CAPTION)
    String mCaption;

    public String getCaption() {
        return mCaption;
    }

    public SoccerSeasonLinks getLinks() {
        return mSoccerSeasonLinks;
    }

    public String getLeagueId() {
        return mSoccerSeasonLinks.getLeagueLink().getLeagueId();
    }
}
