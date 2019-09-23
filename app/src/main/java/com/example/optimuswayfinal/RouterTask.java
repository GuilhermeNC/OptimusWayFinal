package com.example.optimuswayfinal;

import android.content.Context;
import android.os.AsyncTask;

import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public class RouterTask extends AsyncTask<Object, Void, Road> {

    private OnAsyncTaskResult<Road> mCallBack;
    private Context mContext;
    public Exception mException;

    public RouterTask(Context context, OnAsyncTaskResult<Road> callback) {
        this.mContext = context;
        this.mCallBack = callback;
    }

    @Override
    protected Road doInBackground(Object... objects) {
        try {
            RoadManager roadManager = (RoadManager) objects[0];
            ArrayList<GeoPoint> waypoints = (ArrayList<GeoPoint>) objects[1];

            Road road = roadManager.getRoad(waypoints);

            return road;
        } catch (Exception e) {
            mException = e;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Road road) {
        if (mCallBack != null) {
            if (mException == null) {
                mCallBack.onSuccess(road);
            } else {
                mCallBack.onFailure(mException);
            }
        }
    }
}
