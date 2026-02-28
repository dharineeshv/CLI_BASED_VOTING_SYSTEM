package Voters;
import java.sql.*;
import java.util.*;
public class Voters {

    Admin admin = new Admin();
    public void voters(int voter_id, Connection conn) throws Exception {

        Scanner input = new Scanner(System.in);
        Statement st = conn.createStatement();

        String sql = "SELECT * FROM VOTERS WHERE VOTER_ID = " + voter_id;
        ResultSet rs = st.executeQuery(sql);

        if (!rs.next()) {
            System.out.println("Please Register First!!!");
            admin.register_voters(conn);
            return;
        }

        if (rs.getBoolean("has_voted")) {
            System.out.println("You Are Already Voted!!!");
            return;
        }

        System.out.println("Your Name is " + rs.getString("NAME"));
        System.out.println("Your Voter ID is " + rs.getInt("VOTER_ID"));

        String sql3 = "SELECT * FROM Candidates";
        ResultSet rs3 = st.executeQuery(sql3);

        boolean parties_found = false;

        while (rs3.next()) {
            System.out.println(
                    rs3.getInt("Candidate_id") + " : " +
                            rs3.getString("candidate_name") + " : " +
                            rs3.getString("party_name")
            );
            parties_found = true;
        }

        if (parties_found) {
            System.out.print("Enter Candidate ID to Vote: ");
            int vote = input.nextInt();

            String sql4 = "UPDATE Candidates SET votes = votes + 1 WHERE Candidate_id = " + vote;
            String sql5 = "UPDATE VOTERS SET has_voted = TRUE WHERE VOTER_ID = " + voter_id;

            st.executeUpdate(sql4);
            st.executeUpdate(sql5);

            System.out.println("Voted Successfully!!!");
        }
    }


    public void results(int voter_id,Connection conn) throws Exception{
        String sql = "SELECT published from ADMIN WHERE ID ="+1;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if(rs.next() && rs.getBoolean("published"))
        {
            String sql2 = "SELECT * FROM Candidates";
            ResultSet rs2 = st.executeQuery(sql2);
            while(rs2.next())
            {
                System.out.println(rs2.getInt(1)+":"+rs2.getString(2)+":"+rs2.getString(3)+":"+rs2.getInt(4));
            }
        }
        else {
            System.out.println("Result Are Not Yet Published");
        }
    }

}

