package bg.softuni.mobilele.models.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {
    @NotNull
    @Size(min = 5, max = 20, message = "First name should be between 5 and 20 symbols!")
    private String firstName;
    @NotNull
    @Size(min = 5, max = 20, message = "Last name should be between 5 and 20 symbols!")
    private String lastName;
    @NotNull
    @Size(min = 5, max = 20, message = "Password  should be between 5 and 20 symbols!")
    private String password;
    @NotNull
    @Email(message = "You should enter a valid email!")
    private String email;
    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + (password == null ? "N/A" : "[PROVIDED]") + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}