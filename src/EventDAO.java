import java.sql.*;

public class EventDAO {

    public int create(Event e, Integer organizerId) throws Exception {
        String sql = """
            INSERT INTO event(title, event_date, event_time, location, max_participants, organizer_id)
            VALUES (?,?,?,?,?,?) RETURNING id
        """;
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, e.getTitle());
            ps.setDate(2, Date.valueOf(e.getDate()));          // YYYY-MM-DD
            ps.setTime(3, Time.valueOf(e.getTime() + ":00"));  // HH:MM -> HH:MM:SS
            ps.setString(4, e.getLocation());
            ps.setInt(5, e.getMaxParticipants());

            if (organizerId == null) ps.setNull(6, Types.INTEGER);
            else ps.setInt(6, organizerId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }

    public void updateLocation(int id, String newLocation) throws Exception {
        String sql = "UPDATE event SET location=? WHERE id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, newLocation);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM event WHERE id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void readAll() throws Exception {
        String sql = """
            SELECT e.id, e.title, e.event_date, e.event_time, e.location, e.max_participants, o.name as organizer_name
            FROM event e
            LEFT JOIN organizer o ON e.organizer_id = o.id
            ORDER BY e.id
        """;
        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("=== EVENTS ===");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getDate("event_date") + " " +
                        rs.getTime("event_time") + " | " +
                        rs.getString("location") + " | max=" +
                        rs.getInt("max_participants") + " | organizer=" +
                        rs.getString("organizer_name"));
            }
        }
    }
}
