package cis195.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NameEntry extends AppCompatActivity {

    EditText firstPlayer;
    EditText secondPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_entry);
        firstPlayer = (EditText)findViewById(R.id.editText);
        secondPlayer = (EditText) findViewById(R.id.editText2);
    }

    public void sendNames(View view) {
        if(firstPlayer.getText().toString().isEmpty() || secondPlayer.getText().toString().isEmpty()) {
            Toast.makeText(this, "Must enter names for both players", Toast.LENGTH_SHORT).show();
        }
        else if(firstPlayer.getText().toString().equals(secondPlayer.getText().toString())) {
            Toast.makeText(this, "Must enter different names", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(getBaseContext(), TicTacToe.class);
            intent.putExtra("playerOne", firstPlayer.getText().toString());
            intent.putExtra("playerTwo", secondPlayer.getText().toString());
            startActivity(intent);

        }
    }
}
