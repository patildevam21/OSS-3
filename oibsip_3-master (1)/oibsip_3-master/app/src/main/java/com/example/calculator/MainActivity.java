package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView2,txtview1;
    MaterialButton Clear,back,mod,div;
    MaterialButton seven,eight,nine,mul;
    MaterialButton four,five,six,minus;
    MaterialButton one,two,three,plus;
    MaterialButton AC,zero,dec,equal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView2=findViewById(R.id.textView2);
        txtview1=findViewById(R.id.txtview1);
        Clear=findViewById(R.id.Clear);
        back=findViewById(R.id.back);
        mod=findViewById(R.id.mod);
        div=findViewById(R.id.div);
        seven=findViewById(R.id.seven);
        eight=findViewById(R.id.eight);
        nine=findViewById(R.id.nine);
        four=findViewById(R.id.four);
        five=findViewById(R.id.five);
        six=findViewById(R.id.six);
        mul=findViewById(R.id.mul);
        minus=findViewById(R.id.minus);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        plus=findViewById(R.id.plus);
        AC=findViewById(R.id.AC);
        zero=findViewById(R.id.zero);
        dec=findViewById(R.id.dec);
        equal=findViewById(R.id.equal);

        assignId(Clear,R.id.Clear);
        assignId(back,R.id.back);
        assignId(mod,R.id.mod);
        assignId(div,R.id.div);
        assignId(seven,R.id.seven);
        assignId(eight,R.id.eight);
        assignId(nine,R.id.nine);
        assignId(four,R.id.four);
        assignId(five,R.id.five);
        assignId(six,R.id.six);
        assignId(one,R.id.one);
        assignId(two,R.id.two);
        assignId(three,R.id.three);
        assignId(plus,R.id.plus);
        assignId(minus,R.id.minus);
        assignId(mul,R.id.mul);
        assignId(AC,R.id.AC);
        assignId(dec,R.id.dec);
        assignId(zero,R.id.zero);
        assignId(equal,R.id.equal);


    }
    void assignId(MaterialButton btn,int id){
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        //textView2.setText(buttonText);
        String dataTocalculate = textView2.getText().toString();
        if (buttonText.equals("AC")) {
            textView2.setText("");
            txtview1.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            textView2.setText(txtview1.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataTocalculate = dataTocalculate.substring(0,dataTocalculate.length() - 1);


        } else {
            dataTocalculate = dataTocalculate + buttonText;
        }
        //dataTocalculate=dataTocalculate+buttonText;
        textView2.setText(dataTocalculate);

        String finalResult=getResult(dataTocalculate);
        if(!finalResult.equals("Err")){
            txtview1.setText(finalResult);
        }

    }
    String getResult(String data){
       try{
           Context context=Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable=context.initSafeStandardObjects();
           String finalResult =context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if(finalResult.endsWith(".0")){
               finalResult=finalResult.replace(".0","");
           }
           return finalResult;
       }
       catch (Exception e){
           return "Err";
       }
    }
}