public class Event {
    private String title;
    private String date;       // для простоты String, можно было LocalDate
    private String time;       // тоже String
    private String location;
    private Organizer organizer;
    private int maxParticipants;
    private int currentParticipants;

    // Конструктор
    public Event(String title, String date, String time, String location,
                 Organizer organizer, int maxParticipants) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.organizer = organizer;
        this.maxParticipants = maxParticipants;
        this.currentParticipants = 0;
    }

    // Getters и Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }

    // Методы логики

    // Добавить участника (только считает количество, без списка для простоты)
    public boolean registerParticipant(Participant participant) {
        if (isFull()) {
            System.out.println("Event is full, cannot register: " + participant.getName());
            return false;
        }
        currentParticipants++;
        System.out.println("Registered participant: " + participant.getName());
        return true;
    }

    // Проверка, заполнено ли событие
    public boolean isFull() {
        return currentParticipants >= maxParticipants;
    }

    // Перенести событие на другую дату/время
    public void reschedule(String newDate, String newTime) {
        this.date = newDate;
        this.time = newTime;
    }

    // Краткая информация о событии
    public String getInfo() {
        return "Event{title='" + title + "', date='" + date + "', time='" + time +
                "', location='" + location + "', organizer='" +
                (organizer != null ? organizer.getName() : "N/A") + "', " +
                "participants=" + currentParticipants + "/" + maxParticipants + "}";
    }

    // Сравнение двух событий по количеству свободных мест
    public int freeSeats() {
        return maxParticipants - currentParticipants;
    }

    public boolean hasMoreFreeSeatsThan(Event other) {
        if (other == null) return false;
        return this.freeSeats() > other.freeSeats();
    }
}
