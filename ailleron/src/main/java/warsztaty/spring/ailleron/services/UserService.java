package warsztaty.spring.ailleron.services;

import org.springframework.stereotype.Service;
import warsztaty.spring.ailleron.exceptions.UserExistException;
import warsztaty.spring.ailleron.exceptions.UserNotFoundException;
import warsztaty.spring.ailleron.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    static List<User> users = new ArrayList<>();

    public UserService() {
        if (users.isEmpty()) {
            users.add(new User(1L, "Mateusz", "Mnich", 28));
            users.add(new User(2L, "Aleksander", "Kwaśniewski", 58));
            users.add(new User(3L, "Andrzej", "Duda", 45));
        }
    }

    private Optional<User> findUserByName(String name) {
        return users.stream().filter(u -> u.getName().equals(name)).findFirst();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = findUserById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException("Użytkownik o id: " + id + " nie istnieje");
    }

    public User getUserByName(String name) throws UserNotFoundException {
        Optional<User> user = findUserByName(name);
        if (user.isPresent()) {
            return user.get();
        }

        throw new UserNotFoundException("Nie znaleziono użytkownika o imieniu: " + name);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public Long addUser(User user) throws UserExistException {
        Optional<User> existingUser = findUserByName(user.getName());
        if (existingUser.isPresent()) {
            throw new UserExistException("Użytkownik o imieniu " + user.getName() + " istnieje");
        }
        user.setId(Long.valueOf(users.size() + 1));
        users.add(user);
        return user.getId();
    }

    public User modifyUser(User user) throws UserNotFoundException {
        deleteUserById(user.getId());
        users.add(user);
        return user;
    }

    private Optional<User> findUserById(Long id) {
        return users.stream().filter(u -> u.getId().compareTo(id) == 0).findFirst();
    }

    private void deleteUserById(Long id) throws UserNotFoundException {
        Optional<User> modifiedUser = findUserById(id);
        if (!modifiedUser.isPresent()) {
            throw new UserNotFoundException("Nie znaleziono użytkownika o id: " + id);
        }
        users.remove(modifiedUser.get());
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        deleteUserById(id);
    }

}
