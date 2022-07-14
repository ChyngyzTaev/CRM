package com.example.CRM.service.impl;

import com.example.CRM.entity.Users;
import com.example.CRM.exception.NotFoundException;
import com.example.CRM.model.UsersModel;
import com.example.CRM.repository.UsersRepository;
import com.example.CRM.request.AuthenticationRequest;
import com.example.CRM.response.AuthenticationResponse;
import com.example.CRM.security.jwt.JwtUtil;
import com.example.CRM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public UsersModel addNewUser(UsersModel usersModel) {
        Users users = new Users();
        Long id = usersModel.getId();
        checkIdForNull(id);
        users.setId(usersModel.getId());
        users.setCreateDate(usersModel.getCreateDate());
        users.setEmail(usersModel.getEmail());
        users.setPassword(usersModel.getPassword());
        usersRepository.save(users);
        return usersModel;
    }

    @Override
    public ResponseEntity<?> login(AuthenticationRequest authenticationRequest) {
        String userName = authenticationRequest.getUserName();
        String password = authenticationRequest.getPassword();

        if (userName == null || userName.isEmpty())
            return getErrorAuthorizationMessage("Заполните поле логин");

        if (password == null || password.isEmpty())
            return getErrorAuthorizationMessage("Заполните поле пароль");

        if (!userName.equals("admin") || !password.equals("admin"))
            getErrorAuthorizationMessage("Неверный логин или пароль");

        if (!userName.equals("trainer") || !password.equals("trainer"))
            getErrorAuthorizationMessage("Неверный логин или пароль");

        if (!userName.equals("client") || !password.equals("client"))
            getErrorAuthorizationMessage("Неверный логин или пароль");
        else {
            getErrorAuthorizationMessage("Неверный логин или пароль");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    @Override
    public Users getUserById(Long id) {
        return usersRepository
                .findById(id)
                .orElseThrow(()
                        ->
                        new NotFoundException
                                ("Пользоватль связонный с id " + id + "не найден!"));
    }

    @Override
    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }


    @Override
    public void deleteUserById(Long id) {
        usersRepository.deleteById(id);
    }

    public void checkIdForNull(Long id){
        if (id == null){
            System.out.println("Id не может быть пустым!");
        }
    }
    private ResponseEntity<?> getErrorAuthorizationMessage(String message) {
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
