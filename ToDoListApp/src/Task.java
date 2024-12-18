//Individual Tasks
public class Task {
    private int id;
    private String description;
    private boolean isCompleted;

    public Task(int id, String description){
        this.id = id;
        this.description = description;
        this.isCompleted = false; // Default status: false;
    }


    //Getters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    //Mark task status to completed
    public void markAsCompleted(){
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Description: " + description +
                " | Status: " + (isCompleted ? "Completed" : "Incomplete");
    }
}
