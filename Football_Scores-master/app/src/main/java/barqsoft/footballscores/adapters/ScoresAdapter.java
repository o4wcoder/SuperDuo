package barqsoft.footballscores.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import barqsoft.footballscores.R;
import barqsoft.footballscores.data.DBConstants;
import barqsoft.footballscores.helpers.Utilies;
import barqsoft.footballscores.models.ViewHolder;

/**
 * Created by yehya khaled on 2/26/2015.
 */
public class ScoresAdapter extends CursorAdapter implements DBConstants
{
//    public static final int COL_DATE = 1;
//    public static final int COL_MATCHTIME = 2;
//    public static final int COL_HOME = 3;
//    public static final int COL_AWAY = 4;
//    public static final int COL_HOME_GOALS = 5;
//    public static final int COL_AWAY_GOALS = 6;
//    public static final int COL_ID = 7;
//    public static final int COL_MATCHDAY = 8;
//    public static final int COL_LEAGUE = 9;
//    public static final int COL_LEAGUE_NAME = 10;



    public double detail_match_id = 0;
    private String FOOTBALL_SCORES_HASHTAG = "#Football_Scores";

    private static String TAG = ScoresAdapter.class.getSimpleName();

    public ScoresAdapter(Context context, Cursor cursor, int flags)
    {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        View mItem = LayoutInflater.from(context).inflate(R.layout.scores_list_item, parent, false);
        ViewHolder mHolder = new ViewHolder(mItem);
        mItem.setTag(mHolder);
      //  Log.e(TAG, "new View inflated");


        return mItem;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
       // Log.e(TAG, "In bindView()");
        final ViewHolder mHolder = (ViewHolder) view.getTag();

        mHolder.toolbar.setTitle(cursor.getString(INDEX_SCORES_LEAGUE_NAME));
        mHolder.toolbar.setSubtitle(Utilies.getMatchDay(cursor.getInt(INDEX_SCORES_MATCH_DAY),
                cursor.getInt(INDEX_SCORES_LEAGUE)));

                mHolder.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Log.e(TAG, "Cicked Share");
                        context.startActivity(createShareForecastIntent(mHolder.home_name.getText() + " "
                                + mHolder.score.getText() + " " + mHolder.away_name.getText() + " "));
                        return false;
                    }
                });

        //Set the content description for the entire match. A description of the match will be
        //heard with all the details instead of having each component of the CardView having
        //a separate description.
        view.setContentDescription(Utilies.getMatchContentDescription(context,cursor));

        mHolder.home_name.setText(cursor.getString(INDEX_SCORES_HOME));
        mHolder.away_name.setText(cursor.getString(INDEX_SCORES_AWAY));
        mHolder.time.setText(cursor.getString(INDEX_SCORES_TIME));
        mHolder.score.setText(Utilies.getScores(cursor.getInt(INDEX_SCORES_HOME_GOALS), cursor.getInt(INDEX_SCORES_AWAY_GOALS)));
        mHolder.match_id = cursor.getDouble(INDEX_SCORES_MATCH_ID);
        mHolder.home_crest.setImageResource(Utilies.getTeamCrestByTeamName(
                cursor.getString(INDEX_SCORES_HOME)));
        mHolder.away_crest.setImageResource(Utilies.getTeamCrestByTeamName(
                cursor.getString(INDEX_SCORES_AWAY)
        ));
        //Log.e(TAG,"Score: " + cursor.getInt(COL_HOME_GOALS) + " - " + cursor.getInt(COL_AWAY_GOALS));
       // Log.e(TAG, mHolder.home_name.getText() + " Vs. " + mHolder.away_name.getText() + " id " + String.valueOf(mHolder.match_id));
        //Log.e(TAG, String.valueOf(match_id));
//        LayoutInflater vi = (LayoutInflater) context.getApplicationContext()
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = vi.inflate(R.layout.detail_fragment, null);
//        ViewGroup container = (ViewGroup) view.findViewById(R.id.details_fragment_container);
//        Log.e(TAG,"Match ID: " + mHolder.match_id + " Detail Match ID: " + detail_match_id);
//        if(mHolder.match_id == detail_match_id)
//        {
//            Log.v(TAG,"will insert extraView");
//
//            container.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
//                    , ViewGroup.LayoutParams.MATCH_PARENT));
//            TextView match_day = (TextView) v.findViewById(R.id.matchday_textview);
//            match_day.setText(Utilies.getMatchDay(cursor.getInt(INDEX_SCORES_MATCH_DAY),
//                    cursor.getInt(INDEX_SCORES_LEAGUE)));
//            TextView league = (TextView) v.findViewById(R.id.league_textview);
//            league.setText(Utilies.getLeague(cursor.getInt(INDEX_SCORES_LEAGUE)));
//            Button share_button = (Button) v.findViewById(R.id.share_button);
//            share_button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v)
//                {
//                    //add Share Action
//                    context.startActivity(createShareForecastIntent(mHolder.home_name.getText()+" "
//                    +mHolder.score.getText()+" "+mHolder.away_name.getText() + " "));
//                }
//            });
//        }
//        else
//        {
//          //  Log.e(TAG,"Removing all views");
//            container.removeAllViews();
//        }

    }
    public Intent createShareForecastIntent(String ShareText) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, ShareText + FOOTBALL_SCORES_HASHTAG);
        return shareIntent;
    }


}
