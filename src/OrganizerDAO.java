import java.sql.*;

public class OrganizerDAO {

    public int create(Organizer o) throws Exception {
        String sql = "INSERT INTO organizer(name, organization_name, contact_email) VALUES (?,?,?) RETURNING id";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, o.getName());
            ps.setString(2, o.getOrganizationName());
            ps.setString(3, o.getEmail());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }

    public void updateEmail(int id, String newEmail) throws Exception {
        String sql = "UPDATE organizer SET contact_email=? WHERE id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, newEmail);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM organizer WHERE id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void readAll() throws Exception {
        String sql = "SELECT id, name, organization_name, contact_email FROM organizer ORDER BY id";
        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("=== ORGANIZERS ===");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("organization_name") + " | " +
                        rs.getString("contact_email"));
            }
        }
    }
}
