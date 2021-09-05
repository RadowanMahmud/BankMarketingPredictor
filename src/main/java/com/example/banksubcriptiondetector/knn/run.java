package com.example.banksubcriptiondetector.knn;

import java.io.*;
import java.util.Scanner;

public class run {

    Scanner sc = new Scanner(System.in);
    implement knn=new implement();
    int[] result=new int[knnStarter.numberOfTestRows];

    public int[]  dataTesting(int strt,implement knn) throws NumberFormatException, IOException {

        // knn.RandomizeArray();
        int k=0;

        try {
            String testline;
            int i=strt;

            while (i<strt+knnStarter.numberOfTestRows && i<knnStarter.numberOfDataRows) {

                String cls;
                double[] arr = new double[11];
                arr[0] = knn.dataRows[i].getAge();
                arr[1] = knn.dataRows[i].getJob();
                arr[2] = knn.dataRows[i].getMarital();
                arr[3] = knn.dataRows[i].getEducation();
                arr[4] = knn.dataRows[i].getDefaultV();
                arr[5] = knn.dataRows[i].getHousing();
                arr[6] = knn.dataRows[i].getLoan();
                arr[7] = knn.dataRows[i].getDay_of_week();
                arr[8] = knn.dataRows[i].getPrevious();
                // arr[9] = knn.dataRows[i].getVar_rate();
                arr[9] = knn.dataRows[i].getPrice_idx();
                arr[10] = knn.dataRows[i].getConf_idx();
//                arr[12] = knn.dataRows[i].getEuribor3m();
//                arr[13] = knn.dataRows[i].getNr_employeed();
                cls=knn.dataRows[i].getCls();
                //System.out.println("got in run");

                String chk="";
                // System.out.println(knn.test(a,b,c,d));
                chk=knn.test(i,arr);

                // System.out.println("Our declared point is"+a+","+b+","+c+","+d+"  cls="+cls+"  our="+chk);
                i++;

                if(chk.equals(cls)){
                    result[k]=10;
                }
                else{
                    result[k]=-10;
                    // System.out.println(i+" "+cls+" "+chk);
                }
                k++;

            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            return result;
        }
    }

    public String singleTesting(double[] test,implement knn) throws IOException {
        return knn.test(9999,test);
    }
}

