package com.example.dialogdraw;

import android.app.Dialog;
import android.os.Bundle;

import com.example.dialogdraw.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final WebView webView = findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/UI.html");
        webView.getSettings().setJavaScriptEnabled(true);




        final View mView = getLayoutInflater().inflate(R.layout.dialog_card, null);
        Button drawBtn = (Button) mView.findViewById(R.id.drawBtn);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();

            }
        });


        drawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input = ((TextView) mView.findViewById(R.id.DrawNum)).getText().toString();
                webView.evaluateJavascript("addNode("+ input +")",num -> {
                    Log.d("WEBVIEW", "returned from webview is: " + num);
                });
                dialog.dismiss();
            }


        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.clear) {
            WebView webView = findViewById(R.id.webView);
            webView.reload();
            return true;

        }

        return super.onOptionsItemSelected(item);

    }
}
