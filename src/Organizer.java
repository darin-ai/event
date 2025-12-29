public class Organizer extends Person {
    private String organizationName;

    public Organizer(String name, String organizationName, String contactEmail) {
        super(name, contactEmail);
        this.organizationName = organizationName;
    }

    @Override
    public String getRole() {
        return "Organizer";
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Override
    public String toString() {
        return "Organizer{name='" + getName() + "', org='" + organizationName + "', email='" + getEmail() + "'}";
    }
}