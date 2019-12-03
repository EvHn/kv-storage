package khannanov.kvstorage.service;

import khannanov.kvstorage.model.User;
import khannanov.kvstorage.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

    @Override
    public void addUser(User user) {
        if(!userRepository.existsById(user.getUsername())) {
            userRepository.save(user);
        }
    }
}
