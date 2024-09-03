import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {
    @Serial
    public static long serialVersionUID = 1L;
    private String email, password, firstName, lastName;
    public User(String email, String password, String firstName, String lastName){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public boolean equals(Object other){
        if(other instanceof User){
            User o = (User)other;
            return o.email.equals(this.email);
        }
        return false;
    }
    @Override
    public int hashCode(){
        return email.hashCode();
    }
}
