package com.calculator.programmer.wordSize;

/**
 *
 * @author AW Developer
 */
public enum WordSize {
    /**
     * bit 8
     */
    WORD(8),
    /**
     * bit 16
     */
    DWORD(16),
    /**
     * bit 32
     */
    QWORD(32),
    /**
     * bit 64
     */
    BYTE(64),
    /**
     * bit 64
     */
    BYTE_(128);

    public final int BITS;

    WordSize(int bits) {
        BITS = bits;
    }
    
    public int compare(long d){
        return singedRange().compare(d);
    }

    public BitRange unsingedRange() {
        return new BitRange(0, (long) (Math.pow(2, BITS - 1) - 1));
    }

    public BitRange singedRange() {
        long max = (long) (Math.pow(2, BITS-1));
        return new BitRange(-(max), max-1);
    }

}
