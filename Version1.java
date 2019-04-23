import java.util.*;
import java.lang.*;
import java.io.*;


public class Version1
{

   public static void main (String[] args) throws FileNotFoundException
   {
      Scanner input = new Scanner(new File("TestPairs100000"));
   	
      int max = 100001;
      int size = 0;
     
      double[][] point = new double[max][2]; //use 2 dimesnion array to store x&y
   	
      for (int i=0; input.hasNextDouble();i++) {
         point[i][0] = input.nextDouble();
         point[i][1] = input.nextDouble();
         size++;
      }
      
      //for just 1 point
      if(size == 1){ System.out.print("There is only one point"); return;}
      
      double result[] = new double[5];
      double startTime = System.nanoTime();
      result = bruteForce(point, size);
      double endTime = System.nanoTime();
      
      //OUTPUT
      System.out.print("Version 1, "+size+",");
      for(int i=1; i<5;i++)
         System.out.print(result[i]+",");
      System.out.print(result[0]+",");
      System.out.print((endTime-startTime)/1000000+"ms");
   }
   
   private static double[] bruteForce(double point[][], int size){
      double[] result = new double[5];
      result[0] = Integer.MAX_VALUE;
      for(int i=0; i<size; i++)
         for(int j=0; j<size; j++)
            if(i!=j&&dist(point[i],point[j])<result[0]){
               result[0] = dist(point[i],point[j]);
               result[1] = point[i][0];
               result[2] = point[i][1];
               result[3] = point[j][0];
               result[4] = point[j][1];
            }
      return result;
   }
   
   private static double dist(double point1[], double point2[]){
      return Math.sqrt(Math.pow(point1[0]-point2[0],2)+Math.pow(point1[1]-point2[1],2));
   }   
}