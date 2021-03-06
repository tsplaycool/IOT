package cn.edu.bjtu.tsplaycool.sensor;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager sm = null;

	private LinearLayout mSensorLinearLayout;
	private TextView View0 = null;
	private TextView View1 = null;
	private TextView View2 = null;
	private TextView View3 = null;
	private TextView View4 = null;
	private TextView View5 = null;
	private TextView View6 = null;
	private TextView View7 = null;
	private TextView View8 = null;
	private TextView View9 = null;
	private TextView View10 = null;
	private TextView View11 = null;
	private TextView View12 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		mSensorLinearLayout = (LinearLayout) findViewById(R.id.sensor_linearlayout);
		View1 = (TextView) findViewById(R.id.tv1);
		View2 = (TextView) findViewById(R.id.tv2);
		View3 = (TextView) findViewById(R.id.tv3);
		View4 = (TextView) findViewById(R.id.tv4);
		View5 = (TextView) findViewById(R.id.tv5);
		View6 = (TextView) findViewById(R.id.tv6);
		View7 = (TextView) findViewById(R.id.tv7);
		View8 = (TextView) findViewById(R.id.tv8);
		View9 = (TextView) findViewById(R.id.tv9);
		View10 = (TextView) findViewById(R.id.tv10);
		View11 = (TextView) findViewById(R.id.tv11);
		View12 = (TextView) findViewById(R.id.tv12);

		// 从系统服务中获得传感器管理器
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);

		// 从传感器管理器中获得全部的传感器列表
		List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);

		// 显示有多少个传感器
		// View0.setText("经检测该手机有" + allSensors.size() + "个传感器，他们分别是：\n");

		TextView tv = new TextView(this);
		tv.setText("Total sensors: " + allSensors.size());
		mSensorLinearLayout.addView(tv);
		// 显示每个传感器的具体信息
		for (Sensor s : allSensors) {
			tv = new TextView(this);
			tv.setText(s.toString());
			mSensorLinearLayout.addView(tv);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	@Override
	protected void onStop() {
		sm.unregisterListener(this);
		super.onStop();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;
		String str = "X: ";
		for (int i = 0; i < values.length; i++) {
			if (i == 0) {
				str += values[0];
			} else if (i == 1) {
				str += ",Y: ";
				str += values[1];
			} else if (i == 2) {
				str += ",Z: ";
				str += values[2];
			}
		}
		switch (event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			View1.setText("Accelerometer(加速度)：\n" + str);
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			View2.setText("Magnetic(磁场)：\n" + str);
			break;
		case Sensor.TYPE_GYROSCOPE:
			View4.setText("Gyroscope(陀螺仪)：\n" + str);
			break;
		case Sensor.TYPE_LIGHT:
			View5.setText("Light(光线)：\n" + str);
			break;
		case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
			View6.setText("GEOMAGNETIC_ROTATION(压力)：\n" + str);
			break;
		case Sensor.TYPE_AMBIENT_TEMPERATURE:
			View7.setText("Temperature(温度)：\n" + str);
			break;
		case Sensor.TYPE_PROXIMITY:
			View8.setText("Proximity(距离)：\n" + str);
			break;
		case Sensor.TYPE_GRAVITY:
			View9.setText("Gravity(重力)：\n" + str);
			break;
		case Sensor.TYPE_LINEAR_ACCELERATION:
			View10.setText("Linear Acceleration(线性加速度)：\n" + str);
			break;
		case Sensor.TYPE_ROTATION_VECTOR:
			View11.setText("Rotation Vector(旋转矢量)：\n" + str);
			break;
		default:
			View12.setText("Normal(未知)：\n" + event.sensor.toString());
			Log.d("tanshuai", event.accuracy + "----" + event.sensor.toString());
			break;
		}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}
}
