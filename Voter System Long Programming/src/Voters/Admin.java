package Voters;
import java.util.*;
import java.sql.*;
public class Admin {
   private String name;
   private int id;
   private final int admin_pwd = 1590;

   public void new_candidates(int id,String name,String party,Connection conn) throws Exception
   {
       int default_votes = 0;
       String sql = "SELECT candidate_id from CANDIDATES WHERE candidate_id = "+id;
       Statement st = conn.createStatement();
       ResultSet rs = st.executeQuery(sql);
       if(rs.next())
       {
           System.out.println("Not Approved By Election Council Of India");
       }
       else {
           String sql2 = "INSERT INTO CANDIDATES VALUES(?,?,?,?)";
           PreparedStatement ps = conn.prepareStatement(sql2);
           ps.setString(2,name);
           ps.setInt(1,id);
           ps.setString(3,party);
           ps.setInt(4,default_votes);
           int rows = ps.executeUpdate();
           System.out.println("Approved By Election Council Of India");
       }

   }

   public void register_voters(Connection conn) throws Exception {
       Scanner input = new Scanner(System.in);
       System.out.print("Enter Your Name: ");
       String name = input.nextLine();
       System.out.print("Enter Your Email: ");
       String email = input.nextLine();
       System.out.print("Enter The Password: ");
       int pwd = input.nextInt();
       System.out.print("Enter your Voter_Id: ");
       int voter_id = input.nextInt();
       String sql = "INSERT INTO VOTERS VALUES(?,?,?,?,?)";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1,voter_id);
      ps.setString(2,name);
      ps.setString(3,email);
      ps.setInt(4,pwd);
      ps.setBoolean(5,false);
       int rows = ps.executeUpdate();
       System.out.println("Registered Successfully!!!");

   }

   public void publish_result(int pwd,Connection conn) throws Exception
   {
       Scanner input = new Scanner(System.in);
       if(admin_pwd == pwd)
       {
           Statement st = conn.createStatement();
           System.out.println("Enter To Publish Or Not:(YES/NO): ");
           input.nextLine();
           String result_publish = input.nextLine();
           if(result_publish.equalsIgnoreCase("yes"))
           {
                   String sql2 = "UPDATE ADMIN SET published = true WHERE ID = "+1;
                   System.out.println("Result Published Successfully");
                   st.executeUpdate(sql2);
           }
           else {
               String sql = "SELECT * FROM Candidates";
               ResultSet rs = st.executeQuery(sql);
               while(rs.next())
               {
                   System.out.println(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getInt(4));
               }
           }
       }
       else {
           System.out.println("Invalid Pin");
       }
   }



}
