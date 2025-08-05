/*
* 用抽象类重做上一个练习
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

abstract class IncomeTax {
    protected double income;

    public IncomeTax(double income) {
        this.income = income;
    }

    public abstract double calculateTax();
}

class Income extends IncomeTax {
    public Income(double income) {
        super(income);
    }

    @Override
    public double calculateTax() {
        return income * 0.1;
    }
}

class SalaryIncome extends IncomeTax {
    public SalaryIncome(double income) {
        super(income);
    }

    @Override
    public double calculateTax() {
        if (income <= 5000) {
            return 0;
        }
        return (income - 5000) * 0.2;
    }
}

class RoyaltyIncome extends IncomeTax {
    public RoyaltyIncome(double income) {
        super(income);
    }

    @Override
    public double calculateTax() {
        return income * 0.2;
    }
}