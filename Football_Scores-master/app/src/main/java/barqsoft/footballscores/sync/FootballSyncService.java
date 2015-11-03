package barqsoft.footballscores.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import timber.log.Timber;

/**
 * Created by Chris Hare on 11/1/2015.
 */
public class FootballSyncService extends Service {

    private static final Object sSyncAdapterLock = new Object();
    private static FootballSyncAdapter sFootballSyncAdapter = null;

    @Override
    public void onCreate() {
        Timber.e("onCreate: FootballSyncService");
        synchronized (sSyncAdapterLock) {
            if (sFootballSyncAdapter == null) {
                sFootballSyncAdapter = new FootballSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sFootballSyncAdapter.getSyncAdapterBinder();
    }
}
