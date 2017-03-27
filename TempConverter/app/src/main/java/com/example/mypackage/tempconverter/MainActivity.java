package com.example.mypackage.tempconverter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText text;
    View view; //create object to manipulate background color
    private ImageView iv; //create iv object to manipulate image view
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /* this method is called when user clicks the button and is handled
    because we assigned the name to the "OnClick property" of the
    button */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                RadioButton celsiusButton = (RadioButton)
                        findViewById(R.id.radioButton);
                RadioButton fahrenheitButton = (RadioButton)
                        findViewById(R.id.radioButton2);
                if (text.getText().length() == 0) {
                    Toast.makeText(this, "Please enter a valid number",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                float inputValue = Float.parseFloat(text.getText().toString());

                if (celsiusButton.isChecked()) {
                    text.setText(String.valueOf(ConverterUtil.convertCelsiusToFahrenheit(inputValue)));
                    celsiusButton.setChecked(false);
                    fahrenheitButton.setChecked(true);
                } else {
                    text.setText(String.valueOf(ConverterUtil.convertFahrenheitToCelsius(inputValue)));
                    fahrenheitButton.setChecked(false);
                    celsiusButton.setChecked(true);
                }
//                grab CURRENT result value now in Text Field
                inputValue = Float.parseFloat(text.getText().toString());
                view = findViewById(R.id.activity_main);
                iv = (ImageView) findViewById(R.id.imageView);

                if (inputValue > 90) {
//                    set hex color to skyblue
                    view.setBackgroundColor(Color.parseColor("#87ceff"));
                    iv.setVisibility(View.VISIBLE);
//                    clear any prior image
                    ((ImageView) iv.findViewById(R.id.imageView)).setImageResource(0);
//                    image that represent warmth
                    iv.setImageResource(R.drawable.sun);
                } else if (inputValue < 0) {
//                    set hex color to red
                    view.setBackgroundColor(Color.RED);
                    //view.setBackgroundColor(Color.rgb(0,0,0));
//                    clear image
                    ((ImageView) iv.findViewById(R.id.imageView)).setImageResource(0);
                    //image that represent coldness
                    iv.setImageResource(R.drawable.snowflake);
                } else {
//                    set yellow background color
                    view.setBackgroundColor(Color.YELLOW);
//                    clear image
                    iv.setVisibility(View.INVISIBLE);
                    ((ImageView) iv.findViewById(R.id.imageView)).setImageResource(0);
                }
                break;
        }
    }
}
