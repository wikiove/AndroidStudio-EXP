package com.example.calculator;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

public class Calculate {
    String formula = "";

    public Calculate(String formula){
        this.formula = formula;
    }

    public static String cal(String formula){
        String result = "";//结果初始化

        try {
            Symbols s = new Symbols();

            double res = s.eval(formula);//计算公式的值
            result = String.valueOf(res);//将bouble里的内容转换为字符串形式

        } catch (SyntaxException e) {
            result="Error Input\nPress Clear button to continue";
        }

        return result;//返回计算结果
    }
}
