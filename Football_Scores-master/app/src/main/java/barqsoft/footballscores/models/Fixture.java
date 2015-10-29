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
    private League mLeague;

    @SerializedName(DBConstants.MATCH_ID)
    private String mMatchId;

    @SerializedName(DBConstants.MATCH_DATE)
    private String mMatchDate;

    @SerializedName(DBConstants.HOME_TEAM)
    private String mHomeTeam;

    @SerializedName(DBConstants.AWAY_TEAM)
    private String mAwayTeam;

    @SerializedName(DBConstants.MATCH_DAY)
    private String mMatchDay;

    @SerializedName(DBConstants.RESULT)
    private Goals mGoals;


    @Override
    public String toString() {

        return  "League:     " + mLeague.getLeague() + "\n" +
                "Match ID:   " + mMatchId + "\n" +
                "Match Date: " + mMatchDate + "\n" +
                "Home Team:  " + mHomeTeam + "\n" +
                "Away Team:  " + mAwayTeam + "\n" +
                "Home Goals: " + mGoals.getHomeGoals() + "\n" +
                "Away Goals: " + mGoals.getAwayGoals() + "\n" +
                "Match Day:  " + mMatchDay + "\n";

    }
    public ContentValues getContentValues() {

        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.ScoresEntry.DATE_COL,mMatchDate);
        cv.put(DatabaseContract.ScoresEntry.HOME_COL,mHomeTeam);
        cv.put(DatabaseContract.ScoresEntry.AWAY_COL,mAwayTeam);
        cv.put(DatabaseContract.ScoresEntry.HOME_GOALS_COL,mGoals.getHomeGoals());
        cv.put(DatabaseContract.ScoresEntry.AWAY_GOALS_COL,mGoals.getAwayGoals());
        cv.put(DatabaseContract.ScoresEntry.MATCH_DAY_COL,mMatchDay);
        cv.put(DatabaseContract.ScoresEntry.MATCH_ID_COL,mMatchId);
        cv.put(DatabaseContract.ScoresEntry.LEAGUE_COL,mLeague.getLeague());

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



}
