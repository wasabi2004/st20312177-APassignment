package calculator;

public class BillCalculator {
    public static double calculateTotal(double treatmentCost, double consultationFee) {
        return treatmentCost + consultationFee;
    }

    public static double applyDiscount(double amount, double discountPercentage) {
        return amount - (amount * discountPercentage / 100);
    }
}