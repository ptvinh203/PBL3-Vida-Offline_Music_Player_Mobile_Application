package httprequest;

import java.util.List;

public interface IHttpResponse<T> {
    T findById(int id) throws Exception;

    List<T> findAll() throws Exception;

    void deleteById(Integer id) throws Exception;

    boolean update(int id, T data) throws Exception;
}
