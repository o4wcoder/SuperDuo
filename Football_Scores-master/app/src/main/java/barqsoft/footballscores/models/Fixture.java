package barqsoft.footballscores.models;

import android.content.ContentValues;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import barqsoft.footballscores.data.DBConstants;
import barqsoft.footballscores.data.DatabaseContract;

/**
 * Created by Chris Hare on 10/27/2015.
 */
public class Fixture implements DBConstants{

    private static final String TAG = Fixture.class.getSimpleName();

    @SerializedName(DBConstants.LINKS)
    private FixtureLinks mFixtureLinks;

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

    private String mTime;

    private League mLeague;

    @Override
    public String toString() {

        return  "\n" +
                "LeagueLink: " + mFixtureLinks.getLeagueLink().getLeagueLink() + "\n" +
                "LeagueId:   " + mFixtureLinks.getLeagueLink().getLeagueId() + "\n" +
                "LeagueName: " + mLeague.getCaption() + "\n" +
                "Match ID:   " + mFixtureLinks.getMatchId().getMatchId() + "\n" +
                "Match Date: " + getMatchDate() + "\n" +
                "Home Team:  " + mHomeTeam + "\n" +
                "Away Team:  " + mAwayTeam + "\n" +
                "Home Goals: " + mGoals.getHomeGoals() + "\n" +
                "Away Goals: " + mGoals.getAwayGoals() + "\n" +
                "Match Day:  " + mMatchDay + "\n" +
                "Time:       " + getTime() + "\n";

    }
    public ContentValues getContentValues() {

        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.ScoresEntry.DATE_COL,getMatchDate());
        cv.put(DatabaseContract.ScoresEntry.HOME_COL,mHomeTeam);
        cv.put(DatabaseContract.ScoresEntry.AWAY_COL,mAwayTeam);
        cv.put(DatabaseContract.ScoresEntry.HOME_GOALS_COL,mGoals.getHomeGoals());
        cv.put(DatabaseContract.ScoresEntry.AWAY_GOALS_COL,mGoals.getAwayGoals());
        cv.put(DatabaseContract.ScoresEntry.MATCH_DAY_COL,mMatchDay);
        cv.put(DatabaseContract.ScoresEntry.TIME_COL,getTime());
        cv.put(DatabaseContract.ScoresEntry.MATCH_ID_COL,getMatchId());
        cv.put(DatabaseContract.ScoresEntry.LEAGUE_COL,getLeagueId());
        cv.put(DatabaseContract.ScoresEntry.LEAGUE_NAME_COL,mLeague.getCaption());

        return cv;
    }
    public String getMatchDay() {
        return mMatchDay;
    }

    public String getMatchDate() {
        return mMatchDate.substring(0,mMatchDate.indexOf("T"));
    }

    public String getHomeTeam() {
        return mHomeTeam;
    }

    public String getAwayTeam() {
        return mAwayTeam;
    }

    public String getLeagueId() {
        return mFixtureLinks.getLeagueLink().getLeagueId();
    }

    public String getMatchId() {
        return mFixtureLinks.getMatchId().getMatchId();
    }

    public League getLeague() {
        return mLeague;
    }

    public void setLeague(League mLeague) {
        this.mLeague = mLeague;
    }

    public String getTime() {

        mTime = mMatchDate.substring(mMatchDate.indexOf("T") + 1, mMatchDate.indexOf("Z"));

        String strDate = mMatchDate.substring(0, mMatchDate.indexOf("T"));
        SimpleDateFormat match_date = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        match_date.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date parseddate = match_date.parse(strDate+mTime);
            SimpleDateFormat new_date = new SimpleDateFormat("yyyy-MM-dd:HH:mm");
            new_date.setTimeZone(TimeZone.getDefault());
            strDate = new_date.format(parseddate);
            mTime = strDate.substring(strDate.indexOf(":") + 1);

            return mTime;
        }
        catch (Exception e)
        {
            Log.e(TAG,e.getMessage());
            return "";
        }
    }



}
