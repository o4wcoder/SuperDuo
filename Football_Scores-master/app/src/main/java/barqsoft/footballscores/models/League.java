package barqsoft.footballscores.models;

import com.google.gson.annotations.SerializedName;

import barqsoft.footballscores.data.DBConstants;

/**
 * Created by Chris Hare on 10/28/2015.
 */
public class League {

    @SerializedName(DBConstants.HREF)
    private String mLeague;

    public String getLeague() {
        return mLeague;
    }
}
