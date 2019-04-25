package com.ss_company.pokemontool00;

/**
 * 作成日：２０１８年１１月２１日
 * @version 0.0.001
 * @author Shunta Suzuki
 * ファイル：Calculate.java
 * ポケモンの能力関係の計算をするクラス
 */
public class Calculate {

    /**
     * 補正なしの素早さ調整
     * @param objectiveValue 目的の素早さ
     * @param baseStats 種族値
     * @param individualValue 個体値
     * @param level レベル
     * @return
     */
    public int adjustmentNoCorrection(int objectiveValue,int baseStats,int individualValue,int level){
        int effortValue = 0;
        int tmp = 0;
        while(objectiveValue != tmp){
            //目的の素早さが最初の計算結果より小さければループを抜ける（努力値を振る必要がない）
            if (objectiveValue <= (int)abilityCalNoCorrection(baseStats,individualValue,0,level))
                return 0;
            effortValue = effortValue + 4;
            tmp = (int)abilityCalNoCorrection(baseStats,individualValue,effortValue,level);
        }
        return effortValue;
    }

    /**
     * 補正ありの素早さ調整
     * @param objectiveValue 目的の素早さ
     * @param baseStats 種族値
     * @param individualValue 個体値
     * @param level レベル
     * @return
     */
    public int adjustmentCorrection(int objectiveValue,int baseStats,int individualValue,int level){
        int effortValue = 0;
        int tmp = 0;
        while(objectiveValue != tmp){
            //目的の素早さが最初の計算結果より小さければループを抜ける（努力値を振る必要がない）
            if (objectiveValue <= (int)abilityCalCorrection(baseStats,individualValue,0,level))
                return 0;
            effortValue = effortValue + 4;
            tmp = (int)abilityCalCorrection(baseStats,individualValue,effortValue,level);
        }
        return effortValue;
    }

    /**
     * 逆補正の素早さ調整
     * @param objectiveValue 目的の素早さ
     * @param baseStats 種族値
     * @param individualValue 個体値
     * @param level レベル
     * @return
     */
    public int adjustmentInverseCorrection(int objectiveValue,int baseStats,int individualValue,int level){
        int effortValue = 0;
        int tmp = 0;
        while(objectiveValue != tmp){
            //目的の素早さが最初の計算結果より小さければループを抜ける（努力値を振る必要がない）
            if (objectiveValue <= abilityCalInverseCorrection(baseStats,individualValue,0,level))
                return  0;
            effortValue = effortValue + 4;
            tmp = (int)abilityCalInverseCorrection(baseStats,individualValue,effortValue,level);
        }
        return effortValue;
    }

    /**
     * 補正なしのステータス計算
     * @param baseStats 種族値
     * @param individualValue 個体値
     * @param effortValue 努力値
     * @param level レベル
     * @return  計算したポケモンの能力値を返す
     */
    public int abilityCalNoCorrection(int baseStats,int individualValue,int effortValue,int level){
        int abilityResult;  //ポケモンの能力値の結果
        abilityResult = ((baseStats * 2 + effortValue / 4 + individualValue) * level / 100) + 5;
        return  abilityResult;
    }

    /**
     * 補正ありのステータス計算
     * @param baseStats 種族値
     * @param individualValue 個体値
     * @param effortValue 努力値
     * @param level レベル
     * @return
     */
    public double abilityCalCorrection(int baseStats,int individualValue,int effortValue,int level){
        double abilityResult;
        abilityResult = abilityCalNoCorrection(baseStats,individualValue,effortValue,level) * 1.1;
        return abilityResult;
    }

    /**
     * 逆補正のステータス計算
     * @param baseStats 種族値
     * @param individualValue 個体値
     * @param effortValue 努力値
     * @param level レベル
     * @return
     */
    public double abilityCalInverseCorrection(int baseStats,int individualValue,int effortValue,int level){
        double abilityResult;
        abilityResult = abilityCalNoCorrection(baseStats,individualValue,effortValue,level) * 0.9;
        return abilityResult;
    }

    /**
     * HPの計算
     * @param baseStats 種族値(HP)
     * @param individualValue 個体値
     * @param effortValue 努力値
     * @param level レベル
     * @return  計算したポケモンの能力値を返す
     */
    public int hpCal(int baseStats,int individualValue,int effortValue,int level){
        int hpResult;
        hpResult = ((baseStats * 2 + effortValue / 4 +individualValue) * level / 100) + 10 + level;
        return hpResult;
    }

}
