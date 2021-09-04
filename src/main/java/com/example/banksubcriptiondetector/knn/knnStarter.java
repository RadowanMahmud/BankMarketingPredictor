package com.example.banksubcriptiondetector.knn;

import java.io.IOException;

public class knnStarter {
    public static int numberOfDataRows=1000;
    public static int numberOfTestRows=100;

    public void startMethod() throws IOException {
        String fileName= "withconf.txt";
        implement loadedData=new implement();
        loadedData.loadtestData(fileName);

        //Now running crossValidation

        run k_nn=new run();

        int[] arr=new int[numberOfTestRows];
        double sum=0;
        for(int startIndex=0;startIndex<numberOfDataRows;startIndex=startIndex+numberOfTestRows){
            arr=k_nn.dataTesting(startIndex,loadedData);
            sum=sum+accur(arr);
            System.out.println(accur(arr)*100+"%");
        }

        System.out.println("Avg accur is "+(sum/10)*100+"%");
    }
    public static double accur(int[] arr){
        double count=0;
        // System.out.println("arr lehgth is "+ arr.length);
        for(int i=0;i<arr.length;i++){
            if(arr[i]==10){
                count++;
            }
        }
        //System.out.println("count is "+ count);
        return count/numberOfTestRows;
    }
}
