package com.example.banksubcriptiondetector.knn;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class implement {

    public implement(){

    }

    DataRow[] dataRows =new DataRow[knnStarter.numberOfDataRows];
    Scanner sc = new Scanner(System.in);

    public void loadtestData(String testfilename) throws NumberFormatException, IOException {

        File testfile = new File(testfilename);

        try {
            BufferedReader testreadFile = new BufferedReader(new FileReader(testfile));
            String testline;
            int i=0,yescount=0,nocount=0;

            while ((testline = testreadFile.readLine()) != null) {
                //System.out.println(i);
                String[] split = testline.split(",");
                dataRows[i]=new DataRow(split);
                if(dataRows[i].getCls().equals("yes")){
                    yescount++;
                }else nocount++;

                i++;
            }
            System.out.println("Yes count is "+yescount);
            System.out.println("No count is "+nocount);
            testreadFile.close();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Map<String, Double> sortByValue(final Map<String, Double> wordCounts) {

        return wordCounts.entrySet()

                .stream()

                .sorted((Map.Entry.<String, Double>comparingByValue()))

                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    }

    double calculateDistance(double[] test,double[] arr)
    {
        double distance;
        double sum=0;
        for(int i=0;i<test.length;i++){
            sum=sum+((test[i]-arr[i])*(test[i]-arr[i]));
        }
        distance=Math.sqrt(sum);
        return distance;
    }

    double getKthDistance(Map <String,Double> sortedDistance,int k)
    {
        Map.Entry me = null;

        Set entrySet = sortedDistance.entrySet();
        Iterator it = entrySet.iterator();

        int i=0;
        while(i<k && it.hasNext())
        {
            me = (Map.Entry)it.next();
            i++;
        }

        return (double) me.getValue();
    }

    public void RandomizeArray(){
        Random rgen = new Random();  // Random number generator

        for (int i = 0; i< dataRows.length; i++) {
            int randomPosition = rgen.nextInt(dataRows.length);
            DataRow temp = dataRows[i];
            dataRows[i] = dataRows[randomPosition];
            dataRows[randomPosition] = temp;
        }

        //  return array;
    }

    public String test(int chk,double[] test) throws IOException
    {
        int j=0;

        HashMap<String,Double> distance=new HashMap<String,Double>();

        for(int i = 0; i< dataRows.length; i++)
        {
            if(i==chk) ;
                // System.out.println(j);
            else{
                double[] arr = new double[11];
                arr[0] = dataRows[i].getAge();
                arr[1] = dataRows[i].getJob();
                arr[2] = dataRows[i].getMarital();
                arr[3] = dataRows[i].getEducation();
                arr[4] = dataRows[i].getDefaultV();
                arr[5] = dataRows[i].getHousing();
                arr[6] = dataRows[i].getLoan();
                arr[7] = dataRows[i].getDay_of_week();
                arr[8] = dataRows[i].getPrevious();
                //arr[9] = dataRows[i].getVar_rate();
                arr[9] = dataRows[i].getPrice_idx();
                arr[10] = dataRows[i].getConf_idx();
//                arr[12] = dataRows[i].getEuribor3m();
//                arr[13] = dataRows[i].getNr_employeed();
                distance.put(dataRows[i].getCls(),calculateDistance(test, arr));
            }
            j++;
        }



        HashMap <String,Double> sortedDistance=new HashMap<String,Double>();

        sortedDistance=(HashMap<String, Double>) sortByValue(distance);

        double [] weight=new double[2];


        int [] count=new int[2];

        int n=0;

        for(Map.Entry<String, Double> m: sortedDistance.entrySet())
        {
            // int k=Integer.parseInt(m.getKey());
            String s=m.getKey();

            if(s.equals("yes"))
            {
                count[0]++;
                weight[0]+=(getKthDistance(sortedDistance, 10)-m.getValue())/(getKthDistance(sortedDistance, 10)-getKthDistance(sortedDistance, 1));
            }
            else if(s.equals("no"))
            {
                count[1]++;
                weight[1]+=(getKthDistance(sortedDistance, 10)-m.getValue())/(getKthDistance(sortedDistance, 10)-getKthDistance(sortedDistance, 1));
            }
            n++;

            if(n==10) break;
        }

        //eta normal knn line 151-161

//		int maxFlower=count[0];
//		int index=0;
//
//		for(int i=0;i<2;i++)
//		{
//			if(count[i]>maxFlower)
//			{
//				maxFlower=count[i];
//				index=i;
//			}
//		}

        //eta weighted knn line 165-175

        double maxWeight=weight[0];
        int index=0;

        for(int i=0;i<2;i++)
        {
            if(weight[i]>maxWeight)
            {
                maxWeight=weight[i];
                index=i;
            }
        }

        if(index==0)
            return "yes";
        else if(index==1)
            return "no";

        return null;

    }
}

