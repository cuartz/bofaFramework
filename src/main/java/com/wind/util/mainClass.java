///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.wind.util;
//
///**
// *
// * @author cuartz
// */
//public class mainClass {
//    
//    public static void main(String []args){
//        //System.out.println(IsCasiPalindromo("abchcfg"));
//        int[] arrrr={1,6,8,6,8};
//        System.out.println(NumMasPopular(arrrr,4));
//     }
//    int x;
//    public mainClass(int a):x(a){};
//    
//    public static int NumMasPopular(int[] myArray, int size){
//        int[] sumsArray= new int[5000];
//        for (int currentNumber:myArray){
//            sumsArray[currentNumber]++;
//        }
//        int greaterSum=0;
//        int popularNumber=0;
//        for (int currentSum:myArray){
//            if (sumsArray[currentSum]>greaterSum){
//                greaterSum=sumsArray[currentSum];
//                popularNumber=currentSum;
//            }
//        }
//        return popularNumber;
//    }
//    
//    public static boolean IsCasiPalindromo(String palabra){
//        boolean almost=true;
//        for(int i=0;i<(palabra.length())/2;i++){
//            if (palabra.charAt(i)!=palabra.charAt(palabra.length()-i-1)){
//                if (!almost){
//                    return false;
//                }
//                almost = false;
//            }
//        }
//        return true;
//    }
//    
//    public double distance(double x1, double y1, double x2, double y2, double x3, double y3)
//{ 
//    double dx   = x1 - x2;
//  double dy   = y1 - y2;
//  double dist12 = Math.sqrt( dx*dx + dy*dy );
//    dx   = x1 - x3;
//    dy   = y1 - y3;
//    double dist13 = Math.sqrt( dx*dx + dy*dy );
//    dx   = x2 - x3;
//    dy   = y2 - y3;
//    double dist23 = Math.sqrt( dx*dx + dy*dy );
//  return (dist12+dist13+dist23)/3;
//}
//}
