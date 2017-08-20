package com.passivealtitude.a12fly.currencyconverter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "Converter";
    private EditText orimyr;
    private EditText oriwon;
    private EditText cmyr;
    private EditText cwon;
    private Button btnConMyr;
    private Button btnConOtr;
    private Button btnRateMyr;
    private Button btnRateOtr;
    private Button btnRefresh;
    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        orimyr = (EditText) findViewById(R.id.orimyr);
        oriwon = (EditText) findViewById(R.id.oriwon);
        cmyr = (EditText) findViewById(R.id.cmyr);
        cwon = (EditText) findViewById(R.id.cwon);

        orimyr.setText(sharedpreferences.getString("orimyr", "1.0"));
        oriwon.setText(sharedpreferences.getString("oriwon", "1.0"));
        cmyr.setText(sharedpreferences.getString("cmyr", "1.0"));
        cwon.setText(sharedpreferences.getString("cwon", "1.0"));

        btnConMyr = (Button) findViewById(R.id.btnConMyr);
        btnConMyr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Double myrrate = Double.parseDouble(cmyr.getText().toString());
                    Double myrval = Double.parseDouble(orimyr.getText().toString());
                    Double wonval = Double.parseDouble(oriwon.getText().toString());
                    if (myrrate != 0) {
                        cwon.setText(String.format("%.4f", wonval / myrval * myrrate));
                    } else {
                        cwon.setText("0.0");
                    }

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("orimyr", orimyr.getText().toString());
                    editor.putString("oriwon", oriwon.getText().toString());
                    editor.putString("cmyr", cmyr.getText().toString());
                    editor.putString("cwon", cwon.getText().toString());
                    editor.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    orimyr.setText("1.0");
                    oriwon.setText("1.0");
                    cmyr.setText("0.0");
                    cwon.setText("0.0");
                }
            }
        });

        btnConOtr = (Button) findViewById(R.id.btnConOtr);
        btnConOtr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Double wonrate = Double.parseDouble(cwon.getText().toString());
                    Double myrval = Double.parseDouble(orimyr.getText().toString());
                    Double wonval = Double.parseDouble(oriwon.getText().toString());
                    if (wonrate != 0) {
                        cmyr.setText(String.format("%.4f", myrval / wonval * wonrate));
                    } else {
                        cmyr.setText("0.0");
                    }

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("orimyr", orimyr.getText().toString());
                    editor.putString("oriwon", oriwon.getText().toString());
                    editor.putString("cmyr", cmyr.getText().toString());
                    editor.putString("cwon", cwon.getText().toString());
                    editor.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    orimyr.setText("1.0");
                    oriwon.setText("1.0");
                    cmyr.setText("0.0");
                    cwon.setText("0.0");
                }
            }
        });

        btnRateMyr = (Button) findViewById(R.id.btnRateMyr);
        btnRateMyr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Double myrrate = Double.parseDouble(cmyr.getText().toString());
                    Double wonrate = Double.parseDouble(cwon.getText().toString());
                    if (wonrate != 0 && myrrate != 0) {
                        oriwon.setText(String.format("%.6f", wonrate / myrrate * 1));
                    } else {
                        oriwon.setText("1.0");
                    }
                    orimyr.setText("1.0");


                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("orimyr", orimyr.getText().toString());
                    editor.putString("oriwon", oriwon.getText().toString());
                    editor.putString("cmyr", cmyr.getText().toString());
                    editor.putString("cwon", cwon.getText().toString());
                    editor.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    cwon.setText("0.0");
                    cmyr.setText("0.0");
                }
            }
        });

        btnRateOtr = (Button) findViewById(R.id.btnRateOtr);
        btnRateOtr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Double myrrate = Double.parseDouble(cmyr.getText().toString());
                    Double wonrate = Double.parseDouble(cwon.getText().toString());
                    if (wonrate != 0 && myrrate != 0) {
                        orimyr.setText(String.format("%.6f", myrrate / wonrate * 1));
                    } else {
                        orimyr.setText("1.0");
                    }
                    oriwon.setText("1.0");


                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("orimyr", orimyr.getText().toString());
                    editor.putString("oriwon", oriwon.getText().toString());
                    editor.putString("cmyr", cmyr.getText().toString());
                    editor.putString("cwon", cwon.getText().toString());
                    editor.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    cwon.setText("0.0");
                    cmyr.setText("0.0");
                }
            }
        });

        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                orimyr.setText("1.0");
                oriwon.setText("1.0");
                cmyr.setText("0.0");
                cwon.setText("0.0");

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("orimyr", orimyr.getText().toString());
                editor.putString("oriwon", oriwon.getText().toString());
                editor.putString("cmyr", cmyr.getText().toString());
                editor.putString("cwon", cwon.getText().toString());
                editor.commit();
            }
        });
    }
}
