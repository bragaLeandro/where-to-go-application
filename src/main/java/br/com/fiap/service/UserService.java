package br.com.fiap.service;

import br.com.fiap.constants.UserConstants;
import br.com.fiap.dto.UserReqDto;
import br.com.fiap.entity.User;
import br.com.fiap.repository.UserRepository;
import br.com.fiap.util.MapperUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserReqDto userDto) {
        User user = objectMapper.convertValue(userDto, User.class);

        //TODO: Create more validations
        //TODO: Separate validations into different classes
        if (this.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email já existe");
        }

        //TODO: Put this logic in a new method
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserConstants.USER);
        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
