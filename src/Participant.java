public class Participant extends Person {
    private String phone;

    public Participant(String name, String email, String phone) {
        super(name, email);
        this.phone = phone;
    }

    @Override
    public String getRole() {
        return "Participant";
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Participant{name='" + getName() + "', email='" + getEmail() + "', phone='" + phone + "'}";
    }
}