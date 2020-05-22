package com.example.addend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OptionsActivity extends AppCompatActivity {

    Button backButton, playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionsActivity.this, MainActivity.class));
            }
        });

        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.setOperation(((RadioButton) findViewById(((RadioGroup) findViewById(R.id.selectOperation)).getCheckedRadioButtonId())).getText().toString().charAt(0));
                Model.setGameVersion(((RadioButton) findViewById(((RadioGroup) findViewById(R.id.gameMode)).getCheckedRadioButtonId())).getText().toString());
                startActivity(new Intent(OptionsActivity.this, PlayActivity.class));
            }
        });
    }
}
