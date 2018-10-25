package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@AllArgsConstructor
// gradle build wylatuje tak jakby nie widział @AllArgsConstructor ???
//@NoArgsConstructor  to samo
public class TaskDto {

    private Long id;
    private String title;
    private String content;

    public TaskDto() {
    }

    public TaskDto(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
