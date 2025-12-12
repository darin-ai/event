public class Main {
    public static void main(String[] args) {

        // Создаём организаторов
        Organizer organizer1 = new Organizer(
                "Aitu Events Team",
                "AITU University",
                "events@aitu.kz"
        );

        Organizer organizer2 = new Organizer(
                "Student Council",
                "AITU University",
                "council@aitu.kz"
        );

        // Создаём участников
        Participant p1 = new Participant("Arman", "arman@example.com", "+7 701 000 00 01");
        Participant p2 = new Participant("Dana", "dana@example.com", "+7 702 000 00 02");
        Participant p3 = new Participant("Arman", "arman@example.com", "+7 703 000 00 03"); // тот же email, что p1

        // Проверяем сравнение участников по email
        System.out.println("p1 has same email as p2? " + p1.hasSameEmail(p2));
        System.out.println("p1 has same email as p3? " + p1.hasSameEmail(p3));
        System.out.println();

        // Создаём события
        Event event1 = new Event(
                "Java Workshop",
                "2025-03-10",
                "14:00",
                "Room B203",
                organizer1,
                2
        );

        Event event2 = new Event(
                "Hackathon",
                "2025-04-20",
                "10:00",
                "Main Hall",
                organizer2,
                5
        );

        // Регистрируем участников на event1
        event1.registerParticipant(p1);
        event1.registerParticipant(p2);
        // Попытка третьего участника – уже нет мест
        event1.registerParticipant(p3);

        // Регистрируем участников на event2
        event2.registerParticipant(p1);
        event2.registerParticipant(p2);

        // Вывод информации о событиях
        System.out.println();
        System.out.println("Event 1: " + event1.getInfo());
        System.out.println("Event 2: " + event2.getInfo());

        // Сравнение объектов Event
        System.out.println();
        System.out.println("Event1 has more free seats than Event2? " +
                event1.hasMoreFreeSeatsThan(event2));
        System.out.println("Event2 has more free seats than Event1? " +
                event2.hasMoreFreeSeatsThan(event1));

        // Переносим дату и время event1
        System.out.println();
        System.out.println("Rescheduling event1...");
        event1.reschedule("2025-03-12", "16:00");
        System.out.println("Event 1 after reschedule: " + event1.getInfo());

        // Вывод информации об организаторах
        System.out.println();
        System.out.println("Organizer 1: " + organizer1.getInfo());
        System.out.println("Organizer 2: " + organizer2.getInfo());
    }
}
