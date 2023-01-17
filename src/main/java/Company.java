public class Company {
    private static long ID_COUNT;
    private long id;
    private String name;
    private String street;
    private String businessType;
    private long numberOfJobs;

    public Company() {
        Company.ID_COUNT++;
        id = Company.ID_COUNT;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public long getNumberOfJobs() {
        return numberOfJobs;
    }

    public void setNumberOfJobs(long numberOfJobs) {
        this.numberOfJobs = numberOfJobs;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", businessType='" + businessType + '\'' +
                ", numberOfJobs=" + numberOfJobs +
                '}';
    }
}
