package barqsoft.footballscores.data;

/**
 * Created by Chris Hare on 10/26/2015.
 */
public interface DBConstants {

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
}
