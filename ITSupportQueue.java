import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Ticket {
    int id;
    String studentName;
    String problem;

    Ticket(int id, String studentName, String problem) {
        this.id = id;
        this.studentName = studentName;
        this.problem = problem;
    }

    void display() {
        System.out.println("Ticket ID: " + id);
        System.out.println("Student: " + studentName);
        System.out.println("Problem: " + problem);
        System.out.println("-------------------------");
    }
}

public class ITSupportQueue {

    static Queue<Ticket> tickets = new LinkedList<>();
    static Scanner input = new Scanner(System.in);
    static int nextId = 1;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\nCAMPUS IT SUPPORT SYSTEM");
            System.out.println("1. Submit a ticket");
            System.out.println("2. View waiting tickets");
            System.out.println("3. Process next ticket");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            while (!input.hasNextInt()) {
                System.out.print("Enter a number from 1 to 4: ");
                input.next();
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    submitTicket();
                    break;

                case 2:
                    viewTickets();
                    break;

                case 3:
                    processTicket();
                    break;

                case 4:
                    System.out.println("Thank you for using the system.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        input.close();
    }

    static void submitTicket() {
        System.out.print("Enter student name: ");
        String name = input.nextLine().trim();

        System.out.print("Describe the problem: ");
        String problem = input.nextLine().trim();

        if (name.isEmpty() || problem.isEmpty()) {
            System.out.println("Name and problem cannot be empty.");
            return;
        }

        Ticket ticket = new Ticket(nextId++, name, problem);
        tickets.offer(ticket);

        System.out.println("Ticket submitted successfully!");
        System.out.println("Ticket number: " + ticket.id);
    }

    static void viewTickets() {
        if (tickets.isEmpty()) {
            System.out.println("There are no waiting tickets.");
            return;
        }

        System.out.println("\nWAITING TICKETS");

        for (Ticket ticket : tickets) {
            ticket.display();
        }

        System.out.println("Total waiting: " + tickets.size());
    }

    static void processTicket() {
        Ticket ticket = tickets.poll();

        if (ticket == null) {
            System.out.println("There are no tickets to process.");
        } else {
            System.out.println("Processing the next ticket:");
            ticket.display();
            System.out.println("Ticket processed successfully.");
        }
    }
}