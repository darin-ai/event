public class Participant {
    private String name;
    private String email;
    private String phone;

    // Конструктор
    public Participant(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters и Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Метод для краткой информации
    public String getInfo() {
        return "Participant{name='" + name + "', email='" + email + "', phone='" + phone + "'}";
    }

    // Сравнение участников по email (считаем email уникальным)
    public boolean hasSameEmail(Participant other) {
        if (other == null) return false;
        return this.email != null && this.email.equalsIgnoreCase(other.email);
    }
}
