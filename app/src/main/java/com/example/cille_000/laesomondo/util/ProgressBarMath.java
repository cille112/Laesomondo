package com.example.cille_000.laesomondo.util;



public class ProgressBarMath {

    public ProgressBarMath() {

    }


    public int progressBarResault(int level, int totalxp) {
        double p;
        if(level >= 1 && level <= 9){
            p = (totalxp / (150 * (Math.pow(level, (1.5))))) * 100;
        } else if (level == 10){
            p = 100;
        } else {
            p = 0;
        }

        return (int) p;
    }

}
