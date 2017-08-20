package com.passivealtitude.a12fly.currencyconverter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private Button btnUndo;
    private Button btnAddExpenses;
    private Button btnAddIncome;
    private Button btncExp;
    private Button btnCAdd;
    private Button btnCSpending;
    private TextView tvIncome;
    private TextView tvExpenses;
    private EditText tfExpenses;
    private EditText tfIncome;

    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orimyr = (EditText) findViewById(R.id.orimyr);
        oriwon = (EditText) findViewById(R.id.oriwon);
        cmyr = (EditText) findViewById(R.id.cmyr);
        cwon = (EditText) findViewById(R.id.cwon);

        btnConMyr = (Button) findViewById(R.id.btnConMyr);
        btnConOtr = (Button) findViewById(R.id.btnConOtr);
        btnRateMyr = (Button) findViewById(R.id.btnRateMyr);
        btnRateOtr = (Button) findViewById(R.id.btnRateOtr);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        btnUndo = (Button) findViewById(R.id.btnUndo);
        btnAddExpenses = (Button) findViewById(R.id.btnAddExpenses);
        btnAddIncome = (Button) findViewById(R.id.btnAddIncome);
        btncExp = (Button) findViewById(R.id.btncExp);
        btnCAdd = (Button) findViewById(R.id.btnCAdd);
        btnCSpending = (Button) findViewById(R.id.btnCSpending);

        tvIncome = (TextView) findViewById(R.id.tvIncome);
        tvExpenses = (TextView) findViewById(R.id.tvExpenses);
        tfIncome = (EditText) findViewById(R.id.tfIncome);
        tfExpenses = (EditText) findViewById(R.id.tfExpenses);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        orimyr.setText(sharedpreferences.getString("orimyr", "1.0"));
        oriwon.setText(sharedpreferences.getString("oriwon", "1.0"));
        cmyr.setText(sharedpreferences.getString("cmyr", "0.0"));
        cwon.setText(sharedpreferences.getString("cwon", "0.0"));
        tvIncome.setText(sharedpreferences.getString("current", "0.0"));
        tvExpenses.setText(sharedpreferences.getString("expenses", "0.0"));

        btnConMyr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setUndoPreference(orimyr.getText().toString(), oriwon.getText().toString(), cmyr.getText().toString(), cwon.getText().toString());
                try {
                    Double myrrate = Double.parseDouble(cmyr.getText().toString());
                    Double myrval = Double.parseDouble(orimyr.getText().toString());
                    Double wonval = Double.parseDouble(oriwon.getText().toString());
                    if (myrrate != 0 && myrval != 0) {
                        cwon.setText(String.format("%.4f", wonval / myrval * myrrate));
                    } else {
                        cwon.setText("0.0");
                    }

                    setOriPreference(orimyr.getText().toString(), oriwon.getText().toString(), cmyr.getText().toString(), cwon.getText().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    setOriField();
                }
            }
        });

        btnConOtr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setUndoPreference(orimyr.getText().toString(), oriwon.getText().toString(), cmyr.getText().toString(), cwon.getText().toString());
                try {
                    Double wonrate = Double.parseDouble(cwon.getText().toString());
                    Double myrval = Double.parseDouble(orimyr.getText().toString());
                    Double wonval = Double.parseDouble(oriwon.getText().toString());
                    if (wonrate != 0 && wonval != 0) {
                        cmyr.setText(String.format("%.4f", myrval / wonval * wonrate));
                    } else {
                        cmyr.setText("0.0");
                    }
                    setOriPreference(orimyr.getText().toString(), oriwon.getText().toString(), cmyr.getText().toString(), cwon.getText().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    setOriField();
                }
            }
        });

        btnRateMyr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setUndoPreference(orimyr.getText().toString(), oriwon.getText().toString(), cmyr.getText().toString(), cwon.getText().toString());
                try {
                    Double myrrate = Double.parseDouble(cmyr.getText().toString());
                    Double wonrate = Double.parseDouble(cwon.getText().toString());
                    if (myrrate != 0) {
                        oriwon.setText(String.format("%.6f", wonrate / myrrate * 1));
                    } else {
                        oriwon.setText("1.0");
                    }
                    orimyr.setText("1.0");
                    setOriPreference(orimyr.getText().toString(), oriwon.getText().toString(), cmyr.getText().toString(), cwon.getText().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    setOriField();
                }
            }
        });

        btnRateOtr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setUndoPreference(orimyr.getText().toString(), oriwon.getText().toString(), cmyr.getText().toString(), cwon.getText().toString());
                try {
                    Double myrrate = Double.parseDouble(cmyr.getText().toString());
                    Double wonrate = Double.parseDouble(cwon.getText().toString());
                    if (wonrate != 0) {
                        orimyr.setText(String.format("%.6f", myrrate / wonrate * 1));
                    } else {
                        orimyr.setText("1.0");
                    }
                    oriwon.setText("1.0");
                    setOriPreference(orimyr.getText().toString(), oriwon.getText().toString(), cmyr.getText().toString(), cwon.getText().toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    setOriField();
                }
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setUndoPreference(orimyr.getText().toString(), oriwon.getText().toString(), cmyr.getText().toString(), cwon.getText().toString());
                setOriField();
                setOriPreference(orimyr.getText().toString(), oriwon.getText().toString(), cmyr.getText().toString(), cwon.getText().toString());
            }
        });

        btnUndo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String temporimyr = orimyr.getText().toString();
                String temporiwon = oriwon.getText().toString();
                String tempcmyr = cmyr.getText().toString();
                String tempprecwon = cwon.getText().toString();

                setUndoPreference(temporimyr, temporiwon, tempcmyr, tempprecwon);

                orimyr.setText(sharedpreferences.getString("preorimyr", "1.0"));
                oriwon.setText(sharedpreferences.getString("preoriwon", "1.0"));
                cmyr.setText(sharedpreferences.getString("precmyr", "0.0"));
                cwon.setText(sharedpreferences.getString("precwon", "0.0"));

                setOriPreference(orimyr.getText().toString(), oriwon.getText().toString(), cmyr.getText().toString(), cwon.getText().toString());
            }
        });

        btnAddExpenses.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Double newVal = Double.parseDouble(tfExpenses.getText().toString());
                    Double oriVal = Double.parseDouble(tvExpenses.getText().toString());
                    Double oriInc = Double.parseDouble(tvIncome.getText().toString());
                    tvIncome.setText(String.format("%.2f", oriInc - newVal));
                    tvExpenses.setText(String.format("%.2f", newVal + oriVal));
                    tfExpenses.setText("0.00");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    tfExpenses.setText("0.00");
                }
                setSpendingPre(tvIncome.getText().toString(), tvExpenses.getText().toString());
            }
        });

        btnAddIncome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Double newVal = Double.parseDouble(tfIncome.getText().toString());
                    Double oriVal = Double.parseDouble(tvIncome.getText().toString());
                    tvIncome.setText(String.format("%.2f", oriVal + newVal));
                    tfIncome.setText("0.00");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    tfIncome.setText("0.00");
                }
                setSpendingPre(tvIncome.getText().toString(), tvExpenses.getText().toString());
            }
        });

        btncExp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tfExpenses.setText("0.00");
            }
        });

        btnCAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tfIncome.setText("0.00");
            }
        });

        btnCSpending.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tvIncome.setText("0.00");
                tvExpenses.setText("0.00");
                setSpendingPre(tvIncome.getText().toString(), tvExpenses.getText().toString());
            }
        });


        Button[] btn = {btnConMyr, btnConOtr, btnRateMyr, btnRateOtr, btnRefresh, btnUndo, btnAddExpenses, btnAddIncome, btncExp, btnCAdd, btnCSpending};
        for (int i = 0; i < btn.length; i++) {
            buttonEffect(btn[i]);
        }
    }

    public void setUndoPreference(String orimyr, String oriwon, String cmyr, String cwon) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("preorimyr", orimyr);
        editor.putString("preoriwon", oriwon);
        editor.putString("precmyr", cmyr);
        editor.putString("precwon", cwon);
        editor.commit();
    }

    public void setOriPreference(String orimyr, String oriwon, String cmyr, String cwon) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("orimyr", orimyr);
        editor.putString("oriwon", oriwon);
        editor.putString("cmyr", cmyr);
        editor.putString("cwon", cwon);
        editor.commit();
    }

    public void setSpendingPre(String current, String expenses) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("current", current);
        editor.putString("expenses", expenses);
        editor.commit();
    }

    public void setOriField() {
        orimyr.setText("1.0");
        oriwon.setText("1.0");
        cmyr.setText("0.0");
        cwon.setText("0.0");
    }

    public void buttonEffect(View button) {
        button.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }
}
