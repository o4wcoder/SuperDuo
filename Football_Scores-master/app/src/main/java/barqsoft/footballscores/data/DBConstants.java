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
    String LEAGUE = "soccerseason";
    String MATCH_ID = "self";
    String HREF = "href";
    String MATCH_DATE = "date";
    String HOME_TEAM = "homeTeamName";
    String AWAY_TEAM = "awayTeamName";
    String RESULT = "result";
    String HOME_GOALS = "goalsHomeTeam";
    String AWAY_GOALS = "goalsAwayTeam";
    String MATCH_DAY = "matchday";

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SERVER_OK, SERVER_DOWN, SERVER_UNKNOWN})
    public @interface ServerStatus {}

    int SERVER_OK = 0;
    int SERVER_DOWN = 1;
    int SERVER_UNKNOWN = 2;
}
