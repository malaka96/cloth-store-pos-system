package service.imple;

import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public boolean isUserExisted(String email, String password) throws SQLException {
        ResultSet resultSet = userRepository.getUser(email,password);
        return resultSet.next();
    }
}
