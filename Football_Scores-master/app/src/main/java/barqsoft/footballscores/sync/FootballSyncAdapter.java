package barqsoft.footballscores.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.Vector;

import barqsoft.footballscores.R;
import barqsoft.footballscores.data.DBConstants;
import barqsoft.footballscores.data.DatabaseContract;
import barqsoft.footballscores.models.Fixture;
import barqsoft.footballscores.web.DataManager;
import retrofit.RetrofitError;

/**
 * Created by Chris Hare on 10/30/2015.
 */
public class FootballSyncAdapter extends AbstractThreadedSyncAdapter {

    public final String TAG = FootballSyncAdapter.class.getSimpleName();
    // Interval at which to sync with the football api, in seconds.
    // 60 seconds (1 minute) * 180 = 3 hours
    public static final int SYNC_INTERVAL = 30; //* 180;
    public static final int SYNC_FLEXTIME = SYNC_INTERVAL/3;
    private static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;

    DataManager sDataManager;

    public FootballSyncAdapter(Context context, boolean autoInitialize) {
        super(context,autoInitialize);
    }
    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {

        Log.e(TAG,"on PerformSync()");
        sDataManager = DataManager.get();

        getFixtures("p3");
        getFixtures("n3");

    }

    private void getFixtures(String timeFrame) {

        List<Fixture> fixtureList = null;
        try {
            fixtureList = sDataManager.fetchFixtures(timeFrame);
        } catch (RetrofitError e ) {

            Log.e(TAG, "------- Got retrofit error --------");
            Log.e(TAG,e.getMessage());

            return;
        }

        Log.e(TAG, "FixtureList size: " + fixtureList.size());
        Vector<ContentValues> vectorValues = new Vector<>(fixtureList.size());
        if(fixtureList != null) {
            for (Fixture fixture : fixtureList) {

                if (fixture != null) {
                    //  Log.e(TAG, "Getting content values");
                    // Log.e(TAG, fixture.toString());

                    //This if statement controls which leagues we're interested in the data from.
                    //add leagues here in order to have them be added to the DB.
                    // If you are finding no data in the app, check that this contains all the leagues.
                    // If it doesn't, that can cause an empty DB, bypassing the dummy data routine.
//                    if (fixture.getLeague().equals(DBConstants.PREMIER_LEAGUE) ||
//                            fixture.getLeague().equals(DBConstants.SERIE_A) ||
//                            fixture.getLeague().equals(DBConstants.BUNDESLIGA1) ||
//                            fixture.getLeague().equals(DBConstants.BUNDESLIGA2) ||
//                            fixture.getLeague().equals(DBConstants.PRIMERA_DIVISION)) {
                    //   Log.e(TAG,"Adding fixture from league " + fixture.getLeague());
                    // Log.e(TAG,fixture.toString());
                    vectorValues.add(fixture.getContentValues());
                    //   }
                }
            }
        }

        int inserted = 0;
        ContentValues[] values = new ContentValues[vectorValues.size()];
        vectorValues.toArray(values);
        inserted = getContext().getContentResolver().bulkInsert(
                DatabaseContract.BASE_CONTENT_URI,values);


        Log.e(TAG,"Succesfully Inserted : " + String.valueOf(inserted));
    }
    /**
     * Helper method to schedule the sync adapter periodic execution
     */
    public static void configurePeriodicSync(Context context, int syncInterval, int flexTime) {
        Account account = getSyncAccount(context);
        String authority = DatabaseContract.CONTENT_AUTHORITY;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // we can enable inexact timers in our periodic sync
            SyncRequest request = new SyncRequest.Builder().
                    syncPeriodic(syncInterval, flexTime).
                    setSyncAdapter(account, authority).
                    setExtras(new Bundle()).build();
            ContentResolver.requestSync(request);
        } else {
            ContentResolver.addPeriodicSync(account,
                    authority, new Bundle(), syncInterval);
        }
    }

    /**
     * Helper method to have the sync adapter sync immediately
     * @param context The context used to access the account service
     */
    public static void syncImmediately(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                DatabaseContract.CONTENT_AUTHORITY, bundle);
    }

    /**
     * Helper method to get the fake account to be used with SyncAdapter, or make a new one
     * if the fake account doesn't exist yet.  If we make a new account, we call the
     * onAccountCreated method so we can initialize things.
     *
     * @param context The context used to access the account service
     * @return a fake account.
     */
    public static Account getSyncAccount(Context context) {
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        // Create the account type and default account
        Account newAccount = new Account(
                context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        // If the password doesn't exist, the account doesn't exist
        if ( null == accountManager.getPassword(newAccount) ) {

        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
            if (!accountManager.addAccountExplicitly(newAccount, "", null)) {
                return null;
            }
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call ContentResolver.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */

            onAccountCreated(newAccount, context);
        }
        return newAccount;
    }

    private static void onAccountCreated(Account newAccount, Context context) {
        /*
         * Since we've created an account
         */
        FootballSyncAdapter.configurePeriodicSync(context, SYNC_INTERVAL, SYNC_FLEXTIME);

        /*
         * Without calling setSyncAutomatically, our periodic sync will not be enabled.
         */
        ContentResolver.setSyncAutomatically(newAccount, DatabaseContract.CONTENT_AUTHORITY, true);

        /*
         * Finally, let's do a sync to get things started
         */
        syncImmediately(context);
    }

    public static void initializeSyncAdapter(Context context) {
        getSyncAccount(context);
    }
}
