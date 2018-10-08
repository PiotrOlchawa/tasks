package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class TrelloListDto {

    @Getter
    @Setter
    String Id;
    @Getter
    @Setter
    String name;
    @Getter
    @Setter
    boolean closed;
}
