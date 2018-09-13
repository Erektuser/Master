package tkacz.kamil.masterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.Locale;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class test extends AppCompatActivity {

    private TextView mTextViewAngleLeft;
    private TextView mTextViewStrengthLeft;

    private TextView mTextViewAngleRight;
    private TextView mTextViewStrengthRight;

    private TextView y1;
    private TextView x1;

    private TextView y2;
    private TextView x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mTextViewAngleLeft = (TextView) findViewById(R.id.textView_angle_left);
        mTextViewStrengthLeft = (TextView) findViewById(R.id.textView_strength_left);
        y1 = (TextView) findViewById(R.id.textView_y1);
        x1 = (TextView) findViewById(R.id.textView_x1);

        JoystickView joystickLeft = (JoystickView) findViewById(R.id.joystickView_left);
        joystickLeft.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                mTextViewAngleLeft.setText(angle + "°");
                mTextViewStrengthLeft.setText(strength + "%");
                y1.setText((int)(Math.sin(angle)) + " y°");
                x1.setText((int)(Math.cos(angle)) + " x%");

            }
        });


        mTextViewAngleRight = (TextView) findViewById(R.id.textView_angle_right);
        mTextViewStrengthRight = (TextView) findViewById(R.id.textView_strength_right);
        y2 = (TextView) findViewById(R.id.textView_y2);
        x2 = (TextView) findViewById(R.id.textView_x2);
        DecimalFormat df2 = new DecimalFormat(".##");



        JoystickView joystickRight = (JoystickView) findViewById(R.id.joystickView_right);
        joystickRight.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                DecimalFormat df2 = new DecimalFormat(".##");

                    y2.setText(String.format(Locale.ROOT,"%.2f", strength * Math.sin(Math.toRadians(angle))) + " y°");
                    x2.setText(String.format(Locale.ROOT,"%.2f", strength * Math.cos(Math.toRadians(angle))) + " x%");

            }
        });
    }
}