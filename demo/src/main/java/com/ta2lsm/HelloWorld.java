//--- BOLUM 1.3 (17.04.2022 / Semih SENOL) ---
package com.ta2lsm;

// aynı paket (com.ta2lsm) altında olduklarından import etmeden erişim yapılabiliyor.
//import com.ta2lsm.PrimitiveSizes; 
//import com.ta2lsm.mortgageCalculator;

import java.util.*;
import java.text.*;
import java.util.Scanner;
//import java.awt.*;

/**
 * JAVA uygulamasına ait ana class
 */
public final class HelloWorld {
    /**
     * Uygulamaya özgü dışarıdan erişilemeyecek kodlar
     */
    private HelloWorld() {
        //System.out.printf("Uygulama baslatildi...%n%n");
    }

    // /**
    //  * 
    //  * @param name
    //  */
    // public static void fizzBuzz() {
    //     Scanner keyInput = new Scanner(System.in);
    //     System.out.printf("5'e bolunen sayilarda \"Fizz\", 3'e bolunen sayilarda \"Buzz\", %nikisine de bolunen sayilarda \"FizzBuzz\" yazdirilacaktir...%n");
    //     System.out.print("Tam sayi giriniz: ");
    //     int number = keyInput.nextInt();
    //     keyInput.close();        
        
    //     String result;

    //     // flat şekilde yazılmış. Daha rahat okunur ve anlaşılır. 
    //     if( (number % 5) == 0 && (number % 3) == 0 )
    //         result = "FizzBuzz";
    //     else if( (number % 5) == 0 )
    //         result = "Fizz";
    //     else if( (number % 3) == 0 )
    //         result = "Buzz";
    //     else 
    //         result = "Hicbiri degil! (" + number + ")";

    //     // // nested şekilde yazılmış. Tekrar eden öbekler yok ama iç içe geçmeler var.
    //     // if( (number % 5) == 0 )
    //     // {
    //     //     if( (number % 3) == 0 )
    //     //         result = "FizzBuzz";
    //     //     else
    //     //         result = "Fizz";
    //     // }
    //     // else if( (number % 3) == 0 )
    //     //     result = "Buzz";
    //     // else 
    //     //     result = "None of them! (" + number + ")";

    //     System.out.printf("Sonuc: %s%n", result);
    // }

    // /**
    //  * This function takes a name of a user and greet user
    //  * @param name
    //  */
    // public static String greetUser(String firstName, String lastName) {
    //     return "Merhaba " + firstName + " " + lastName;
    //     //return new String("Merhaba " + firstName + " " + lastName);
    // }

    /**
     * Reads numbers from keyboard input
     * @param keyboardInput Scanner class object for System.in
     * @param prompt Display message for user input
     * @param minVal Minimum valid input
     * @param maxVal Maximum valid input
     * @return a double value. Cast for suitable variable type
     */
    private static double readNumber(Scanner keyboardInput, String prompt, double minVal, double maxVal) {  
        double readedVal;

        while( true ) {
            System.out.print(prompt);
            readedVal = keyboardInput.nextDouble();

            if(readedVal >= minVal && readedVal <= maxVal)
                break;

            System.out.println("Enter a value between: " + minVal + " - " + maxVal);
        }

        return readedVal;
    }

    /**
     * Buraya yazılanlar ilgili metodun açıklamaları olarak gözükecektir.
     * @param args The arguments of the program.
     * @return void
     */
     public static void main(String[] args) { 
        final int MIN_PRINCIPAL = 1_000;
        final int MAX_PRINCIPAL = 1_000_000;
        final float MIN_ANNUAL_INTEREST = 1F;
        final float MAX_ANNUAL_INTEREST = 30F;
        final byte MIN_YEAR = 1;
        final byte MAX_YEAR = 30;

        final byte MONTHS_IN_YEAR = 12;

        //fizzBuzz();
        // yukarıdaki fonksiyonun terminaldeki çıktısı mortgage için girdi olarak gözüküyor
        // program hata veriyordu. keyboardInput.close(); kullanıldığı için oluyor.
        // klavye girişi okumasını tek bir yerde açıp ilgili fonksiyona parametre olarak
        // geçmek daha mantıklı oluyor.
        
        // String message = greetUser("Semih", "SENOL");
        // System.out.println(message); 

        // int size = PrimitiveSizes.sizeof(mortgage);
        // System.out.println(Integer.toString(size)); 
     
        Scanner keyboardInput = new Scanner(System.in);
        int principal = (int)readNumber(keyboardInput, "Principal ($1K - $1M): ", MIN_PRINCIPAL, MAX_PRINCIPAL);
        float annualInterest = (float)readNumber(keyboardInput, "Annual Interest Rate: ", MIN_ANNUAL_INTEREST, MAX_ANNUAL_INTEREST);
        byte years = (byte)readNumber(keyboardInput, "Period (Years): ", MIN_YEAR, MAX_YEAR);
        keyboardInput.close();        // keyboard girişini readNumber() kısmında kapatırsak sıkıntı oluyor.

        double mortgage = mortgageCalculator.calculateMortgage(principal, annualInterest, years);

        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.printf("%nMORTGAGE%n--------%nMonthly Payments: %s%n", currency.format(mortgage));  
        System.out.printf("Total Amount of Payment: %s%n%n", currency.format(mortgage * years * (float)MONTHS_IN_YEAR));     
        System.out.printf("PAYMENT SCHEDULE%n----------------%n");  

        //float[] payments = new float[years * MONTHS_IN_YEAR];  // float dizi tanımlama

        float calcVal;
        float prevCalcVal = 0;
        int numberOfPayments = years * MONTHS_IN_YEAR;

        for(short i = 1; i <= numberOfPayments; i++)
        {        
            calcVal = (float)mortgageCalculator.calculateBalance(principal, annualInterest, years, i);
           
            System.out.printf("PAYMENT: %03d/%03d - AMOUNT: %s (BALANCE: %s)%n", i, numberOfPayments, currency.format(principal - calcVal - prevCalcVal), currency.format(calcVal)); 
            prevCalcVal = principal - calcVal; 
        }
        System.out.printf("%nSUCCESSFULLY PAID ALL YOUR DEBTS. CONGRATULATIONS...%n%n");

    }

    //...
}
