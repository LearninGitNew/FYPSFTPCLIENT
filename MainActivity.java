package com.example.vernanda.fypsftpclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText addressinputtext = (EditText) findViewById(R.id.adresstextinput);
        final EditText portinputtext = (EditText) findViewById(R.id.porttextinput);
        final Button connectbutton = (Button) findViewById(R.id.connectbutton);



        connectbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent;
                intent = new Intent(MainActivity.this,login_screen.class);
                String addresstextresult = addressinputtext.getText().toString();
                String porttextresult = portinputtext.getText().toString();
                Bundle extras = new Bundle();
                extras.putString("ADDRESSTEXT", addresstextresult);
                extras.putString("PORTTEXT",porttextresult);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }

}
