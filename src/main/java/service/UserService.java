package service;

import java.sql.SQLException;

public interface UserService {

    boolean isUserExisted (String email, String password) throws SQLException;

}
