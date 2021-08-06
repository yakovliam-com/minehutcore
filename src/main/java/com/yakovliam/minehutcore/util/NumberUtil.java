package com.yakovliam.minehutcore.util;

import java.text.DecimalFormat;

public class NumberUtil {

    /**
     * Decimal formatter
     */
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    /**
     * Formats to two decimal places
     *
     * @param input input
     * @return output
     */
    public static String formatToTwoDecimalPlaces(Number input) {
        return decimalFormat.format(input);
    }
}
