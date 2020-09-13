package com.example.addend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;


public class PlayActivity extends AppCompatActivity {

    Model model = new Model();
    boolean enterPressed = false, over;
    Button nextButton;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        score = 0;

        TextView checkCorrect = findViewById(R.id.CheckCorrect);
        checkCorrect.setVisibility(View.INVISIBLE);

        EditText input = findViewById(R.id.input);
        input.requestFocus();
        input.setImeOptions(EditorInfo.IME_ACTION_DONE);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        ((TextView) findViewById(R.id.firstNumber)).setText(Integer.toString(model.getTopNumber()));
        ((TextView) findViewById(R.id.secondNumber)).setText(Integer.toString(model.getBottomNumber()));
        ((TextView) findViewById(R.id.operatorSymbol)).setText(Character.toString(Model.getOperation()));
        ((TextView) findViewById(R.id.gameType)).setText(Model.getGameVersion());

        nextButton = findViewById(R.id.NEXT_BUTTON);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterPressed = true;
            }
        });

        CountDownTimer timer;
        if (Model.getGameVersion().equals("Infinite")) {
                // TODO: Create function for start time
                timer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        ((TextView) findViewById(R.id.timer)).setText(millisUntilFinished / 1000 + "");
                        if (enterPressed) {
                            EditText input = findViewById(R.id.input);
                            int result = Integer.MIN_VALUE;
                            try {
                                if (input.getText().toString() != null &&
                                        model.isAnswerCorrect(Integer.parseInt(input.getText().toString()))) {
                                    ((TextView) findViewById(R.id.CheckCorrect)).setText("Correct!");
                                    findViewById(R.id.CheckCorrect).setVisibility(View.VISIBLE);
                                    score++;
                                    ((TextView) findViewById(R.id.firstNumber)).setText(Integer.toString(model.changeTopNumber()));
                                    ((TextView) findViewById(R.id.secondNumber)).setText(Integer.toString(model.changeBottomNumber()));
                                    input.setText("");
                                    input.requestFocus();
                                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
                                    enterPressed = false;
                                    start();
                                } else {
                                    ((TextView) findViewById(R.id.CheckCorrect)).setText("Incorrect!");
                                    findViewById(R.id.CheckCorrect).setVisibility(View.VISIBLE);
                                    Model.changeStillGoing(false);
                                    cancel();
                                }
                            } catch (NumberFormatException e) {
                                Model.changeStillGoing(false);
                                cancel();
                            }
                            enterPressed = false;
                        }
                    }

                    @Override
                    public void onFinish() {
                        Model.changeStillGoing(false);
                    }
                }.start();
        } else {
            timer = new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    ((TextView) findViewById(R.id.timer)).setText(millisUntilFinished / 1000 + "");
                    if (enterPressed) {
                        EditText input = findViewById(R.id.input);
                        int result = Integer.MIN_VALUE;
                        try {
                            if (input.getText().toString() != null &&
                                    model.isAnswerCorrect(Integer.parseInt(input.getText().toString()))) {
                                score++;
                                ((TextView) findViewById(R.id.firstNumber)).setText(Integer.toString(model.changeTopNumber()));
                                ((TextView) findViewById(R.id.secondNumber)).setText(Integer.toString(model.changeBottomNumber()));
                                input.setText("");
                                input.requestFocus();
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
                                enterPressed = false;
                            }
                        } catch (NumberFormatException e) {
                            // nothing to do here
                            e.getMessage();
                        }
                        enterPressed = false;
                    }
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }


    }
}
