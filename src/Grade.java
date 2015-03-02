
public enum Grade {
    G1(1, true, true), G2(2, true, true), G3(3, true, true), G4(4, true, true), G5(5, false, true),
    GET(1, true, false), GB(1, true, false), GAB(1, true, false);

    // + --> nicht teilgenommen NT(5, false, false)
    
    private final boolean isPositive;
    private final boolean isNumber;
    private int value;

    private Grade(int value, boolean isPositive, boolean isNumber) {
        this.value = value;
        this.isPositive = isPositive;
        this.isNumber = isNumber;
    }
    
    public int getValue() {
        return value;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public boolean isPositive() {
        return isPositive;
    }
}
