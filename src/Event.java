import java.util.ArrayList;
import java.util.Objects;

public class Event {
    private String title;
    private String date;   // формат "YYYY-MM-DD"
    private String time;   // формат "HH:MM"
    private String location;
    private Organizer organizer;

    private int maxParticipants;
    private final ArrayList<Participant> participants;

    public Event(String title, String date, String time, String location,
                 Organizer organizer, int maxParticipants) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.organizer = organizer;
        this.maxParticipants = maxParticipants;
        this.participants = new ArrayList<>();
    }

    private Integer id;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }


    // Getters / Setters (абстракция + инкапсуляция)
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Organizer getOrganizer() { return organizer; }
    public void setOrganizer(Organizer organizer) { this.organizer = organizer; }

    public int getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; }

    public int getCurrentParticipants() { return participants.size(); }

    public int seatsLeft() {
        return maxParticipants - participants.size();
    }

    public boolean isFull() {
        return participants.size() >= maxParticipants;
    }

    public void reschedule(String newDate, String newTime) {
        this.date = newDate;
        this.time = newTime;
    }

    // Регистрация с защитой от дублей по email (equals у Participant по email из Person)
    public boolean registerParticipant(Participant participant) {
        if (participant == null) return false;

        if (isFull()) {
            System.out.println("Event is full. Cannot register: " + participant.getEmail());
            return false;
        }

        if (participants.contains(participant)) {
            System.out.println("Already registered: " + participant.getEmail());
            return false;
        }

        participants.add(participant);
        System.out.println("Registered: " + participant.getName() + " -> " + title);
        return true;
    }

    public void showParticipants() {
        System.out.println("Participants of '" + title + "':");
        if (participants.isEmpty()) {
            System.out.println("  none yet");
            return;
        }
        for (Participant p : participants) {
            System.out.println("  - " + p);
        }
    }

    @Override
    public String toString() {
        return "Event{title='" + title + "', date='" + date + "', time='" + time +
                "', location='" + location + "', organizer='" +
                (organizer != null ? organizer.getName() : "N/A") + "', seatsLeft=" + seatsLeft() + "}";
    }

    // equals/hashCode для Event: уникальность по title+date+time+location
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Objects.equals(title, event.title)
                && Objects.equals(date, event.date)
                && Objects.equals(time, event.time)
                && Objects.equals(location, event.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date, time, location);
    }
}