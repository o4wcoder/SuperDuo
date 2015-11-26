package barqsoft.footballscores.models;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import barqsoft.footballscores.R;

/**
 * Created by yehya khaled on 2/26/2015.
 */
public class ViewHolder
{
    private static final String TAG = ViewHolder.class.getSimpleName();

    public Toolbar toolbar;
    public TextView home_name;
    public TextView away_name;
    public TextView score;
    public TextView time;
    public ImageView home_crest;
    public ImageView away_crest;
    public double match_id;
    public ViewHolder(View view)
    {
        toolbar = (Toolbar) view.findViewById(R.id.scores_toolbar);
        toolbar.inflateMenu(R.menu.menu_share);




        home_name = (TextView) view.findViewById(R.id.home_name);
        away_name = (TextView) view.findViewById(R.id.away_name);
        score     = (TextView) view.findViewById(R.id.score_textview);
        time      = (TextView) view.findViewById(R.id.time_textview);
        home_crest = (ImageView) view.findViewById(R.id.home_crest);
        away_crest = (ImageView) view.findViewById(R.id.away_crest);
    }
}
