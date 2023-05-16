package com.spring.data.rest.springdatarust;
public class testMan {

    public static void main(String[] arg){
    /* Set<String> set=new HashSet<>();
        for(int i=0;i<1000;i++){
         String test= RandomStringUtils.randomAlphanumeric(6);
         String finalString="SKU-"+test;
         set.add(finalString);
     }
        Set<String> setTwo = new HashSet<>(set);
       setTwo.forEach(System.out::println);*/

        int[] arr={1,2,3,4,5,6,7,8};
        int target=8;

        int low=0;
        int high=arr.length-1;
        while (low<high){
            int mid=(low+high)/2;
            if(arr[mid]==target || arr[low]==target || arr[high]==target){
                System.out.print("found man here");
                break;
            }
            if(arr[mid]>target){
                high=mid-1;
            }
            if(arr[mid]<target){
                low=mid+1;
            }
        }
    }
}