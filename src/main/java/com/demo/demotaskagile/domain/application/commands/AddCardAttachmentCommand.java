package com.demo.demotaskagile.domain.application.commands;

import com.demo.demotaskagile.domain.model.card.CardId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
public class AddCardAttachmentCommand extends UserCommand {

    private final CardId cardId;
    private final MultipartFile file;

    public AddCardAttachmentCommand(long cardId, MultipartFile file) {
        this.cardId = new CardId(cardId);
        this.file = file;
    }

}
