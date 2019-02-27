package com.example.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    LayoutInflater inflater;
    AlertDialog.Builder dialog;
    private View dialogView;

    Handler progressDialogHandler = new Handler();
    ProgressDialog progressDialog;
    Runnable runCheck, runMain;
    int progressDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogShow();
            }
        });
    }

    private void dialogShow() {
        dialog = new AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_layout, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(android.R.mipmap.sym_def_app_icon);
        dialog.setTitle("Form Biodata");

        final TextInputEditText edtName, edtAge, edtAddress, edtQuote;
        edtName = dialogView.findViewById(R.id.edt_name);
        edtAge = dialogView.findViewById(R.id.edt_age);
        edtAddress = dialogView.findViewById(R.id.edt_address);
        edtQuote = dialogView.findViewById(R.id.edt_quote);

        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this, ResultActivity.class)
                        .putExtra("name", edtName.getText().toString())
                        .putExtra("age", edtAge.getText().toString())
                        .putExtra("address", edtAddress.getText().toString())
                        .putExtra("quote", edtQuote.getText().toString())
                );
            }
        });
        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnOnClick(final View view) {
        progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Proses Mengambil Data");
        progressDialog.setMax(100);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading");
        progressDialog.setIcon(R.mipmap.ic_launcher_round);
        progressDialog.show();
        progressDownload = 0;


        runMain = new Runnable() {
            @Override
            public void run() {
                while (progressDownload < 100) {
                    progressDownload += 10;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressDialogHandler.post(runCheck);
                }
            }
        };
        runCheck = new Runnable() {
            @Override
            public void run() {
                progressDialog.setProgress(progressDownload);
                if (progressDownload >= 100) {
                    progressDialog.dismiss();
                    Snackbar.make(view, "Download Selesai", Snackbar.LENGTH_LONG).show();
                }
            }
        };

        Thread jalan = new Thread(runMain);
        jalan.start();
    }
}
