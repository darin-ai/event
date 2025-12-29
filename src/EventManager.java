import java.util.ArrayList;
import java.util.Comparator;

public class EventManager {
    private final ArrayList<Event> events = new ArrayList<>();

    public void addEvent(Event e) {
        if (e != null) events.add(e);
    }

    public ArrayList<Event> getAllEvents() {
        return events;
    }

    // Searching: поиск по названию
    public Event findByTitle(String title) {
        if (title == null) return null;
        for (Event e : events) {
            if (e.getTitle().equalsIgnoreCase(title)) return e;
        }
        return null;
    }

    // Filtering: события, где есть места
    public ArrayList<Event> filterAvailableEvents() {
        ArrayList<Event> res = new ArrayList<>();
        for (Event e : events) {
            if (e.seatsLeft() > 0) res.add(e);
        }
        return res;
    }

    // Filtering: события по месту
    public ArrayList<Event> filterByLocation(String location) {
        ArrayList<Event> res = new ArrayList<>();
        if (location == null) return res;
        for (Event e : events) {
            if (e.getLocation().equalsIgnoreCase(location)) res.add(e);
        }
        return res;
    }

    // Sorting: по дате
    public void sortByDate() {
        events.sort(Comparator.comparing(Event::getDate));
    }

    // Sorting: по свободным местам (по убыванию)
    public void sortBySeatsLeftDesc() {
        events.sort((a, b) -> Integer.compare(b.seatsLeft(), a.seatsLeft()));
    }
}