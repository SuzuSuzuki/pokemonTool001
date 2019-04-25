package com.ss_company.pokemontool00;

import android.widget.EditText;

public class Change {

    /**
     *EditをStringに変換するメソッド
     * @param editText
     * @return
     */
    public String editChangeString(EditText editText){
        String string = editText.getText().toString();
        return  string;
    }

    /**
     * ユーザーが入力した値をintに変換するメソッド
     * @param editText ユーザーが入力した値
     * @return 文字列を変換した数値を返す
     */
    public int editChangeValue(EditText editText){
        int value = Integer.parseInt(editChangeString(editText));//editTextで取得した文字列を数値に変換
        return value;
    }
}

