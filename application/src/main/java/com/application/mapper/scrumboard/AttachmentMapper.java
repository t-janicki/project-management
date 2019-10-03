package com.application.mapper.scrumboard;

import com.scrumboard.domain.Attachment;
import com.utility.dto.scrumboard.AttachmentDTO;
import org.springframework.stereotype.Component;

@Component
public final class AttachmentMapper {

    public Attachment mapToAttachment(AttachmentDTO attachmentDTO) {
        return new Attachment(
                attachmentDTO.getId(),
                attachmentDTO.getName(),
                attachmentDTO.getSrc(),
                attachmentDTO.getTime(),
                attachmentDTO.getType()
        );
    }

    public AttachmentDTO mapToAttachmentDTO(Attachment attachment) {
        return new AttachmentDTO(
                attachment.getId(),
                attachment.getName(),
                attachment.getSrc(),
                attachment.getTime(),
                attachment.getType()
        );
    }
}
