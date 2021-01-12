package io.qio.sensorpoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Sensor sensor;
    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

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