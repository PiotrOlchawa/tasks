package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Badges {

    @JsonProperty("votes")
    int votes;
    @JsonProperty("attachmentsByType")
    private AttachmentByType attachments;

}
