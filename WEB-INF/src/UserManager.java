import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users;

    public UserManager(Map<String, User>users){
        this.users = users;
    }
    public UserManager(){
        this.users = new HashMap<>();
    }

    public Map<String, User> getUsers(){
        return users;
    }
    public void registerUser(User userToRegister){
        if(userToRegister == null || userToRegister.getEmail() == null || userToRegister.getEmail().isEmpty() ||
        userToRegister.getFirstName() == null || userToRegister.getFirstName().isEmpty() ||
        userToRegister.getLastName() == null || userToRegister.getLastName().isEmpty() ||
        userToRegister.getPassword() == null || userToRegister.getPassword().isEmpty()){
            throw new InvalidParameterException("The user isn't correct");
        }
        if(users.containsKey(userToRegister.getEmail())){
            throw new IllegalStateException("The user is already registered");
        }
        users.put(userToRegister.getEmail(), userToRegister);
    }
    public User loginUser(User userToLogin){
        if(userToLogin.getEmail() == null || userToLogin.getEmail().isEmpty()
        || userToLogin.getPassword() == null || userToLogin.getPassword().isEmpty()){
            throw new InvalidParameterException("The user isn't correct");
        }
        if(!users.containsKey(userToLogin.getEmail())){
            throw new IllegalArgumentException("This user doesn't exist");
        }
        User user = users.get(userToLogin.getEmail());
        if(!user.getPassword().equals(userToLogin.getPassword()) || !user.getEmail().equals(userToLogin.getEmail())){
            throw new IllegalStateException("Credentials don't match");
        }
        return user;
    }

}
