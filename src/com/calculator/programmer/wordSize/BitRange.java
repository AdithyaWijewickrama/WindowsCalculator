package com.calculator.programmer.wordSize;

/**
 *
 * @author AW Developer
 */
public class BitRange {

    public long min;
    public long max;
    private int rangeType = FROMTO;

    /**
     * [A,B]
     */
    public static final int FROMTO = 1;

    /**
     * (A,B)
     */
    public static final int BETWEENAND = 2;

    /**
     * [A,B)
     */
    public static final int AND_BETWEENAND = 3;

    /**
     * (A,B]
     */
    public static final int BETWEENAND_AND = 4;

    public BitRange(long min, long max) {
        this.min = min;
        this.max = max;
    }

    public BitRange(long min, long max, int rangeType) {
        this.min = min;
        this.max = max;
        this.rangeType = rangeType;
    }

    public void setBitRange(long min, long max) {
        this.min = min;
        this.max = max;
    }

    public int compare(long c) {
        switch (rangeType) {
            case FROMTO:
                if (c <= max && c >= min) {
                    return 0;
                }
                if (c > max) {
                    return 1;
                }
                if (c < min) {
                    return -1;
                }
                break;
            case BETWEENAND:
                if (c < max && c > min) {
                    return 0;
                }
                if (c >= max) {
                    return 1;
                }
                if (c <= min) {
                    return -1;
                }

                break;
            case AND_BETWEENAND:
                if (c < max && c >= min) {
                    return 0;
                }
                if (c > max) {
                    return 1;
                }
                if (c <= min) {
                    return -1;
                }

                break;
            case BETWEENAND_AND:
                if (c <= max && c > min) {
                    return 0;
                }
                if (c >= max) {
                    return 1;
                }
                if (c < min) {
                    return -1;
                }

                break;
            default:
                throw new AssertionError();
        }
        return 2;
    }

    @Override
    public String toString() {
        switch (rangeType) {
            case FROMTO:
                return String.format("[ %d, %d ]", min, max);
            case BETWEENAND:
                return String.format("( %d, %d )", min, max);
            case AND_BETWEENAND:
                return String.format("[ %d, %d )", min, max);
            case BETWEENAND_AND:
                return String.format("( %d, %d ]", min, max);
            default:
                throw new AssertionError();
        }
    }

}
