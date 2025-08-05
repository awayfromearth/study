/*
* TODO: 给一个有工资收入和稿费收入的小伙伴算税:
*  工资税率：5000以下部分不交税，5000以上部分交20%税
*  稿费税率：20%
*  其他税率：10%
* */

public class Main {

    public static void main(String[] args) {
        Income[] incomes = new Income[] { new Income(3000), new SalaryIncome(7500), new RoyaltyIncome(12000) };
        double total = 0;
        // TODO:
        for (Income income : incomes) {
            total += income.getTax();
        }
        System.out.println(total);
    }

}

class Income {
    protected double income;

    public Income(double income) {
        this.income = income;
    }

    public double getTax() {
        return income * 0.1;
    }
}

class SalaryIncome extends Income {
    public SalaryIncome(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        if (income <= 5000) {
            return 0;
        }

        return (income - 5000) * 0.2;
    }
}

class RoyaltyIncome extends Income {
    public RoyaltyIncome(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        return income * 0.2;
    }
}