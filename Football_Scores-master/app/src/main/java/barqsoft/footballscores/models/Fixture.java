package barqsoft.footballscores.models;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import barqsoft.footballscores.data.DBConstants;
import barqsoft.footballscores.data.DatabaseContract;

/**
 * Created by Chris Hare on 10/27/2015.
 */
public class Fixture implements DBConstants{

    @SerializedName(DBConstants.LEAGUE)
    private String mLeague;

    @SerializedName(DBConstants.MATCH_ID)
    private String mMatchId;
    
    @SerializedName(DBConstants.MATCH_DATE)
    private String mMatchDate;

    @SerializedName(DBConstants.HOME_TEAM)
    private String mHomeTeam;

    @SerializedName(DBConstants.AWAY_TEAM)
    private String mAwayTeam;

    @SerializedName(DBConstants.HOME_GOALS)
    private String mHomeGoals;

    @SerializedName(DBConstants.AWAY_GOALS)
    private String mAwayGoals;

    @SerializedName(DBConstants.MATCH_DAY)
    private String mMatchDay;

    public ContentValues getContentValues() {

        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.ScoresEntry.DATE_COL,mMatchDate);
        cv.put(DatabaseContract.ScoresEntry.HOME_COL,mHomeTeam);
        cv.put(DatabaseContract.ScoresEntry.AWAY_COL,mAwayTeam);
        cv.put(DatabaseContract.ScoresEntry.HOME_GOALS_COL,mHomeGoals);
        cv.put(DatabaseContract.ScoresEntry.AWAY_GOALS_COL,mAwayGoals);

        return cv;
    }
    public String getmMatchDay() {
        return mMatchDay;
    }

    public String getmMatchDate() {
        return mMatchDate;
    }

    public String getmHomeTeam() {
        return mHomeTeam;
    }

    public String getmAwayTeam() {
        return mAwayTeam;
    }

    public String getmHomeGoals() {
        return mHomeGoals;
    }

    public String getmAwayGoals() {
        return mAwayGoals;
    }


}
