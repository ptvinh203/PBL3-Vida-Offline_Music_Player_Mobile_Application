package com.pbl3.musicapplication.model.model;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pbl3.musicapplication.model.entity.MyFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor@AllArgsConstructor
@Setter@Getter
@Builder
public class MyFileModel {
    private String fileName;
    private String fileType;
    private String fileDownloadUri;
    private long fileSize;

    public MyFileModel(MyFile entity) {
        this.fileName = entity.getFileName();
        this.fileType = entity.getFileType();

        String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/file/downloadFile/")
                            .path(entity.getFileId().toString())
                            .toUriString();
        this.fileDownloadUri = downloadUri;

        this.fileSize = entity.getFileData().length;
    }
}
