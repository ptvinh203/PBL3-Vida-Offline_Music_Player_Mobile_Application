package httprequest;

import java.util.List;

import models.UserModel;

public interface IUserResponse {
    void deleteById(Integer userId) throws Exception;

    List<UserModel> findAll() throws Exception;
}
