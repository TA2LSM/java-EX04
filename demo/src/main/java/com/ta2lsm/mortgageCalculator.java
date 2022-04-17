package com.ta2lsm;

import java.util.*;
import java.text.*;
import java.util.Scanner;

public class mortgageCalculator {
    final static byte MONTHS_IN_YEAR = 12;      // static yazılmazsa OLMUYOR !!!
    final static byte PERCENT_DIVIDER = 100;    // Bir Class içinde static olan metotlar SADECE static olan tanımlamalara erişebilirler.

    final static int MIN_PRINCIPAL = 1_000;
    final static int MAX_PRINCIPAL = 1_000_000;
    final static float MIN_ANNUAL_INTEREST = 0F;
    final static float MAX_ANNUAL_INTEREST = 30F;
    final static byte MIN_YEAR = 1;
    final static byte MAX_YEAR = 30;    

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
     * 
     */
    public static void startCalculation() {
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

    /**
     * Mortgage Calcualtor: Yıl olarak geri ödeme süresine göre aylık bedel hesaplanacaktır
     * @param principal Yıllık gelir
     * @param annualInterest Yıllık kredi faizi
     * @param years Geri ödeme süresi (yıl)
     * @return mortgage
     */
    public static double calculateMortgage(
        int principal, float annualInterest, byte years) {

        float monthlyInterest = annualInterest / PERCENT_DIVIDER / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        double mortgage = principal
                    * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                    / (Math.pow((1 + monthlyInterest), numberOfPayments) - 1);

        return mortgage;
    }

    /**
     * 
     * @param principal
     * @param annualInterest
     * @param years
     * @param numberOfPaymentsMade
     * @return
     */
    public static float calculateBalance(
        int principal, float annualInterest, 
        byte years, short numberOfPaymentsMade) {

        /**
         * Kalan miktarın hesabı için formül var:
         * B = principal * [ (1+monthlyInterest)^numberOfPayments - ((1+monthlyInterest)^numberOfPaymentsMade) ]
         *               / [ (1+monthlyInterest)^numberOfPayments - 1 ]
         */            

        float calcVal = 0;
        float monthlyInterest = annualInterest / PERCENT_DIVIDER / MONTHS_IN_YEAR;
        int numberOfPayments = years * MONTHS_IN_YEAR;

        calcVal = principal 
        * ( (float)Math.pow(1 + monthlyInterest, numberOfPayments) 
          - (float)Math.pow(1 + monthlyInterest, numberOfPaymentsMade) )
        / ( (float)Math.pow(1 + monthlyInterest, numberOfPayments) - 1.0F) ;


        return calcVal;
    }

    // /**
    //  * Mortgage Calcualtor 2
    //  * @param name
    //  */
    // public static double calculateMortgage2() {
    //     int principal = 0;
    //     float rate = 0;
    //     int months = 0;
    //     byte errorLevel = 0;

    //     Scanner keyboardInput = new Scanner(System.in);
    //     while( true )
    //     {
    //         if( errorLevel == 0 )
    //         {
    //             System.out.print("Principal ($1K - $1M): ");     // yıllık kazanç (100000)
    //             principal = keyboardInput.nextInt();
                
    //             if( principal < MIN_PRINCIPAL || principal > MAX_PRINCIPAL )
    //             {
    //                 System.out.println("Enter a number between 1.000 and 1.000.000");
    //                 continue;
    //             }
    //             else
    //                 errorLevel = 1;
    //         }
    //         else if( errorLevel == 1 )
    //         {
    //             System.out.print("Annual Interest Rate: ");     // kredi faizi (3.92)
    //             rate = keyboardInput.nextFloat();
    //             // Kursta bu kısmı aşağıdaki gibi yazmış:
    //             // float rate = keyboardInput.nextFloat() / PERCENT_DIVIDER / MONTHS_IN_YEAR;

    //             if( rate > MIN_ANNUAL_RATE && rate <= MAX_ANNUAL_RATE )
    //             {
    //                 rate /= ((float)MONTHS_IN_YEAR * (float)PERCENT_DIVIDER);  // monthly interest
    //                 errorLevel = 2;
    //             }
    //             else
    //             {
    //                 System.out.println("Enter a value greater than 0 and less than or equal to 30");
    //                 continue;
    //             }
    //         }
    //         else if( errorLevel == 2 )
    //         {
    //             System.out.print("Period (Years): ");           // kredi geri ödeme süresi (30 yıl ama ay olarak kullanılacak)
    //             int year = keyboardInput.nextInt();

    //             if( year >= MIN_YEAR && year <= MAX_YEAR )
    //             {
    //                 months = year * MONTHS_IN_YEAR;
    //                 errorLevel = 3;
    //             }
    //             else
    //             {
    //                 System.out.println("Enter a value greater or equal to 1 and less than or equal to 30");
    //                 continue;
    //             }
    //         }
    //         else if( errorLevel > 2 )
    //             break;
    //     }
    //     keyboardInput.close();

    //     /** MORTGAGE HESABI
    //      * mortgage = principal * ( (rate x (1 + rate)^n) /  (1 + rate)^n - 1)
    //      * months: number of payments (Period * 12)
    //      * rate: Annual Interest Rate / (12 * 100)
    //      */

    //     // kursta aşağıdaki gibi yazıldı:
    //     // double mortgage = principal 
    //     //             * ( rate * (Math.pow((1 + rate), months)) )
    //     //             / ( Math.pow((1 + rate), months) - 1.0F );
              
    //     // aylık ödemelerin hesaplanması (mortgage)
    //     double mortgage = rate * (Math.pow((1 + rate), months));
    //     mortgage /= ( Math.pow((1 + rate), months) - 1.0F );
    //     mortgage *= principal;

    //     return mortgage;
    // }
}
