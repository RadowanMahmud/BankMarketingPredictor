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
        double precision=0,recall=0,f1_score=0;
        for(int startIndex=0;startIndex<numberOfDataRows;startIndex=startIndex+numberOfTestRows){
            arr=k_nn.dataTesting(startIndex,loadedData);
            sum=sum+accur(arr);
            precision=precision+precision(arr);
            recall= recall+recall(arr);
            // System.out.println(accur(arr)*100+"%");
        }

        System.out.println("Accuarcy is = "+(sum/10)*100+"%");
        System.out.println("Precision is = "+(precision/10)*100+"%");
        double p = (precision/10)*100;
        double r = (recall/10)*100;
        System.out.println("Recall is = "+(recall/10)*100+"%");
        System.out.println("F1 Score is = "+(2*p*r)/(p+r)+"%");
    }
    public static double accur(int[] arr){
        double tp=0,tn=0,fp=0,fn=0;
        // System.out.println("arr lehgth is "+ arr.length);
        for(int i=0;i<arr.length;i++){
            if(arr[i]==5){
                tp++;
            }else if(arr[i]==10){
                tn++;
            }else if(arr[i]==-5){
                fp++;
            }else if(arr[i]==-10){
                fn++;
            }
        }
        return (tp+tn)/(tp+tn+fp+fn);
    }
    public static double precision(int[] arr){
        double tp=0,tn=0,fp=0,fn=0;
        // System.out.println("arr lehgth is "+ arr.length);
        for(int i=0;i<arr.length;i++){
            if(arr[i]==5){
                tp++;
            }else if(arr[i]==10){
                tn++;
            }else if(arr[i]==-5){
                fp++;
            }else if(arr[i]==-10){
                fn++;
            }
        }
        return (tp)/(tp+fp);
    }
    public static double recall(int[] arr){
        double tp=0,tn=0,fp=0,fn=0;
        // System.out.println("arr lehgth is "+ arr.length);
        for(int i=0;i<arr.length;i++){
            if(arr[i]==5){
                tp++;
            }else if(arr[i]==10){
                tn++;
            }else if(arr[i]==-5){
                fp++;
            }else if(arr[i]==-10){
                fn++;
            }
        }
        return (tp)/(tp+fn);
    }

    public String test(double[] test) throws IOException {
        return k_nn.singleTesting(test,loadedData);
    }
}
