/*
 * 用接口重做上一个练习
 * */

public class Main {
    public static void main(String[] args) {
        IncomeTax[] incomes = new IncomeTax[] { new Income(3000), new SalaryIncome(7500), new RoyaltyIncome(12000) };
        double total = 0;
        // TODO:
        for (IncomeTax income : incomes) {
            total += income.calculateTax();
        }
        System.out.println(total);
    }
}

interface IncomeTax {
    double calculateTax();
}

class Income implements IncomeTax {
    private double income;

    public Income(double income) {
        this.income = income;
    }

    @Override
    public double calculateTax() {
        return income * 0.1;
    }
}

class SalaryIncome implements IncomeTax {
    private double income;

    public SalaryIncome(double income) {
        this.income = income;
    }

    @Override
    public double calculateTax() {
        if (income <= 5000) {
            return 0;
        }
        return (income - 5000) * 0.2;
    }
}

class RoyaltyIncome implements IncomeTax {
    private double income;

    public RoyaltyIncome(double income) {
        this.income = income;
    }

    @Override
    public double calculateTax() {
        return income * 0.2;
    }
}