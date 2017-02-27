package cis195.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToe extends AppCompatActivity {

    private boolean playerOne = true;
    private int numTurns;
    private final String TURN = getString(R.string.append_turn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        TextView text = (TextView) findViewById(R.id.textView);
        text.setText(getIntent().getStringExtra("playerOne") + TURN);
    }

    public void clickListen(View view) {
        TextView text = (TextView) findViewById(R.id.textView);
        Button btn = (Button) findViewById(view.getId());

        if(playerOne && btn.getText().toString().isEmpty()) {
            playerOne = false;
            btn.setText("X");
            numTurns++;
        }
        else if (!playerOne && btn.getText().toString().isEmpty()) {
            playerOne = true;
            btn.setText("O");
            numTurns++;
        }


        if(!((Button) findViewById(R.id.one)).getText().toString().isEmpty()) {
            if(((Button) findViewById(R.id.one)).getText().toString().equals(((Button)
                    findViewById(R.id.two)).getText().toString()) && ((Button)
                    findViewById(R.id.one)).getText().toString().equals(((Button)
                    findViewById(R.id.three)).getText().toString()) ||
                    ((Button) findViewById(R.id.one)).getText().toString().equals(((Button)
                            findViewById(R.id.four)).getText().toString()) && ((Button)
                            findViewById(R.id.one)).getText().toString().equals(((Button)
                            findViewById(R.id.seven)).getText().toString())) {
                        winCondition();
            }
        }
        else if(!((Button) findViewById(R.id.five)).getText().toString().isEmpty()) {
            if (((Button) findViewById(R.id.five)).getText().toString().equals(((Button)
                    findViewById(R.id.two)).getText().toString()) && ((Button)
                    findViewById(R.id.five)).getText().toString().equals(((Button)
                    findViewById(R.id.eight)).getText().toString()) ||
                    ((Button) findViewById(R.id.five)).getText().toString().equals(((Button)
                            findViewById(R.id.four)).getText().toString()) && ((Button)
                            findViewById(R.id.five)).getText().toString().equals(((Button)
                            findViewById(R.id.six)).getText().toString())
                     ||
                    ((Button) findViewById(R.id.five)).getText().toString().equals(((Button)
                            findViewById(R.id.one)).getText().toString()) && ((Button)
                            findViewById(R.id.five)).getText().toString().equals(((Button)
                            findViewById(R.id.nine)).getText().toString())
                    ||
                    ((Button) findViewById(R.id.five)).getText().toString().equals(((Button)
                            findViewById(R.id.three)).getText().toString()) && ((Button)
                            findViewById(R.id.five)).getText().toString().equals(((Button)
                            findViewById(R.id.seven)).getText().toString())) {
                winCondition();
            }
        }
            else if (!((Button) findViewById(R.id.nine)).getText().toString().isEmpty()) {
                if (((Button) findViewById(R.id.nine)).getText().toString().equals(((Button)
                        findViewById(R.id.eight)).getText().toString()) && ((Button)
                        findViewById(R.id.nine)).getText().toString().equals(((Button)
                        findViewById(R.id.seven)).getText().toString()) ||
                        ((Button) findViewById(R.id.nine)).getText().toString().equals(((Button)
                                findViewById(R.id.six)).getText().toString()) && ((Button)
                                findViewById(R.id.nine)).getText().toString().equals(((Button)
                                findViewById(R.id.three)).getText().toString())) {
                winCondition();
            }
        }

        if(numTurns == 9) {
            drawCondition();
        }

        if(playerOne) {
            text.setText(getIntent().getStringExtra("playerOne") + TURN);
        }
        else {
            text.setText(getIntent().getStringExtra("playerTwo") + TURN);
        }
    }


    public void winCondition() {
        String result = playerOne ? getIntent().getStringExtra("playerTwo") :
                getIntent().getStringExtra("playerOne");
        String loser = playerOne ? getIntent().getStringExtra("playerOne") :
                getIntent().getStringExtra("playerTwo");
        createDialog(result + getString(R.string.won));
        SharedPreferences sharedPref = getSharedPreferences("LevelScores",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(result, sharedPref.getInt(result, 0) + 1);
        editor.putInt(loser, sharedPref.getInt(loser, 0));
    }

    public void drawCondition() {
        createDialog(getString(R.string.draw));
        SharedPreferences sharedPref = getSharedPreferences("LevelScores",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getIntent().getStringExtra("playerOne"),
                sharedPref.getInt(getIntent().getStringExtra("playerOne"), 0));
        editor.putInt(getIntent().getStringExtra("playerTwo"),
                sharedPref.getInt(getIntent().getStringExtra("playerTwo"), 0));
    }

    public void createDialog(String condition) {
        new AlertDialog.Builder(this)
                .setTitle(condition)
                .setItems(R.array.end_options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0) {
                            startActivity(new Intent(getBaseContext(), NameEntry.class));
                        }
                        else if (which == 1) {
                            startActivity(new Intent(getBaseContext(), Leaderboard.class));
                        }
                        else {
                            startActivity(new Intent(getBaseContext(), MainActivity.class));
                        }
                    }
                })
                .setIcon(android.R.drawable.ic_menu_view)
                .show();
    }

}

