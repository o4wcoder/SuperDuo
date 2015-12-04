package barqsoft.footballscores.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import barqsoft.footballscores.R;
import barqsoft.footballscores.data.DBConstants;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilies implements DBConstants
{
    public static final int SERIE_A = 357;
    public static final int PREMIER_LEGAUE = 354;
    public static final int CHAMPIONS_LEAGUE = 362;
    public static final int PRIMERA_DIVISION = 358;
    public static final int BUNDESLIGA = 351;
    public static String getLeague(int league_num)
    {
        switch (league_num)
        {
            case SERIE_A : return "Seria A";
            case PREMIER_LEGAUE : return "Premier LeagueLink";
            case CHAMPIONS_LEAGUE : return "UEFA Champions LeagueLink";
            case PRIMERA_DIVISION : return "Primera Division";
            case BUNDESLIGA : return "Bundesliga";
            default: return "Not known LeagueLink Please report";
        }
    }
    public static String getMatchDay(int match_day,int league_num)
    {
        if(league_num == CHAMPIONS_LEAGUE)
        {
            if (match_day <= 6)
            {
                return "Group Stages, Matchday : 6";
            }
            else if(match_day == 7 || match_day == 8)
            {
                return "First Knockout round";
            }
            else if(match_day == 9 || match_day == 10)
            {
                return "QuarterFinal";
            }
            else if(match_day == 11 || match_day == 12)
            {
                return "SemiFinal";
            }
            else
            {
                return "Final";
            }
        }
        else
        {
            return "Matchday : " + String.valueOf(match_day);
        }
    }

    public static String getScores(int home_goals,int awaygoals)
    {
        if(home_goals < 0 || awaygoals < 0)
        {
            return " - ";
        }
        else
        {
            return String.valueOf(home_goals) + " - " + String.valueOf(awaygoals);
        }
    }

    public static int getTeamCrestByTeamName (String teamname)
    {
        if (teamname==null){return R.drawable.no_icon;}
        switch (teamname)
        { //This is the set of icons that are currently in the app. Feel free to find and add more
            //as you go.
            case "Arsenal London FC" : return R.drawable.arsenal;
            case "Manchester United FC" : return R.drawable.manchester_united;
            case "Swansea City" : return R.drawable.swansea_city_afc;
            case "Leicester City" : return R.drawable.leicester_city_fc_hd_logo;
            case "Everton FC" : return R.drawable.everton_fc_logo1;
            case "West Ham United FC" : return R.drawable.west_ham;
            case "Tottenham Hotspur FC" : return R.drawable.tottenham_hotspur;
            case "West Bromwich Albion" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Sunderland AFC" : return R.drawable.sunderland;
            case "Stoke City FC" : return R.drawable.stoke_city;
            default: return R.drawable.soccer_ball;
        }
    }

    /**
     * Returns true if the network is available or about to become available.
     *
     * @param c Context used to get the ConnectivityManager
     * @return true if the network is available
     */
    static public boolean isNetworkAvailable(Context c) {
        ConnectivityManager cm =
                (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Set the status for the Football scores server
     * @param context Calling context
     * @param serverStatus Status to be set
     */
    static public void setServerStatus(Context context,@DBConstants.ServerStatus int serverStatus) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor spe = sp.edit();
        spe.putInt(context.getString(R.string.pref_server_status_key), serverStatus);
        spe.commit(); //Use commit since calling from background thread in syncAdapter
    }

    /**
     * Get the status of the Footbal server stored in Shared Preferences
     * @param context
     * @return
     */
    @SuppressWarnings("ResourceType")
    static public @DBConstants.ServerStatus
    int getServerStatus(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(context.getString(R.string.pref_server_status_key),DBConstants.SERVER_UNKNOWN);
    }

    static public void resetServerStatus(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor spe = sp.edit();
        spe.putInt(context.getString(R.string.pref_server_status_key),DBConstants.SERVER_UNKNOWN);
    }

    /**
     * Put together the Content Description for the selected match. This is used when in Talk Back
     * mode
     * @param cursor Data of the current match
     * @return String of the content description of the match.
     */
    public static String getMatchContentDescription(Context context, Cursor cursor) {

        String message = "";

        if(cursor.getInt(INDEX_SCORES_HOME_GOALS) == -1) {
           //Message for when the match has not started
            message = cursor.getString(INDEX_SCORES_LEAGUE_NAME) + context.getString(R.string.content_desc_league) +
                    cursor.getString(INDEX_SCORES_HOME) + context.getString(R.string.content_desc_versus) + cursor.getString(INDEX_SCORES_AWAY) +
                    context.getString(R.string.content_desc_match_starts_at) + cursor.getString(INDEX_SCORES_TIME);
        }
        else {

            String strTeamScores = "";
            if(cursor.getInt(INDEX_SCORES_HOME_GOALS) == cursor.getInt(INDEX_SCORES_AWAY_GOALS)) {
                strTeamScores = cursor.getString(INDEX_SCORES_HOME) + context.getString(R.string.content_desc_tied) + cursor.getString(INDEX_SCORES_AWAY) +
                        cursor.getInt(INDEX_SCORES_HOME_GOALS) + context.getString(R.string.content_desc_to) + cursor.getInt(INDEX_SCORES_AWAY_GOALS);
            }
            else if(cursor.getInt(INDEX_SCORES_HOME_GOALS) > cursor.getInt(INDEX_SCORES_AWAY_GOALS)) {
                strTeamScores = cursor.getString(INDEX_SCORES_HOME) + context.getString(R.string.content_desc_over) + cursor.getString(INDEX_SCORES_AWAY) +
                        cursor.getInt(INDEX_SCORES_HOME_GOALS) + context.getString(R.string.content_desc_to) + cursor.getInt(INDEX_SCORES_AWAY_GOALS);
            }
            else {
                strTeamScores = cursor.getString(INDEX_SCORES_AWAY) + context.getString(R.string.content_desc_over) + cursor.getString(INDEX_SCORES_HOME) +
                        cursor.getInt(INDEX_SCORES_AWAY_GOALS) + context.getString(R.string.content_desc_to) + cursor.getInt(INDEX_SCORES_HOME_GOALS);
            }
            message = cursor.getString(INDEX_SCORES_LEAGUE_NAME) + context.getString(R.string.content_desc_league) + strTeamScores;
        }

        return message;


    }
}
