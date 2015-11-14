package com.blabbertabber.blabbertabber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blabbertabber.blabbertabber.PieChart;

/**
 * Created by cunnie on 11/11/15.
 */
public class SummaryActivity extends Activity {

    private static final String TAG = "SummaryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
        setContentView(R.layout.activity_summary);

        TextView durationView = (TextView) findViewById(R.id.textview_duration);
        long meetingDuration = TheSpeakers.getInstance().getMeetingDuration();
        durationView.setText(Helper.timeToHMMSS(meetingDuration));

        TextView avgSpeakerDurationView = (TextView) findViewById(R.id.textview_average);
        long avgSpeakerDuration = TheSpeakers.getInstance().getAverageSpeakerDuration();
        avgSpeakerDurationView.setText(Helper.timeToHMMSSm(avgSpeakerDuration));

        TextView minSpeakerDurationView = (TextView) findViewById(R.id.textview_min);
        long minSpeakerDuration = TheSpeakers.getInstance().getMinSpeakerDuration();
        minSpeakerDurationView.setText(Helper.timeToHMMSSm(minSpeakerDuration));

        TextView maxSpeakerDurationView = (TextView) findViewById(R.id.textview_max);
        long maxSpeakerDuration = TheSpeakers.getInstance().getMaxSpeakerDuration();
        maxSpeakerDurationView.setText(Helper.timeToHMMSSm(maxSpeakerDuration));

        Log.wtf(TAG, "We SHOULD get here!");
        LinearLayout ll = (LinearLayout) findViewById(R.id.pie_chart_ll);
        ll.addView(new PieChart(getApplicationContext(), new double[]{45, 45, 90, 180}));
        Log.wtf(TAG, "Yeah, just drew a circle.  You should see it.");

    }

    public void newMeeting(View v) {
        TheSpeakers.getInstance().reset();
        Intent i = new Intent(this, RecordingActivity.class);
        startActivity(i);
    }
}