package com.ta2lsm;

// direkt class içine overload olarak yazılmış "sizeof" metotları
public class PrimitiveSizes {
    public static byte sizeof(byte b) { return 1; } 
    public static byte sizeof(short s) { return 2; }
    public static byte sizeof(int i) { return 4; }
    public static byte sizeof(long l) { return 8; }
    public static byte sizeof(float f) { return 4; }
    public static byte sizeof(double d) { return 8; }
    public static byte sizeof(char c) { return 2; }
}
