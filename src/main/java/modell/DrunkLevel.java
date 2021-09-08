package modell;

import java.util.Random;

public enum DrunkLevel {

    SOBER (1),
    TIPSY (1.1),
    BRAVE (1.2),
    DRUNK (0.5),
    WASTED (0.1);

    private double coefficient;

    DrunkLevel(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    private static final DrunkLevel[] VALUES = values();    //source:
                                                            // https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum/30641206
    private static final int LENGTH = VALUES.length;
    private static final Random RANDOM = new Random();

    public static DrunkLevel randomDrunkLevel()  {
        return VALUES[RANDOM.nextInt(LENGTH)];
    }

    public static DrunkLevel getDrunker(DrunkLevel howDrunkBefore){
        for (int i = 0; i < LENGTH; i++) {
            if(!howDrunkBefore.equals(DrunkLevel.WASTED)) {
                if (howDrunkBefore.equals(VALUES[i])) {
                    return VALUES[i + 1];
                }
            }
        }
        System.out.println();
        return WASTED;
    }
}
