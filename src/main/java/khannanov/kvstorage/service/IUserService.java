package khannanov.kvstorage.service;

import khannanov.kvstorage.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService extends UserDetailsService {
    User getUserByUsername(String username) throws UsernameNotFoundException;
    void addUser(User user);
}
