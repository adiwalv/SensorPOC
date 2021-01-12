package io.qio.sensorpoc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import io.qio.sensorpoc.domain.Event;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Sensor sensor;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final String TAG = "MainActivity";
    private EventRestService eventRestService;
    Event eventBody = new Event();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        eventRestService = EventRestService.getInstance();

        if (sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER) != null){
            sensor  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            TextView xValue = findViewById(R.id.xValue);
            TextView yValue = findViewById(R.id.yValue);
            TextView zValue = findViewById(R.id.zValue);
            ImageView imageX = findViewById(R.id.imageX);
            ImageView imageY = findViewById(R.id.imageY);
            xValue.setText(roundToTwoDecimal(x));
            yValue.setText(roundToTwoDecimal(y));
            zValue.setText(roundToTwoDecimal(z));
            eventBody.setxAxis(x);
            eventBody.setyAxis(y);
            eventBody.setzAxis(z);
            eventRestService.getEventInterface().postEvent(eventBody).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> r) {
                    Toast.makeText(getApplicationContext(), "X:"+x+" Y:"+y+" Z:"+z+" sent!", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e(TAG, t.getLocalizedMessage());
                    Toast.makeText(getApplicationContext(), "Error posting x, y and z axis" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            if (z >= 9.8 || z <= -9.8) {
                imageX.setVisibility(View.GONE);
                imageY.setVisibility(View.GONE);
            } else if (y > 0 && (int)x == 0) {
                imageY.setVisibility(View.VISIBLE);
                imageX.setVisibility(View.GONE);
                imageY.setImageResource(R.drawable.ic_arrow_up);
            } else if (y < 0 && (int)x == 0) {
                imageY.setVisibility(View.VISIBLE);
                imageX.setVisibility(View.GONE);
                imageY.setImageResource(R.drawable.ic_arrow_down);
            } else if (x > 0 && (int)y == 0) {
                imageX.setVisibility(View.VISIBLE);
                imageY.setVisibility(View.GONE);
                imageX.setImageResource(R.drawable.ic_arrow_forward);
            } else if (x < 0 && (int)y == 0) {
                imageX.setVisibility(View.VISIBLE);
                imageY.setVisibility(View.GONE);
                imageX.setImageResource(R.drawable.ic_arrow_back);
            } else if (x > 0 && y > 0) {
                imageX.setVisibility(View.VISIBLE);
                imageY.setVisibility(View.VISIBLE);
                imageX.setImageResource(R.drawable.ic_arrow_forward);
                imageY.setImageResource(R.drawable.ic_arrow_up);
            } else if (x < 0 && y > 0) {
                imageX.setVisibility(View.VISIBLE);
                imageY.setVisibility(View.VISIBLE);
                imageX.setImageResource(R.drawable.ic_arrow_back);
                imageY.setImageResource(R.drawable.ic_arrow_up);
            } else if (y < 0 && x >0 ) {
                imageX.setVisibility(View.VISIBLE);
                imageY.setVisibility(View.VISIBLE);
                imageX.setImageResource(R.drawable.ic_arrow_forward);
                imageY.setImageResource(R.drawable.ic_arrow_down);
            } else if (y < 0 && x < 0) {
                imageX.setVisibility(View.VISIBLE);
                imageY.setVisibility(View.VISIBLE);
                imageX.setImageResource(R.drawable.ic_arrow_back);
                imageY.setImageResource(R.drawable.ic_arrow_down);
            }
        }
    }

    private String roundToTwoDecimal(float input) {
        return df.format(input);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}