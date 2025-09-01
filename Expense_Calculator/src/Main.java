import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== Mini Expense Tracker ====");
            System.out.println("1. Add Expense");
            System.out.println("2. Show Monthly Report");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                sc.nextLine(); // consume leftover newline 
                System.out.print("Enter Category (Food/Travel/Study): ");
                String category = sc.nextLine();
                System.out.print("Enter Description: ");
                String desc = sc.nextLine();
                System.out.print("Enter Amount: ");
                double amt = sc.nextDouble();
                sc.nextLine();
                System.out.print("Enter Date (YYYY-MM-DD): ");
                String date = sc.nextLine();

                Expense e = new Expense(category, desc, amt, date);
                manager.addExpense(e);

            } else if (choice == 2) {
                manager.monthlyReport();
            } else {
                System.out.println(" Exiting...");
                break;
            }
        }
        sc.close();
    }
}
