package barqsoft.footballscores.data;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Chris Hare on 10/26/2015.
 */
public interface DBConstants {

    // This set of league codes is for the 2015/2016 season. In fall of 2016, they will need to
    // be updated. Feel free to use the codes
    String BUNDESLIGA1 = "394";
    String BUNDESLIGA2 = "395";
    String LIGUE1 = "396";
    String LIGUE2 = "397";
    String PREMIER_LEAGUE = "398";
    String PRIMERA_DIVISION = "399";
    String SEGUNDA_DIVISION = "400";
    String SERIE_A = "401";
    final String PRIMERA_LIGA = "402";
    final String Bundesliga3 = "403";
    final String EREDIVISIE = "404";

    String MATCH_LINK = "http://api.football-data.org/alpha/fixtures/";
    String SEASON_LINK = "http://api.football-data.org/alpha/soccerseasons/";
    String FIXTURES = "fixtures";
    String LINKS = "_links";
    String SOCCERSEASON = "soccerseason";
    String SELF = "self";
    String HREF = "href";
    String MATCH_DATE = "date";
    String HOME_TEAM = "homeTeamName";
    String AWAY_TEAM = "awayTeamName";
    String RESULT = "result";
    String HOME_GOALS = "goalsHomeTeam";
    String AWAY_GOALS = "goalsAwayTeam";
    String MATCH_DAY = "matchday";
    String LEAGUE_TABLE = "LeagueTable";

    //League Constants
    String LEAGUE_CAPTION = "caption";

    //Server status Annotation
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SERVER_OK, SERVER_DOWN, SERVER_INVALID, SERVER_UNKNOWN})
    public @interface ServerStatus {}

    int SERVER_OK = 0;
    int SERVER_DOWN = 1;
    int SERVER_INVALID = 2;
    int SERVER_UNKNOWN = 3;


    int INDEX_SCORES_ID = 0;
    int INDEX_SCORES_DATE = 1;
    int INDEX_SCORES_TIME = 2;
    int INDEX_SCORES_HOME = 3;
    int INDEX_SCORES_AWAY = 4;
    int INDEX_SCORES_HOME_GOALS = 5;
    int INDEX_SCORES_AWAY_GOALS = 6;
    int INDEX_SCORES_MATCH_ID = 7;
    int INDEX_SCORES_MATCH_DAY = 8;
    int INDEX_SCORES_LEAGUE = 9;
    int INDEX_SCORES_LEAGUE_NAME = 10;



}
