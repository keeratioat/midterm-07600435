package th.ac.su.speedcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCalculate = findViewById(R.id.button_calculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextDistance = findViewById(R.id.edit_text_distance);
                EditText editTextTime = findViewById(R.id.edit_text_time);
                String strDistance = editTextDistance.getText().toString();
                String strTime = editTextTime.getText().toString();
                if(strDistance.length() == 0|| strTime.length() == 0){
                    Toast toast = Toast.makeText(MainActivity.this ,
                            R.string.distance_less_than_zero,
                            Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    double distance = Double.parseDouble(strDistance);
                    double time = Double.parseDouble(strTime);
                    if(time <= 0){
                        Toast toast = Toast.makeText(MainActivity.this ,
                                R.string.time_must_be_greter_than_zero,
                                Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        double velocity = distance / time;
                        double velocityKitometer = velocity * 3600 / 1000;

                        DecimalFormat df = new DecimalFormat("0.00");
                        df.setRoundingMode(RoundingMode.HALF_UP);
                        String velocityStr = String.valueOf(df.format(velocityKitometer));
                        TextView showResult = findViewById(R.id.show_result);
                        showResult.setText(velocityStr);
                        if(velocityKitometer > 80){
                            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                            dialog.setTitle("SPEED CALCULATE");
                            dialog.setMessage(R.string.speed_over_limt);
                            dialog.setPositiveButton("OK" , null);
                            dialog.show();
                        }
                    }

                }

            }
        });Button clearButton = findViewById(R.id.button_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextDistance = findViewById(R.id.edit_text_distance);
                EditText editTextTime = findViewById(R.id.edit_text_time);
                TextView showResult = findViewById(R.id.show_result);
                editTextDistance.setText("");
                editTextTime.setText("");
                showResult.setText("");
            }
        });
    }
}