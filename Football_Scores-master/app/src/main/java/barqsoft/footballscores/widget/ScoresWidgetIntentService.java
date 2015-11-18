package barqsoft.footballscores.widget;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Chris Hare on 11/16/2015.
 */
public class ScoresWidgetIntentService extends IntentService {

    public ScoresWidgetIntentService() {
        super(ScoresWidgetIntentService.class.getSimpleName());
    }
    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
