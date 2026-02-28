package Voters;
import java.sql.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception
    {
        Scanner input = new Scanner(System.in);
        Connection conn = DBO.get_connection();
        Admin admin = new Admin();
        Voters voter = new Voters();
        while(true)
        {
            System.out.println("1.Admin");
            System.out.println("2.Candidates");
            System.out.println("3.Voters");
            System.out.print("Your Choice: ");
            int choice = input.nextInt();
            if(choice == 1)
            {
                System.out.print("Enter Your Password: ");
                int pwd = input.nextInt();
                admin.publish_result(pwd,conn);
            }
            else if(choice == 2)
            {
                System.out.print("Enter the Candidate's ID(Unique): ");
                int id = input.nextInt();
                input.nextLine();
                System.out.print("Enter The Candidate's Head: ");
                String name = input.nextLine();
                System.out.print("Enter the Parties Name: ");
                String party = input.nextLine();
                admin.new_candidates(id,name,party,conn);

            }
            else if(choice == 3)
            {
                System.out.print("Enter The Choice (1-Vote) and (2-View Results): ");
                int voters_choice = input.nextInt();
                System.out.print("Enter the Voter ID: ");
                int voter_id = input.nextInt();
                input.nextLine();
                System.out.print("Enter the E-Mail ID: ");
                String email = input.nextLine();
                System.out.print("Enter the Password: ");
                int pwd = input.nextInt();
                if(voters_choice == 1)
                {
                    voter.voters(voter_id,conn);
                }
                else {
                     voter.results(voter_id,conn);
                }

            }
        }
    }

}
