import javax.sound.midi.Soundbank;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class ToDoListApp {
    private ArrayList<Task> taskList;
    private int taskIdCounter;
    private Scanner scanner;

    public ToDoListApp(){
        taskList = new ArrayList<>();
        taskIdCounter = 1;
        scanner = new Scanner(System.in);
    }

    public void addTask(){
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        Task task = new Task(taskIdCounter++, description);
        taskList.add(task);
        System.out.println("Task added successfully!");
    }

    public void deleteTask(){
        System.out.print("Enter task ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for(Task task: taskList){
            if(task.getId() == id){
                taskList.remove(task);
                System.out.println("Task deleted successfully.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void viewTasks(){
        if(taskList.isEmpty()){
            System.out.println("No tasks to display!");
            return;
        }
        System.out.println("\n==== Your To-Do List ====");
        for(Task task: taskList){
            System.out.println(task);
        }
    }

    public void markTaskAsCompleted() {
        System.out.print("Enter task ID to mark as completed: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Task task : taskList) {
            if (task.getId() == id) {
                task.markAsCompleted();
                System.out.println("Task marked as completed!");
                return;
            }
        }
        System.out.println("Task ID not found.");
    }

    public void saveTasks() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        String fileName = username + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Task task : taskList) {
                writer.write(task.getId() + "," + task.getDescription() + "," + task.isCompleted());
                writer.newLine();
            }
            System.out.println("Tasks saved successfully for user: " + username);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void loadTasks() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        String fileName = username + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            taskList.clear(); // Clear existing tasks before loading
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String description = parts[1];
                boolean isCompleted = Boolean.parseBoolean(parts[2]);
                Task task = new Task(id, description);
                if (isCompleted) {
                    task.markAsCompleted();
                }
                taskList.add(task);
                taskIdCounter = Math.max(taskIdCounter, id + 1); // Update ID counter
            }
            System.out.println("Tasks loaded successfully for user: " + username);
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found for user: " + username);
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n===== To-Do List Application =====");
            System.out.println("1. Add a Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Mark a Task as Completed");
            System.out.println("4. Delete a Task");
            System.out.println("5. Save Tasks");
            System.out.println("6. Load Tasks");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    markTaskAsCompleted();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    saveTasks();
                    break;
                case 6:
                    loadTasks();
                    break;
                case 7:
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }



    public static void main(String[] args) {
        ToDoListApp app = new ToDoListApp();
        app.displayMenu();
    }
}


