package com.crud.tasks.domain.createdTrelloCard;

import com.crud.tasks.domain.createdTrelloCard.AttachmentByType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BadgesDto {

    @JsonProperty("votes")
    int votes;
    @JsonProperty("attachmentsByType")
    private AttachmentByType attachments;

}
