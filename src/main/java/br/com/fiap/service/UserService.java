package br.com.fiap.service;

import br.com.fiap.constants.UserConstants;
import br.com.fiap.dto.UserReqDto;
import br.com.fiap.dto.UserResDto;
import br.com.fiap.entity.User;
import br.com.fiap.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public void editUser(UserReqDto userDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof UserDetails)) {
            throw new IllegalStateException("Usuario não autenticado");
        }

        User currentUser = (User) principal;
        User userDb = this.findById(currentUser.getId());

        BeanUtils.copyProperties(userDto, userDb, "password");

        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            userDb.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        userRepository.save(userDb);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public UserResDto findUserDetails(Long id) {
        User user = this.findById(id);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.convertValue(user, UserResDto.class);
    }

    public List<UserResDto> findAllUsersDetails() {
        List<User> users = this.findAll();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        return users.stream()
                .map(user -> objectMapper.convertValue(user, UserResDto.class))
                .collect(Collectors.toList());
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
