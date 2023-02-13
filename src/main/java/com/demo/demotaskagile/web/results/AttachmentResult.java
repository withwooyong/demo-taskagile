package com.demo.demotaskagile.web.results;

import com.demo.demotaskagile.domain.common.file.FileUrlCreator;
import com.demo.demotaskagile.domain.model.attachment.Attachment;
import com.demo.demotaskagile.utils.ImageUtils;
import org.springframework.http.ResponseEntity;

public class AttachmentResult {

    public static ResponseEntity<ApiResult> build(Attachment attachment, FileUrlCreator fileUrlCreator) {
        String fileUrl = fileUrlCreator.url(attachment.getFilePath());
        ApiResult apiResult = ApiResult.blank()
                .add("id", attachment.getId().value())
                .add("fileName", attachment.getFileName())
                .add("fileType", attachment.getFileType())
                .add("fileUrl", fileUrl)
                .add("userId", attachment.getUserId().value())
                .add("createdDate", attachment.getCreatedDate().getTime());

        if (attachment.isThumbnailCreated()) {
            apiResult.add("previewUrl", ImageUtils.getThumbnailVersion(fileUrl));
        }
        return Result.ok(apiResult);
    }
}
