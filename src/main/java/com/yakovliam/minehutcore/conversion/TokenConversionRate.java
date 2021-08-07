package com.yakovliam.minehutcore.conversion;

public class TokenConversionRate {

    /**
     * Tokens
     */
    private final int tokensPer;

    /**
     * Cost
     */
    private final double cost;

    /**
     * Conversion rate
     *
     * @param tokensPer tokens
     * @param cost      cost
     */
    public TokenConversionRate(int tokensPer, double cost) {
        this.tokensPer = tokensPer;
        this.cost = cost;
    }

    /**
     * Returns tokens
     *
     * @return tokens
     */
    public int getTokensPer() {
        return tokensPer;
    }

    /**
     * Returns cost
     *
     * @return cost
     */
    public double getCost() {
        return cost;
    }
}
