package com.daylicode.caloriestrackerapplication.service;




import com.daylicode.caloriestrackerapplication.model.User;
import com.daylicode.caloriestrackerapplication.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }

    public User createUser(@Valid User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, @Valid User userDetails) {
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setAge(userDetails.getAge());
        user.setWeight(userDetails.getWeight());
        user.setHeight(userDetails.getHeight());
        user.setGoal(userDetails.getGoal());
        userRepository.save(user);
        return user;
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}
