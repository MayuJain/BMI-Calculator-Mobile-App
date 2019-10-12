package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Gravity;


public class MainActivity extends AppCompatActivity {
    private TextView Bmiresult;
    private TextView BmiStatus;
    private Button Calculate;
    private EditText heightFeetInput;
    private EditText heightInchesInput;

    private float heightInches;
    private float heightFeet;
    private float Weight;
    private float bmiresultValue;
    private Toast toast;
    private EditText WeightInput;
public void clearResults()
{Bmiresult.setText("");
    BmiStatus.setText("");
    Bmiresult.setVisibility(View.INVISIBLE);
    BmiStatus.setVisibility(View.INVISIBLE);

}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmiresult = findViewById(R.id.bmiResult);
        BmiStatus = findViewById(R.id.bmiStatus);
        Calculate = findViewById(R.id.btnCalculateBMI);
        WeightInput = findViewById(R.id.weightInput);
        heightFeetInput = findViewById(R.id.heightFeetInput);
        heightInchesInput = findViewById(R.id.heightInchesInput);

        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearResults();
                String tempweight = WeightInput.getText().toString();
                String tempheightfoot = heightFeetInput.getText().toString();
                String tempheightinches = heightInchesInput.getText().toString();
                boolean error = false;

                if (tempweight.equals("") || Double.parseDouble(tempweight) == 0.0) {
                    error = true;
                    WeightInput.setError(getResources().getString(R.string.errorMessage));
                }
                if (tempheightfoot.equals("") || Double.parseDouble(tempheightfoot) == 0.0) {
                    error = true;
                    heightFeetInput.setError(getResources().getString(R.string.errorMessage));
                }
                if (tempheightinches.equals("")) {
                    error = true;
                    heightInchesInput.setError(getResources().getString(R.string.errorMessage));
                }
                if (error == true) {
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.errorMessage),Toast.LENGTH_SHORT).show();
                }

                if(error==false)
                {
                if (!tempweight.equals("") && tempweight != null)
                    Weight = Float.parseFloat(WeightInput.getText().toString());
                if (!tempheightfoot.equals("") && tempheightfoot != null)
                    heightFeet = Float.parseFloat(heightFeetInput.getText().toString());
                if (!tempheightinches.equals("") && tempheightinches != null)
                    heightInches = Float.parseFloat(heightInchesInput.getText().toString());
                float totalHeight = heightFeet * 12 + heightInches;
                bmiresultValue = (Weight / (totalHeight * totalHeight)) * 703;
                String tempResultValue = getResources().getString(R.string.BMIResultMessage) + Float.toString(bmiresultValue);
                Bmiresult.setText(tempResultValue);
                if (bmiresultValue <= 18.5) {
                    BmiStatus.setText(getResources().getString(R.string.StatusUnderWeight));
                } else if (bmiresultValue >= 18.5 && bmiresultValue <= 24.9) {
                    BmiStatus.setText(getResources().getString(R.string.StatusNormalWeight));
                } else if (bmiresultValue >= 25 && bmiresultValue <= 29.9) {
                    BmiStatus.setText(getResources().getString(R.string.StatusOverWeight));
                } else if (bmiresultValue >= 30) {
                    BmiStatus.setText(getResources().getString(R.string.StatusObesity));
                }

                    Bmiresult.setVisibility(View.VISIBLE);
                    BmiStatus.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.bmiCalculated),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
