import java.util.*;
import java.time.LocalDate;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Integer> registrationNumbers = new ArrayList<Integer>();
    static ArrayList<String> names = new ArrayList<String>();
    static ArrayList<String> registrationDates = new ArrayList<String>();
    static ArrayList<String> status = new ArrayList<String>();

    static Queue<Integer> checkInQueue = new LinkedList<Integer>();

    static int nextNumber = 1001;

    public static void main(String[] args) {
        int option = -1;

        while (option != 11) {
            System.out.println("\n==================================================");
            System.out.printf("%29s%n", "OrgStudEase");
            System.out.println("==================================================");
            System.out.println("\nWhat would you like to do today?");
            System.out.println("\nRegistration");
            System.out.println("   [1] Register a new student");
            System.out.println("   [2] Display registered students");
            System.out.println("   [3] Search student");
            System.out.println("\nCheck-in");
            System.out.println("   [4] Add to check-in queue (Enqueue)");
            System.out.println("   [5] Check in next student (Dequeue)");
            System.out.println("   [6] Peek next student");
            System.out.println("   [7] Display check-in queue");
            System.out.println("   [8] Check if queue is empty");
            System.out.println("\nSorting");
            System.out.println("   [9] Sort by Name (Bubble Sort)");
            System.out.println("   [10] Sort by Registration Number (Selection Sort)");
            System.out.println("\n   [11] Close program");

            System.out.print("\nEnter an option (1-11): ");
            String optionEntered = sc.nextLine();

            try {
                option = Integer.parseInt(optionEntered.trim());
            } catch (Exception e) {
                option = -1;
            }

            switch (option) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    addToQueue();
                    break;
                case 5:
                    checkInNext();
                    break;
                case 6:
                    viewNext();
                    break;
                case 7:
                    displayQueue();
                    break;
                case 8:
                    checkQueueEmpty();
                    break;
                case 9:
                    bubbleSort();
                    break;
                case 10:
                    selectionSort();
                    break;
                case 11:
                    System.out.println("Thank you for using OrgStudEase! Have a blessed day.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1 to 11.");
                    break;
            }
        }

        sc.close();
    }

    static void registerStudent() {
        System.out.println("\n----- Register Student -----");
        System.out.print("\nEnter full name (Last Name, First Name M.I.): ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Please enter a name.");
            return;
        }

        if (!name.contains(",")) {
            System.out.println("Please use the format: Last Name, First Name M.I.");
            return;
        }

        String date = LocalDate.now().toString();

        registrationNumbers.add(nextNumber);
        names.add(name);
        registrationDates.add(date);
        status.add("Not checked in");

        System.out.println("\nStudent successfully registered!");
        System.out.println("\nRegistration No.: " + nextNumber);
        System.out.println("Name: " + name);
        System.out.println("Registration Date: " + date);

        nextNumber++;
    }

    static void displayStudents() {
        if (registrationNumbers.isEmpty()) {
            System.out.println("No registered students.");
            return;
        }

        System.out.println("\n----- Registered Students -----");

        for (int i = 0; i < registrationNumbers.size(); i++) {
            System.out.println("\nRegistration No.: " + registrationNumbers.get(i));
            System.out.println("Name: " + names.get(i));
            System.out.println("Registration Date: " + registrationDates.get(i));
            System.out.println("Status: " + status.get(i));
        }
    }

    static void searchStudent() {
        if (registrationNumbers.isEmpty()) {
            System.out.println("No registered students.");
            return;
        }

        System.out.print("\nEnter registration number: ");
        int number;

        try {
            number = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        int found = -1;

        for (int i = 0; i < registrationNumbers.size(); i++) {
            if (registrationNumbers.get(i) == number) {
                found = i;
                break;
            }
        }

        if (found == -1) {
            System.out.println("No student found with that registration number.");
        } else {
            System.out.println("\n----- Student Information -----");
            System.out.println("Registration No.: " + registrationNumbers.get(found));
            System.out.println("Name: " + names.get(found));
            System.out.println("Registration Date: " + registrationDates.get(found));
            System.out.println("Status: " + status.get(found));
        }
    }

    static void addToQueue() {
        System.out.print("\nEnter registration number: ");
        int number;

        try {
            number = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("\nPlease enter a valid number.");
            return;
        }

        int found = -1;

        for (int i = 0; i < registrationNumbers.size(); i++) {
            if (registrationNumbers.get(i) == number) {
                found = i;
                break;
            }
        }

        if (found == -1) {
            System.out.println("Student not found.");
        } else if (status.get(found).equals("Checked in")) {
            System.out.println("This student is already checked in.");
        } else if (checkInQueue.contains(number)) {
            System.out.println("This student is already in the queue.");
        } else {
            checkInQueue.add(number);
            System.out.println("\n" + names.get(found) + " has been added to the queue.");
        }
    }

    static void checkInNext() {
        if (checkInQueue.isEmpty()) {
            System.out.println("There are no students waiting in the queue.");
            return;
        }

        int number = checkInQueue.poll();
        int found = registrationNumbers.indexOf(number);

        if (found != -1) {
            status.set(found, "Checked in");
            System.out.println("\nStudent checked in successfully!");
            System.out.println("Name: " + names.get(found));
            System.out.println("Registration No.: " + number);
        }
    }

    static void viewNext() {
        if (checkInQueue.isEmpty()) {
            System.out.println("No students are currently in the queue.");
            return;
        }

        int number = checkInQueue.peek();
        int found = registrationNumbers.indexOf(number);

        if (found != -1) {
            System.out.println("\n----- Next Student -----");
            System.out.println("Name: " + names.get(found));
            System.out.println("Registration No.: " + number);
            System.out.println("Waiting for check-in.");
        }
    }

    static void displayQueue() {
        if (checkInQueue.isEmpty()) {
            System.out.println("The queue is empty.");
            return;
        }

        System.out.println("\n----- Students Waiting for Check-in -----");
        int place = 1;

        for (int number : checkInQueue) {
            int found = registrationNumbers.indexOf(number);

            if (found != -1) {
                System.out.println(place + ". " + registrationNumbers.get(found) + " - " + names.get(found));
                place++;
            }
        }
    }

    static void checkQueueEmpty() {
        if (checkInQueue.isEmpty()) {
            System.out.println("\nThere are no students in the queue.");
        } else {
            System.out.println("\nThere are " + checkInQueue.size() + " student(s) waiting in the queue.");
        }
    }

    static void bubbleSort() {
        if (names.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        System.out.println("\nBefore Bubble Sort:");
        displaySortedRecords();

        for (int i = 0; i < names.size() - 1; i++) {
            for (int j = 0; j < names.size() - 1 - i; j++) {
                if (names.get(j).compareToIgnoreCase(names.get(j + 1)) > 0) {
                    swap(j, j + 1);
                }
            }
        }

        System.out.println("\nAfter Bubble Sort (Alphabetical by Name):");
        displaySortedRecords();
    }

    static void selectionSort() {
        if (registrationNumbers.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        System.out.println("\nBefore Selection Sort:");
        displaySortedRecords();

        for (int i = 0; i < registrationNumbers.size() - 1; i++) {
            int smallest = i;

            for (int j = i + 1; j < registrationNumbers.size(); j++) {
                if (registrationNumbers.get(j) < registrationNumbers.get(smallest)) {
                    smallest = j;
                }
            }

            if (smallest != i) {
                swap(i, smallest);
            }
        }

        System.out.println("\nAfter Selection Sort (Registration Number):");
        displaySortedRecords();
    }

    static void displaySortedRecords() {
        System.out.printf("%-20s %-30s %-18s %s%n", "Registration No.", "Student Name", "Registration Date", "Status");
        System.out.println("-------------------------------------------------------------------------------");

        for (int i = 0; i < registrationNumbers.size(); i++) {
            System.out.printf("%-20d %-30s %-18s %s%n", registrationNumbers.get(i), names.get(i),
                    registrationDates.get(i), status.get(i));
        }
    }

    static void swap(int first, int second) {
        int tempNumber = registrationNumbers.get(first);
        registrationNumbers.set(first, registrationNumbers.get(second));
        registrationNumbers.set(second, tempNumber);

        String tempName = names.get(first);
        names.set(first, names.get(second));
        names.set(second, tempName);

        String tempDate = registrationDates.get(first);
        registrationDates.set(first, registrationDates.get(second));
        registrationDates.set(second, tempDate);

        String tempStatus = status.get(first);
        status.set(first, status.get(second));
        status.set(second, tempStatus);
    }
}
