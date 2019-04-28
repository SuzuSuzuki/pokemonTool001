package com.ss_company.pokemontool00;

//ノートPCのブランチ

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 作成日：２０１８年１１月２１日
 * @version
 * @author Shunta Suzuki
 * ファイル：MainActivity.java
 * 画面の生成、イベント処理のクラス
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner();//スピナーの表示
        main();//画面の


    }

    private void main(){
        final EditText editObjectiveValue = (EditText) findViewById(R.id.objectiveValue);      //目的の素早さ
        final EditText editBaseStats = (EditText) findViewById(R.id.baseStats);                //種族値
        final EditText editIndividualValue = (EditText) findViewById(R.id.individualValue);    //個体値
        final EditText editLevel = (EditText) findViewById(R.id.level);                        //レベル
        final Calculate cal = new Calculate();//ポケモンの計算クラスのインスタンス
        final Change change = new Change();
        final int effortValueMax = 252;//最大の努力値

        final RadioGroup selectCorrection = (RadioGroup) findViewById(R.id.radioGroupCorrection);//ラジオグループの処理
        selectCorrection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                //入力データがないことを確認する
                if (change.editChangeString(editObjectiveValue).length() != 0 ||
                        change.editChangeString(editBaseStats).length() != 0 ||
                        change.editChangeString(editIndividualValue).length() != 0 ||
                        change.editChangeString(editLevel).length() != 0) {

                    int objectiveValue = change.editChangeValue(editObjectiveValue);   //目的の素早さ
                    int baseStats = change.editChangeValue(editBaseStats);             //種族値
                    int individualValue = change.editChangeValue(editIndividualValue); //個体値
                    int level = change.editChangeValue(editLevel);                     //レベル

                    //RadioButtonのテキストでどれがチェックされているか確認する
                    RadioButton select = (RadioButton) findViewById(checkedId);
                    switch (select.getText().toString()) {
                        case "補正なし計算":
                            //Toast.makeText(MainActivity.this, "補正なし",Toast.LENGTH_SHORT).show();
                            int resultNoCorrection = cal.adjustmentNoCorrection(objectiveValue, baseStats, individualValue, level);
                            if (resultNoCorrection <= effortValueMax) {
                                if (resultNoCorrection == 0){//努力値が０なら振る必要はない
                                    TextView setResultNoCorrection = findViewById(R.id.result);
                                    setResultNoCorrection.setText("振る必要はありません");
                                }else {
                                    TextView setResultNoCorrection = findViewById(R.id.result);
                                    setResultNoCorrection.setText(String.valueOf(resultNoCorrection));
                                }
                            } else {
                                TextView setResultNoCorrection = findViewById(R.id.result);
                                setResultNoCorrection.setText("不正な値");
                            }
                            break;
                        case "補正あり計算":
                            //Toast.makeText(MainActivity.this, "補正あり",Toast.LENGTH_SHORT).show();
                            int resultCorrection = cal.adjustmentCorrection(objectiveValue, baseStats, individualValue, level);
                            if (resultCorrection <= effortValueMax) {
                                if (resultCorrection == 0){
                                    TextView setResultNoCorrection = findViewById(R.id.result);
                                    setResultNoCorrection.setText("振る必要はありません");
                                }else {
                                    TextView setResultCorrection = findViewById(R.id.result);
                                    setResultCorrection.setText(String.valueOf(resultCorrection));
                                }
                            } else {
                                TextView setResultNoCorrection = findViewById(R.id.result);
                                setResultNoCorrection.setText("不正な値");
                            }
                            break;
                        case "逆補正計算":
                            //Toast.makeText(MainActivity.this, "逆補正",Toast.LENGTH_SHORT).show();
                            int resultInverseCorrection = cal.adjustmentInverseCorrection(objectiveValue, baseStats, individualValue, level);
                            if (resultInverseCorrection <= effortValueMax) {
                                if (resultInverseCorrection == 0){
                                    TextView setResultNoCorrection = findViewById(R.id.result);
                                    setResultNoCorrection.setText("振る必要はありません");
                                }else {
                                    TextView setResultInverseCorrection = findViewById(R.id.result);
                                    setResultInverseCorrection.setText(String.valueOf(resultInverseCorrection));
                                }
                            } else {
                                TextView setResultNoCorrection = findViewById(R.id.result);
                                setResultNoCorrection.setText("不正な値");
                            }
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                } else {//データが入力されていなければ表示
                    Toast.makeText(MainActivity.this, "入力データがありません", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void spinner (){

        String spinnerItemIV[] = {"0","30","31"};//個体値一覧
        String spinnerItemLV[] = {"50","100"};//レベル一覧
        Spinner spinnerIV = findViewById(R.id.individualValueSpinner);
        Spinner spinnerLV = findViewById(R.id.levelSpinner);

        ArrayAdapter adapterIV = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItemIV);
        adapterIV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIV.setAdapter(adapterIV);

        ArrayAdapter adapterLV = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItemLV);
        adapterLV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLV.setAdapter(adapterLV);


    }
}