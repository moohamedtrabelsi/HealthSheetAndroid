package com.example.healthsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class test extends AppCompatActivity {
    TextView content;
    EditText fname, email, login, pass;
    String Name, Email, Login, Pass;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        content    =   (TextView)findViewById( R.id.content );
        fname      =   (EditText)findViewById(R.id.name);
        email      =   (EditText)findViewById(R.id.email);
        login      =    (EditText)findViewById(R.id.loginname);
        pass       =   (EditText)findViewById(R.id.password);


        Button saveme=(Button)findViewById(R.id.save);

        saveme.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v)
            {
                try{

                    // CALL GetText method to make post method call
                    GetText();
                }
                catch(Exception ex)
                {
                    content.setText(" url exeption! " );
                }
            }
        });
    }

    // Create GetText Metod
    public  void  GetText()  throws UnsupportedEncodingException
    {
        // Get user defined values
        Name = fname.getText().toString();
        Email   = email.getText().toString();
        Login   = login.getText().toString();
        Pass   = pass.getText().toString();

        // Create data variable for sent values to server

        String data =  URLEncoder.encode("username", "UTF-8")
                + "=" + URLEncoder.encode(Login, "UTF-8");

        data += "&" + URLEncoder.encode("password", "UTF-8")
                + "=" + URLEncoder.encode(Pass, "UTF-8");

        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL("http://127.0.0.1:3000/api/auth/signin");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();
/// retrofit / volleycallback
            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            if(reader.readLine()!=null)
            {
                System.out.println("notnull");
            }
            else {
                System.out.println("null");
            }

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
                content.setText( content.getText()+line);
            }


            text = sb.toString();
        }
        catch(Exception ex)
        {

        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {}
        }

        // Show response on activity
        //content.setText( text  );

    }

}