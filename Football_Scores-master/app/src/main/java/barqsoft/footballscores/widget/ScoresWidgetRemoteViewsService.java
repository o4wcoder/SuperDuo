package barqsoft.footballscores.widget;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.Date;

import barqsoft.footballscores.R;
import barqsoft.footballscores.data.DBConstants;
import barqsoft.footballscores.data.ScoresContract;
import barqsoft.footballscores.helpers.Utilies;

/**
 * Created by Chris Hare on 11/17/2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ScoresWidgetRemoteViewsService extends RemoteViewsService implements DBConstants{

    private static final String TAG = ScoresWidgetRemoteViewsService.class.getSimpleName();

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {

            private Cursor data = null;

            @Override
            public void onCreate() {
               Log.e(TAG, "RemoteViewsFactory: onCreate() Inside");
            }

            @Override
            public void onDataSetChanged() {

                Log.e(TAG,"onDataSetChanged() Inside ");
                if(data != null) {
                    data.close();
                }

               final long identityToken = Binder.clearCallingIdentity();
                Uri scoresByDateUri = ScoresContract.ScoresEntry.buildScoreWithDate();

                //Get today's date and search the database for today's entries
                Date todayDate = new Date(System.currentTimeMillis());
                SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
                String today = mformat.format(todayDate);

                data = getContentResolver().query(scoresByDateUri,
                        null,
                        null,
                        new String[]{today},
                        ScoresContract.ScoresEntry.DATE_COL + " ASC");

                Log.e(TAG,"Size of data=" + data.getCount());
                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                    data = null;
                }
            }

            @Override
            public int getCount() {
                return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {

                Log.e(TAG, "getViewsAt() Inside");
                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }

                RemoteViews views = new RemoteViews(getPackageName(),
                        R.layout.widget_list_item);

                Log.e(TAG, "data index[0]= " + data.getString(0) + " index[1]= " + data.getString(1));
                views.setTextViewText(R.id.home_name, data.getString(INDEX_SCORES_HOME));
                views.setTextViewText(R.id.away_name,data.getString(INDEX_SCORES_AWAY));
                String strScore = Utilies.getScores(data.getInt(INDEX_SCORES_HOME_GOALS),data.getInt(INDEX_SCORES_AWAY_GOALS));
                views.setTextViewText(R.id.score_textview,strScore);
                views.setTextViewText(R.id.time_textview, data.getString(INDEX_SCORES_TIME));

                //Set icons
               views.setImageViewResource(R.id.home_crest, Utilies.getTeamCrestByTeamName(
                       data.getString(INDEX_SCORES_HOME)));
                views.setImageViewResource(R.id.away_crest, Utilies.getTeamCrestByTeamName(
                        data.getString(INDEX_SCORES_AWAY)));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
                {
                    views.setContentDescription(R.id.widget_list_item, Utilies.getMatchContentDescription(data));
                }
                return views;
            }



            @Override
            public RemoteViews getLoadingView() {
                RemoteViews views = new RemoteViews(getPackageName(),
                        R.layout.widget_list_item);

                return views;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if(data.moveToPosition(position))
                    return data.getLong(INDEX_SCORES_ID);

                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
