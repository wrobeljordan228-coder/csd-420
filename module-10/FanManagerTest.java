import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FanManagerTest {

    public static void main(String[] args) {
        System.out.println("Starting Automated Fan Database Tests...\n");

        int testId = 1;
        String testFirst = "TestFirstName";
        String testLast = "TestLastName";
        String testTeam = "TestTeam";

        // Test 1: Verify Connection
        try (Connection conn = FanManagerApp.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("[PASS] Test 1: Database connection established successfully.");
            }
        } catch (Exception e) {
            System.err.println("[FAIL] Test 1: Could not connect to database.");
            e.printStackTrace();
            return;
        }

        // Test 2: Update existing record
        try (Connection conn = FanManagerApp.getConnection();
             PreparedStatement updateStmt = conn.prepareStatement(
                     "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?")) {

            updateStmt.setString(1, testFirst);
            updateStmt.setString(2, testLast);
            updateStmt.setString(3, testTeam);
            updateStmt.setInt(4, testId);

            int rows = updateStmt.executeUpdate();
            if (rows > 0) {
                System.out.println("[PASS] Test 2: Record with ID " + testId + " updated successfully.");
            } else {
                System.err.println("[FAIL] Test 2: No rows updated. Make sure record with ID " + testId + " exists.");
            }
        } catch (Exception e) {
            System.err.println("[FAIL] Test 2: Error updating record: " + e.getMessage());
        }

        // Test 3: Display/Select and verify matching updated data
        try (Connection conn = FanManagerApp.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(
                     "SELECT firstname, lastname, favoriteteam FROM fans WHERE ID = ?")) {

            selectStmt.setInt(1, testId);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    String actualFirst = rs.getString("firstname");
                    String actualLast = rs.getString("lastname");
                    String actualTeam = rs.getString("favoriteteam");

                    if (testFirst.equals(actualFirst) && testLast.equals(actualLast) && testTeam.equals(actualTeam)) {
                        System.out.println("[PASS] Test 3: Retrieved data matches updated values.");
                        System.out.printf("       Verified Record -> ID: %d | %s %s | Team: %s%n", 
                                testId, actualFirst, actualLast, actualTeam);
                    } else {
                        System.err.println("[FAIL] Test 3: Data mismatch detected.");
                    }
                } else {
                    System.err.println("[FAIL] Test 3: Record ID " + testId + " could not be retrieved.");
                }
            }
        } catch (Exception e) {
            System.err.println("[FAIL] Test 3: Error querying record: " + e.getMessage());
        }

        System.out.println("\nAll tests completed.");
    }
}