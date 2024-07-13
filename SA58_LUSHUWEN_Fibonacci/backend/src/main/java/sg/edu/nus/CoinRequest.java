package sg.edu.nus;

import java.util.List;

public class CoinRequest {
    private double amount;
    private List<Double> denominations;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Double> getDenominations() {
        return denominations;
    }

    public void setDenominations(List<Double> denominations) {
        this.denominations = denominations;
    }
}
