package com.zw.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.pojo.response
 * @descripation TODO
 * @date 2021-06-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
