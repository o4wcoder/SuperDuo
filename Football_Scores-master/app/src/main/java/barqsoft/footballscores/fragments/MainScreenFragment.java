package barqsoft.footballscores.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import barqsoft.footballscores.data.DBConstants;
import barqsoft.footballscores.data.ScoresContract;
import barqsoft.footballscores.activities.MainActivity;
import barqsoft.footballscores.R;
import barqsoft.footballscores.adapters.ScoresAdapter;
import barqsoft.footballscores.helpers.Utilies;
import barqsoft.footballscores.models.ViewHolder;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainScreenFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>
{

    private final static String TAG = MainScreenFragment.class.getSimpleName();

    public ScoresAdapter mAdapter;
    public static final int SCORES_LOADER = 0;
    private String[] fragmentdate = new String[1];
    private int last_selected_item = -1;

    public MainScreenFragment()
    {
    }

//    private void update_scores()
//    {
//        Timber.e("update_scores() Inside ");
//        Log.e("Fuck you","Inside update_scores");
//
//        Intent service_start = new Intent(getActivity(), ScoresFetchService.class);
//        getActivity().startService(service_start);
//    }
    public void setFragmentDate(String date)
    {
        fragmentdate[0] = date;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        //update_scores();
        Log.e(TAG,"MainScreenFragment onCreateView()");
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final ListView scoreList = (ListView) rootView.findViewById(R.id.scores_list);
        scoreList.setEmptyView(rootView.findViewById(R.id.emptyScoresLayout));
        mAdapter = new ScoresAdapter(getActivity(),null,0);
        scoreList.setAdapter(mAdapter);
        getLoaderManager().initLoader(SCORES_LOADER,null,this);
        mAdapter.detail_match_id = MainActivity.selected_match_id;
        scoreList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ViewHolder selected = (ViewHolder) view.getTag();
                mAdapter.detail_match_id = selected.match_id;
                MainActivity.selected_match_id = (int) selected.match_id;
                mAdapter.notifyDataSetChanged();
            }
        });
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle)
    {
        return new CursorLoader(getActivity(), ScoresContract.ScoresEntry.buildScoreWithDate(),
                null,null,fragmentdate,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor)
    {
        //Log.v(FetchScoreTask.LOG_TAG,"loader finished");
        //cursor.moveToFirst();
        /*
        while (!cursor.isAfterLast())
        {
            Log.v(FetchScoreTask.LOG_TAG,cursor.getString(1));
            cursor.moveToNext();
        }
        */
        updateEmptyView();
        int i = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            i++;
            cursor.moveToNext();
        }
        //Log.v(FetchScoreTask.LOG_TAG,"Loader query: " + String.valueOf(i));
        mAdapter.swapCursor(cursor);
        //mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader)
    {
        mAdapter.swapCursor(null);
    }


    private void updateEmptyView() {

        if(mAdapter.getCount() == 0) {
            TextView emptyTextView = (TextView)getView().findViewById(R.id.emptyTextView);

            if(emptyTextView != null){
                //Cursor was empty. Get the Server Status
                @DBConstants.ServerStatus int serverStatus = Utilies.getServerStatus(getActivity());
                int message = R.string.empty_scores_list;
                switch(serverStatus) {
                    case DBConstants.SERVER_DOWN:
                        message = R.string.empty_scores_list_server_down;
                        break;
                    case DBConstants.SERVER_INVALID:
                        message = R.string.empty_scores_list_server_error;
                        break;
                    default:
                        //Check if the network is even avialbale. If it is then we just
                        //done't have any data for this day
                        if(!Utilies.isNetworkAvailable(getActivity())) {
                            message = R.string.empty_scores_list_no_network;
                        }

                }
                emptyTextView.setText(message);
            }
        }
    }
}
