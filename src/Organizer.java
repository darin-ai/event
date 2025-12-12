public class Organizer {
    private String name;
    private String organizationName;
    private String contactEmail;

    // Конструктор
    public Organizer(String name, String organizationName, String contactEmail) {
        this.name = name;
        this.organizationName = organizationName;
        this.contactEmail = contactEmail;
    }

    public String getInfo() {
        return name + " | " + organization + " | " + contactEmail;
    },

    // Getters и Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    // Метод для краткой информации
    public String getInfo() {
        return "Organizer{name='" + name + "', org='" + organizationName
                + "', email='" + contactEmail + "'}";
    }
}

