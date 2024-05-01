package Person;

public class User extends Person {
    public User(String username, String password) {
        super(username, password);
    }

    // Implementation of abstract method
    @Override
    public void displayMenu() {
        // Display user menu
    }
}
