package HttpRequest;

import java.util.List;

public interface IHttpResponse<T> {
    T findById(int id) throws Exception;
    List<T> findAll() throws Exception;
    T create(T data) throws Exception;
    void deleteById(Integer id) throws Exception;
    String update(int id, T data) throws Exception;
}
