package com.example.cille_000.laesomondo.util;



public class ProgressBarMath {

    public ProgressBarMath() {

    }


    public int progressBarResault(int level, int totalxp) {
        double p;
        if(level == 1){
            p = ((totalxp  * 100)/ (200 * (Math.pow(level+1, (1.5)))));
        } else if (level >= 2 && level <= 9){
            p = (((totalxp - (200 * (Math.pow(level, (1.5) ))))* 100) /
                    ((200 * (Math.pow(level+1, (1.5)))) - (200 *(Math.pow(level, (1.5) ))) )) ;
        } else if (level == 10){
            p = 100;
        } else {
            p = 0;
        }

        return (int) p;
    }

}
