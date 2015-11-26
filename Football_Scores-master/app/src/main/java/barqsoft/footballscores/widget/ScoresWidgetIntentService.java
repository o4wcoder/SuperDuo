//package barqsoft.footballscores.widget;
//
//import android.app.IntentService;
//import android.appwidget.AppWidgetManager;
//import android.content.ComponentName;
//import android.content.Intent;
//import android.database.Cursor;
//import android.net.Uri;
//import android.util.Log;
//
//import barqsoft.footballscores.data.DBConstants;
//import barqsoft.footballscores.data.ScoresContract;
//
///**
// * Created by Chris Hare on 11/16/2015.
// */
//public class ScoresWidgetIntentService extends IntentService implements DBConstants {
//
//    private static final String TAG = ScoresWidgetIntentService.class.getSimpleName();
//
//    public ScoresWidgetIntentService() {
//        super(ScoresWidgetIntentService.class.getSimpleName());
//    }
//    @Override
//    protected void onHandleIntent(Intent intent) {
//
//        Log.e(TAG, "onHandleIntent() Inside");
//        // Retrieve all of the Today widget ids: these are the widgets we need to update
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
//        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,
//                ScoresWidgetProvider.class));
//
//        Uri scoresByDateUri = ScoresContract.ScoresEntry.buildScoreWithDate();
//        Cursor data = getContentResolver().query(scoresByDateUri,
//                SCORES_COLUMNS,
//                null,
//                null,
//                ScoresContract.ScoresEntry.DATE_COL + " ASC");
//
//        if(data == null) {
//            return;
//        }
//
//        if(!data.moveToFirst()) {
//            data.close();
//            return;
//        }
//    }
//}
