package HttpRequest;

import java.io.File;

public interface IMyFileResponse {
    String artistFileUpload(int artistId, File artistFile) throws Exception;
    String songFileUpload(int songId, File songFile) throws Exception;
    void deleteById(int id) throws Exception;
}
