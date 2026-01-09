import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Organizer organizer1 = new Organizer("Aitu Events Team", "AITU University", "events@aitu.kz");
        Organizer organizer2 = new Organizer("Student Council", "AITU University", "council@aitu.kz");

        Event event1 = new Event("Java Workshop", "2025-03-10", "14:00", "Room B203", organizer1, 2);
        Event event2 = new Event("Hackathon", "2025-04-20", "10:00", "Main Hall", organizer2, 5);
        Event event3 = new Event("Design Meetup", "2025-02-15", "18:30", "Main Hall", organizer1, 1);

        try {
            OrganizerDAO orgDao = new OrganizerDAO();
            EventDAO eventDao = new EventDAO();

            // CREATE
            int orgId = orgDao.create(organizer1);
            int eventId = eventDao.create(event1, orgId);

            // READ
            orgDao.readAll();
            eventDao.readAll();

            // UPDATE
            eventDao.updateLocation(eventId, "Room C101");

            // DELETE (чтобы показать удаление)
            // eventDao.delete(eventId);
            // orgDao.delete(orgId);

            System.out.println("CRUD DONE ✅");
        } catch (Exception e) {
            e.printStackTrace();
        }


        Participant p1 = new Participant("Arman", "arman@example.com", "+7 701 000 00 01");
        Participant p2 = new Participant("Dana", "dana@example.com", "+7 702 000 00 02");
        Participant p3 = new Participant("Arman 2", "arman@example.com", "+7 703 000 00 03"); // тот же email -> equals true

        // Полиморфизм: общий список Person
        ArrayList<Person> people = new ArrayList<>();
        people.add(p1);
        people.add(organizer1);

        System.out.println("=== Polymorphism demo ===");
        for (Person person : people) {
            System.out.println(person.getRole() + " -> " + person.getName() + " (" + person.getEmail() + ")");
        }

        EventManager manager = new EventManager();
        manager.addEvent(event1);
        manager.addEvent(event2);
        manager.addEvent(event3);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1) Show all events");
            System.out.println("2) Sort events by date");
            System.out.println("3) Sort events by seats left (desc)");
            System.out.println("4) Filter events with available seats");
            System.out.println("5) Search event by title");
            System.out.println("6) Register participant to event");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Wrong input.");
                continue;
            }

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.println("\n--- All events ---");
                    for (Event e : manager.getAllEvents()) System.out.println(e);
                    break;

                case 2:
                    manager.sortByDate();
                    System.out.println("Sorted by date.");
                    break;

                case 3:
                    manager.sortBySeatsLeftDesc();
                    System.out.println("Sorted by seats left (desc).");
                    break;

                case 4:
                    System.out.println("\n--- Available events ---");
                    for (Event e : manager.filterAvailableEvents()) System.out.println(e);
                    break;

                case 5:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    Event found = manager.findByTitle(title);
                    System.out.println(found == null ? "Not found" : found.toString());
                    break;

                case 6:
                    // для простоты даём выбрать event по номеру и participant из 3 заранее
                    ArrayList<Event> events = manager.getAllEvents();
                    System.out.println("\nChoose event:");
                    for (int i = 0; i < events.size(); i++) {
                        System.out.println((i + 1) + ") " + events.get(i));
                    }
                    int eventIndex;
                    try {
                        eventIndex = Integer.parseInt(sc.nextLine()) - 1;
                    } catch (Exception e) {
                        System.out.println("Wrong input.");
                        break;
                    }if (eventIndex < 0 || eventIndex >= events.size()) {
                    System.out.println("No such event.");
                    break;
                }

                    System.out.println("\nChoose participant:");
                    System.out.println("1) " + p1);
                    System.out.println("2) " + p2);
                    System.out.println("3) " + p3 + " (same email as p1 -> should be duplicate)");
                    int pChoice;
                    try {
                        pChoice = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Wrong input.");
                        break;
                    }

                    Participant chosen = (pChoice == 1) ? p1 : (pChoice == 2) ? p2 : (pChoice == 3) ? p3 : null;
                    if (chosen == null) {
                        System.out.println("No such participant.");
                        break;
                    }

                    Event selected = events.get(eventIndex);
                    selected.registerParticipant(chosen);
                    selected.showParticipants();
                    break;

                default:
                    System.out.println("Unknown option.");
            }
        }

        System.out.println("Bye!");
        sc.close();
    }
}