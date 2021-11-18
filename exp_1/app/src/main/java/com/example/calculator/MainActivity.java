package com.example.calculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_input;//设置输入文本框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //引用layout中的资源
        setContentView(R.layout.activity_main);
        tv_input = findViewById(R.id.tv_input);

        //对象实例化
        Button btn_0 = findViewById(R.id.btn_0);
        Button btn_1 = findViewById(R.id.btn_1);
        Button btn_2 = findViewById(R.id.btn_2);
        Button btn_3 = findViewById(R.id.btn_3);
        Button btn_4 = findViewById(R.id.btn_4);
        Button btn_5 = findViewById(R.id.btn_5);
        Button btn_6 = findViewById(R.id.btn_6);
        Button btn_7 = findViewById(R.id.btn_7);
        Button btn_8 = findViewById(R.id.btn_8);
        Button btn_9 = findViewById(R.id.btn_9);

        Button btn_dot = findViewById(R.id.btn_dot);
        Button btn_divide = findViewById(R.id.btn_divide);
        Button btn_multiply = findViewById(R.id.btn_multiply);
        Button btn_add = findViewById(R.id.btn_add);
        Button btn_minus = findViewById(R.id.btn_minus);
        Button btn_equals= findViewById(R.id.btn_equals);
        Button btn_del = findViewById(R.id.btn_del);
        Button btn_negative = findViewById(R.id.btn_negative);
        Button btn_root = findViewById(R.id.btn_root);
        Button btn_pow = findViewById(R.id.btn_pow);
        Button btn_clear = findViewById(R.id.btn_clear);
        Button btn_sin = findViewById(R.id.btn_sin);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_equals.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_negative.setOnClickListener(this);
        btn_root.setOnClickListener(this);
        btn_pow.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_sin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        String pastStr = tv_input.getText().toString();//得到之前文本框里的内容
        if(pastStr.equals("0"))
            tv_input.setText("");//如果为0，设置内容为空

        String currentStr = tv_input.getText().toString();//储存当前输入之后的结果

        switch(v.getId()){//判断，根据按钮button的id设置输入数值

            //-----数字按钮-----//
            case R.id.btn_0:{
                currentStr+="0";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_1:{
                currentStr+="1";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_2:{
                currentStr+="2";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_3:{
                currentStr+="3";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_4:{
                currentStr+="4";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_5:{
                currentStr+="5";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_6:{
                currentStr+="6";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_7:{
                currentStr+="7";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_8:{
                currentStr+="8";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_9:{
                currentStr+="9";
                tv_input.setText(currentStr);
            }break;

            //-----计算元素按钮-----//
            case R.id.btn_dot:{//点
                currentStr+=".";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_divide:{//除
                currentStr+="/";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_multiply:{//乘
                currentStr+="*";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_add:{//加
                currentStr+="+";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_minus:{//减
                currentStr+="-";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_root:{//开平方根
                currentStr+="sqrt(";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_pow:{//指数
                currentStr+="^";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_del:{//从后删除
                if (TextUtils.isEmpty(tv_input.getText())) {//已经为空则不做任何操作
                    return;
                }
                //不为空，则取除去最后一个字符后的字符串长度
                tv_input.setText(currentStr.substring(0, currentStr.length()-1));
                //
            }break;
            /*
            string str="123abc456";
            int i=3;
            1 取字符串的前i个字符
            str=str.Substring(0,i); // or str=str.Remove(i,str.Length-i);
            2 去掉字符串的前i个字符：
            str=str.Remove(0,i); // or str=str.Substring(i);
            3 从右边开始取i个字符：
            str=str.Substring(str.Length-i); // or str=str.Remove(0,str.Length-i);
            4 从右边开始去掉i个字符：
            str=str.Substring(0,str.Length-i); // or str=str.Remove(str.Length-i,i);
             */
            case R.id.btn_negative:{//取正负号
                if(TextUtils.isEmpty(tv_input.getText())){
                    return;//判断输入框是否为空
                }
                if(currentStr.substring(0,1) == "-"){//如果前面有负号，则去除负号
                    currentStr = currentStr.substring(1,currentStr.length()-1);
                    tv_input.setText(currentStr);
                }else{
                    currentStr = "-" + currentStr;
                    tv_input.setText(currentStr);
                }
            }break;

            case R.id.btn_clear:{//清空输入文本框
                currentStr="";
                tv_input.setText(currentStr);
            }break;
            case R.id.btn_sin:{//sin计算
                currentStr+="sin(";
                tv_input.setText(currentStr);
            }break;

            case R.id.btn_equals:{
                String result = "";
                //判断计算结果，根据输入值进行合理性判断，用toast语法进行提醒
                if(Calculate.cal(currentStr)=="Infinity"){//除数为0
                    Toast.makeText(MainActivity.this,"除数不能为0",Toast.LENGTH_SHORT).show();
                    result = "0";
                }else if(Calculate.cal(currentStr)=="NaN"){//负数不能开平方根
                    Toast.makeText(MainActivity.this,"负数不能开平方根",Toast.LENGTH_SHORT).show();
                    result = "0";
                }else{
                    result = Calculate.cal(currentStr)+"\n";
                }

                tv_input.setText(result);
            }break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

}
