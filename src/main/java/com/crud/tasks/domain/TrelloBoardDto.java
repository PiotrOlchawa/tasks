package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class TrelloBoardDto {

    @JsonProperty("id")
    @Getter
    private String id;

    @JsonProperty("name")
    @Getter
    private String name;

    @JsonProperty("lists")
    private List<TrelloListDto> lists;

}