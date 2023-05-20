package com.pbl3.musicapplication.model.model;

import com.pbl3.musicapplication.model.entity.MyFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MyFileModel {
    private static final String SERVER_IP = "172.20.10.5";
    private static final String SERVER_PORT = "8080";
    private static final String URL_API = "https//" + SERVER_IP + ":" + SERVER_PORT + "/file/downloadFile/";

    private String fileName;
    private String fileType;
    private String fileDownloadUri;
    private long fileSize;

    public MyFileModel(MyFile entity) {
        this.fileName = entity.getFileName();
        this.fileType = entity.getFileType();

        String downloadUri = URL_API + entity.getFileId();
        this.fileDownloadUri = downloadUri;

        this.fileSize = entity.getFileData().length;
    }
}
