package com.example.vernanda.fypsftpclient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Vernanda on 18/9/2018.
 */

public class login_screen extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        final TextView addressresult = (TextView)findViewById(R.id.AdressResult);
        TextView portresult = (TextView)findViewById(R.id.PortResult);
        final TextView errorresult = (TextView)findViewById(R.id.Errorcode);
        final TextView Pathtext =(TextView)findViewById(R.id.Pathtext);
        final EditText usernametext = (EditText)findViewById(R.id.Nameinputlogin);
        final EditText passwordtext = (EditText)findViewById(R.id.Passwordinputlogin);
        Button loginbutton = (Button)findViewById(R.id.LoginButton);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String addresstext = extras.getString("ADDRESSTEXT");
        final String porttext = extras.getString("PORTTEXT");
        final int portint =   Integer.parseInt(porttext);

        addressresult.setText(addresstext);
        portresult.setText(porttext);

        loginbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View vi){
            Writer writer = new StringWriter();
            String username = usernametext.getText().toString();
            String password = passwordtext.getText().toString();
            addressresult.setText(username);
            JSch jsch = new JSch();
                Session session = null;
                try {
                    session = jsch.getSession(username, addresstext, portint);
                } catch (JSchException e) {
                    e.printStackTrace(new PrintWriter(writer));
                    String s = writer.toString();
                    errorresult.setText(s);
                }
                session.setPassword(password);
                try {
                    session.connect();
                } catch (JSchException e) {
                    e.printStackTrace(new PrintWriter(writer));
                    String s = writer.toString();
                    errorresult.setText(s);
                }
                try {
                    Channel channel = session.openChannel("sftp");
                    channel.connect();
                    ChannelSftp c = (ChannelSftp)channel;
                    String lel = c.getHome();
                    Pathtext.setText(lel);
                } catch (JSchException e) {
                    e.printStackTrace(new PrintWriter(writer));
                    String s = writer.toString();
                    errorresult.setText(s);
                } catch (SftpException e) {
                    e.printStackTrace(new PrintWriter(writer));
                    String s = writer.toString();
                    errorresult.setText(s);
                }


            }
        });

    }


    private class MyTask extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... arg0) {
            JSch jsch = new JSch();
            Session session = null;
            try {
                session = jsch.getSession(arg0, addresstext, portint);
            } catch (JSchException e) {
                e.printStackTrace(new PrintWriter(writer));
                String s = writer.toString();
                errorresult.setText(s);
            }
            session.setPassword(password);
            try {
                session.connect();
            } catch (JSchException e) {
                e.printStackTrace(new PrintWriter(writer));
                String s = writer.toString();
                errorresult.setText(s);
            }
            try {
                Channel channel = session.openChannel("sftp");
                channel.connect();
                ChannelSftp c = (ChannelSftp)channel;
                String lel = c.getHome();
                Pathtext.setText(lel);
            } catch (JSchException e) {
                e.printStackTrace(new PrintWriter(writer));
                String s = writer.toString();
                errorresult.setText(s);
            } catch (SftpException e) {
                e.printStackTrace(new PrintWriter(writer));
                String s = writer.toString();
                errorresult.setText(s);
            }
        }
    }
}
