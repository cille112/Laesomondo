package com.example.cille_000.laesomondo.util;



public class ProgressBarMath {

    public ProgressBarMath() {

    }


    public int progressBarResault(int level, int totalxp) {
        double p = 0;
        switch (level) {

            case 1:
                if ((totalxp / (150 * (Math.pow(level, (1.5))))) * 100 <= 99.99 && (totalxp / (150 * (Math.pow(level, (1.5))))) * 100 >= 0) {
                    p = (totalxp / (150 * (Math.pow(level, (1.5))))) * 100;
                }
                break;

            case 2:
                if ((totalxp / (150 * (Math.pow(level, (1.5))))) * 100 <= 99.99 && (totalxp / (150 * (Math.pow(level, (1.5))))) * 100 >= 0) {
                     p = (totalxp / (150 * (Math.pow(level, (1.5))))) * 100;
                }
                break;

            case 3:
                if ((totalxp / (150 * (Math.pow(level, (1.5))))) * 100 <= 99.99 && (totalxp / (150 * (Math.pow(level, (1.5))))) * 100 >= 0) {
                    p = (totalxp / (150 * (Math.pow(level, (1.5))))) * 100;
                }
                break;

            case 4:
                if ((totalxp / (150 * (Math.pow(level, (1.5))))) * 100 <= 99.99 && (totalxp / (150 * (Math.pow(level, (1.5))))) * 100 >= 0) {
                    p = (totalxp / (150 * (Math.pow(level, (1.5))))) * 100;
                }
                break;

            case 5:
                if ((totalxp / (150 * (Math.pow(level, (1.5))))) * 100 <= 99.99 && (totalxp / (150 * (Math.pow(level, (1.5))))) * 100 >= 0) {
                    p = (totalxp / (150 * (Math.pow(level, (1.5))))) * 100;
                }
                break;

            case 6:
                if ((totalxp / (150 * (Math.pow(level, (1.5))))) * 100 <= 99.99 && (totalxp / (150 * (Math.pow(level, (1.5))))) * 100 >= 0) {
                    p = (totalxp / (150 * (Math.pow(level, (1.5))))) * 100;
                }
                break;

            case 7:
                if ((totalxp / (150 * (Math.pow(level, (1.5))))) * 100 <= 99.99 && (totalxp / (150 * (Math.pow(level, (1.5))))) * 100 >= 0) {
                    p = (totalxp / (150 * (Math.pow(level, (1.5))))) * 100;
                }
                break;

            case 8:
                if ((totalxp / (150 * (Math.pow(level, (1.5))))) * 100 <= 99.99 && (totalxp / (150 * (Math.pow(level, (1.5))))) * 100 >= 0) {
                    p = (totalxp / (150 * (Math.pow(level, (1.5))))) * 100;
                }
                break;

            case 9:
                if ((totalxp / (150 * (Math.pow(level, (1.5))))) * 100 <= 99.99 && (totalxp / (150 * (Math.pow(level, (1.5))))) * 100 >= 0) {
                    p = (totalxp / (150 * (Math.pow(level, (1.5))))) * 100;
                }
                break;

            case 10:
                p = 100;
                break;
            default:
                p = 0;
        }
        return (int) p;
    }

}
