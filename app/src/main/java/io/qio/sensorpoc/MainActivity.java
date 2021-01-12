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

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Sensor sensor;

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
            TextView txt_number = findViewById(R.id.txt_number);
            ImageView tv_imageX = findViewById(R.id.tv_imageX);
            ImageView tv_imageY = findViewById(R.id.tv_imageY);
            txt_number.setText( "x = "+ x+ " \ny = "+ y +"\nz = " + z);
            if (z >= 9.8 || z <= -9.8) {
                tv_imageX.setVisibility(View.GONE);
                tv_imageY.setVisibility(View.GONE);
            } else if (y > 0 && (int)x == 0) {
                tv_imageY.setVisibility(View.VISIBLE);
                tv_imageX.setVisibility(View.GONE);
                tv_imageY.setImageResource(R.drawable.ic_arrow_up);
            } else if (y < 0 && (int)x == 0) {
                tv_imageY.setVisibility(View.VISIBLE);
                tv_imageX.setVisibility(View.GONE);
                tv_imageY.setImageResource(R.drawable.ic_arrow_down);
            } else if (x > 0 && (int)y == 0) {
                tv_imageX.setVisibility(View.VISIBLE);
                tv_imageY.setVisibility(View.GONE);
                tv_imageX.setImageResource(R.drawable.ic_arrow_forward);
            } else if (x < 0 && (int)y == 0) {
                tv_imageX.setVisibility(View.VISIBLE);
                tv_imageY.setVisibility(View.GONE);
                tv_imageX.setImageResource(R.drawable.ic_arrow_back);
            } else if (x > 0 && y > 0) {
                tv_imageX.setVisibility(View.VISIBLE);
                tv_imageY.setVisibility(View.VISIBLE);
                tv_imageX.setImageResource(R.drawable.ic_arrow_forward);
                tv_imageY.setImageResource(R.drawable.ic_arrow_up);
            } else if (x < 0 && y > 0) {
                tv_imageX.setVisibility(View.VISIBLE);
                tv_imageY.setVisibility(View.VISIBLE);
                tv_imageX.setImageResource(R.drawable.ic_arrow_back);
                tv_imageY.setImageResource(R.drawable.ic_arrow_up);
            } else if (y < 0 && x >0 ) {
                tv_imageX.setVisibility(View.VISIBLE);
                tv_imageY.setVisibility(View.VISIBLE);
                tv_imageX.setImageResource(R.drawable.ic_arrow_forward);
                tv_imageY.setImageResource(R.drawable.ic_arrow_down);
            } else if (y < 0 && x < 0) {
                tv_imageX.setVisibility(View.VISIBLE);
                tv_imageY.setVisibility(View.VISIBLE);
                tv_imageX.setImageResource(R.drawable.ic_arrow_back);
                tv_imageY.setImageResource(R.drawable.ic_arrow_down);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}