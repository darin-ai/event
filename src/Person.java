import java.util.Objects;

public abstract class Person {
    private String name;
    private String email;

    protected Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public final String getName() {
        return name;
    }

    public final String getEmail() {
        return email;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final void setEmail(String email) {
        this.email = email;
    }

    // Полиморфизм: каждый наследник по-своему
    public abstract String getRole();

    @Override
    public String toString() {
        return "Person{name='" + name + "', email='" + email + "', role='" + getRole() + "'}";
    }

    // Для Person логично сравнение по email (уникальный идентификатор)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        if (email == null || person.email == null) return false;
        return email.equalsIgnoreCase(person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email == null ? null : email.toLowerCase());
    }
}