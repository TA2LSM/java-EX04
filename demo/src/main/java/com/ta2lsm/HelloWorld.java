//--- BOLUM 1.4 (17.04.2022 / Semih SENOL) ---
package com.ta2lsm;

// aynı paket (com.ta2lsm) altında olduklarından import etmeden erişim yapılabiliyor.
//import com.ta2lsm.PrimitiveSizes; 
//import com.ta2lsm.mortgageCalculator;

//import java.util.*;
//import java.text.*;
//import java.util.Scanner;
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

    // Aşağıdaki tanımlamalara "field" denir. Class seviyesindfe tanımlamalardır ve
    // ilgili class'a ait tüm metotlar bunlara erişebilir. Sadece şuna dikkat etmek
    // gerekiyor: eğer tanımlı metot static ise aşağıda tanımlı fields ifadelerden
    // sadece static olanlara erişebilir. ((( ÖNEMLİ )))
    
    // public final static String name = "robot";       // Constant
    // final String dontChange = "India";               // Value
    // protected String river = "GANGA";                // Field
    // private String age;                              // Property

    private String firstName; 
    private String lastName; 

    /**
     * This function takes a name of a user and greet user
     * @param firstName User's first name
     * @param lastName User's last name
     */
    private static String greetUser(String firstName, String lastName) {
        return "Merhaba " + firstName + " " + lastName;
        //return new String("Merhaba " + firstName + " " + lastName);
    }

    // Bu fonksiyon static olmadığı için Hello
    private void welcomeUser() {
        System.out.println("Merhaba " + this.firstName + " " + this.lastName);
    }

    /**
     * Buraya yazılanlar ilgili metodun açıklamaları olarak gözükecektir.
     * @param args The arguments of the program.
     * @return void
     */
     public static void main(String[] args) { 
        //fizzBuzz();
        // yukarıdaki fonksiyonun terminaldeki çıktısı mortgage için girdi olarak gözüküyor
        // program hata veriyordu. keyboardInput.close(); kullanıldığı için oluyor.
        // klavye girişi okumasını tek bir yerde açıp ilgili fonksiyona parametre olarak
        // geçmek daha mantıklı oluyor.
        
        //------------------------------------------------------------------------------
        // Main metodu static olduğu için aşağıdaki 2 satır kod derlemede hata verir.
        // Main metodu, HelloWorld Class'ındaki bu kısımlara erişemez. Diğer field
        // tanımlamaları static olduğu için onlara erişebilir.
        //
        // this.firstName = "Semih";
        // this.lastName = "SENOL";
        //
        // Aşağıdaki 2 kod bloğu da çalışmayacaktır !!!
        // (1)
        // String a = "Semih";
        // String b = "SENOL";
        // String message = greetUser(a, b);
        // System.out.println(message); 
        // (2)
        // String[] fullName = {"Semih", "SENOL"};
        // String message2 = greetUser(fullName[0], fullName[1]);
        // System.out.println(message2); 

        // Yalnızca aşağıdaki şekilde erişim sağlanabilir.
        // Klasik static metotlu yöntem
        String message = greetUser("Semih", "SENOL");
        System.out.println(message); 

        // HelloWorld Class'ına static olmayan şekilde erişim
        HelloWorld accessType = new HelloWorld();
        accessType.firstName = "Semih";
        accessType.lastName = "SENOL";
        accessType.welcomeUser();
        //------------------------------------------------------------------------------

        // int size = PrimitiveSizes.sizeof(mortgage);
        // System.out.println(Integer.toString(size)); 
     
        //--- MORTGAGE HESABI ----------------------------------------------------------
        mortgageCalculator.startCalculation();
        //------------------------------------------------------------------------------
    }

    //...
}
