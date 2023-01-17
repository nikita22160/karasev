public class Grant {
    private static long ID_COUNT;
    private long id;
    private Company company;
    private double amount;
    private int fiscalYear;

    public Grant(Company company) {
        Grant.ID_COUNT++;
        this.id = Grant.ID_COUNT;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(int fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    @Override
    public String toString() {
        return "Grant{" +
                "company=" + company +
                ", amount=" + amount +
                ", fiscalYear=" + fiscalYear +
                '}';
    }
}