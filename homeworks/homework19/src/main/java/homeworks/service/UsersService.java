package homeworks.service;

import homeworks.model.Users;
import homeworks.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    @Autowired
    private final UsersRepository usersRepository;

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users getById(long id) {
        return usersRepository.findById(id)
                .orElseThrow();
    }

    public void clear() {
        usersRepository.deleteAll();
    }

    public Users save(Users user) {
        return usersRepository.save(user);
    }
}
