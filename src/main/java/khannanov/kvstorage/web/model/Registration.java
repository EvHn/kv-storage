package khannanov.kvstorage.web.model;

import khannanov.kvstorage.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class Registration {

    private String username;
    private String password;

    public User toUser(PasswordEncoder encoder) {
        return new User(username, encoder.encode(password));
    }
}
