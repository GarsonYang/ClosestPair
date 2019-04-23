import java.util.*;
import java.lang.*;
import java.io.*;


public class Version2
{

   public static void main (String[] args) throws FileNotFoundException
   {
      Scanner input = new Scanner(new File("TestPairs10"));
   	
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
      
      mergeSort(point, 0, size-1, 0); // sort by x
      
      double result[] = new double[5];
      double startTime = System.nanoTime();
      result = closestPair(point, 0, size-1);
      double endTime = System.nanoTime();
      
      //OUTPUT
      System.out.print("Version 1, "+size+",");
      for(int i=1; i<5;i++)
         System.out.print(result[i]+",");
      System.out.print(result[0]+",");
      System.out.print((endTime-startTime)/1000000+"ms");
   }
   
   
   private static double[] closestPair(double point[][], int left, int right){
      double result[] = new double[5];//result[0] = d, result[1-4]=point1xy,point2xy
      
      //if there is only one point
      if(left >= right) {
         result[0] = Integer.MAX_VALUE;
         result[1]=point[left][0];
         result[2]=point[left][1];
         result[3]=Integer.MAX_VALUE;
         result[4]=Integer.MAX_VALUE;
         return result;
      }
      
      double result1[] = new double[5];
      double result2[] = new double[5];
      int mid = (left+right)/2;
      int length = right-left+1;
      
      //get the smallest d
      result1 = closestPair(point, left, mid);
      result2 = closestPair(point, mid+1, right);
      if(result1[0]<=result2[0])
         for(int i=0;i<5;i++)
            result[i]=result1[i];
      else 
         for(int i=0;i<5;i++)
            result[i]=result2[i];
  

      //get the d inside the gap
      int temp_size=0;
      double temp[][] = new double[length][2]; //create a list to store remained points
      for(int i=0; i<length;i++){
         if(point[i][0]>=point[mid][0]-result[0]||point[i][0]<=point[mid][0]+result[0]){
            temp[temp_size][0]=point[i][0];
            temp[temp_size][1]=point[i][1];
            temp_size++;
         //System.out.println(temp[i][0]+"<loop>"+temp[i][1]);
         }
      }
      mergeSort(temp, 0, temp_size-1, 1); 
      
      for(int i=0;i<temp_size;i++){
         int k=1;
         while(i+k<temp_size&&temp[i+k][1]<temp[i][1]+result[0]){
            if (dist(temp[i], temp[i+k])<result[0]){
               result[0]=dist(temp[i], temp[i+k]);
               result[1]=temp[i][0];
               result[2]=temp[i][1];
               result[3]=temp[i+k][0];
               result[4]=temp[i+k][1];
            }
            k++;
         }
      }
      
      return result;
   }
   
    
   private static double dist(double point1[], double point2[]){
      return Math.sqrt(Math.pow(point1[0]-point2[0],2)+Math.pow(point1[1]-point2[1],2));
   }
   
   //Sort by X with parameter = 0, by Y with parameter = 1
   private static void mergeSort(double array[][], int left, int right, int parameter){
      if(left >= right) 
         return;
      int mid = (left+right)/2;
      mergeSort(array, left, mid, parameter);
      mergeSort(array, mid+1, right, parameter);
      merge(array,left,mid,right,parameter);
   }
 
   private static void merge (double array[][], int left, int mid, int right, int parameter){
      int length = right - left + 1;
      double temp[][] = new double[length][2];
      int a=left, b=mid+1;
      for(int i=0; i<length;i++){
         if(a<=mid&&(b>right||array[a][parameter]<array[b][parameter])){//fill with one array if the other array ends
            temp[i][0]=array[a][0];
            temp[i][1]=array[a][1];
            a++;
         }
         else{
            temp[i][0]=array[b][0];
            temp[i][1]=array[b][1];
            b++;
         }
      }
      for(int i=0; i<length;i++){
         array[left][0]=temp[i][0];
         array[left][1]=temp[i][1];
         left++;
         //System.out.println(temp[i][0]+"<loop>"+temp[i][1]);
      }
   }
}