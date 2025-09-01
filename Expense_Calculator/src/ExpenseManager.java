import java.sql.*;

public class ExpenseManager {
    private Connection conn;

    public ExpenseManager() {
        try {
            // 1. Connect to DB yes
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/expense_db?useSSL=false&serverTimezone=UTC",
                    "root", "root123");

            // 2. Create table if it doesn’t exist
            String createTable = "CREATE TABLE IF NOT EXISTS expenses (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "category VARCHAR(50), " +
                    "description VARCHAR(255), " +
                    "amount DOUBLE, " +
                    "date DATE)";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createTable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Insert expense
    public void addExpense(Expense e) {
        try {
            String sql = "INSERT INTO expenses (category, description, amount, date) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, e.getCategory());
            pstmt.setString(2, e.getDescription());
            pstmt.setDouble(3, e.getAmount());
            pstmt.setString(4, e.getDate());
            pstmt.executeUpdate();
            System.out.println(" Expense Added!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Monthly Report
    public void monthlyReport() {
        try {
            String sql = "SELECT category, SUM(amount) as total FROM expenses GROUP BY category";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n Monthly Report:");
            while (rs.next()) {
                System.out.println(rs.getString("category") + " : " + rs.getDouble("total"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
