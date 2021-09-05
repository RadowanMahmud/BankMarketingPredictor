package com.example.banksubcriptiondetector.knn;

import java.io.IOException;

public class knnStarter {
    public static int numberOfDataRows=1500;
    public static int numberOfTestRows=150;
    run k_nn=new run();
    implement loadedData=new implement();

    public void startMethod() throws IOException {
        String fileName= "keep.txt";
        loadedData.loadtestData(fileName);

        //Now running crossValidation
        int[] arr=new int[numberOfTestRows];
        double sum=0;
        for(int startIndex=0;startIndex<numberOfDataRows;startIndex=startIndex+numberOfTestRows){
            arr=k_nn.dataTesting(startIndex,loadedData);
            sum=sum+accur(arr);
            // System.out.println(accur(arr)*100+"%");
        }

        System.out.println((sum/10)*100+"%");
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

    public String test(double[] test) throws IOException {
        return k_nn.singleTesting(test,loadedData);
    }
}
