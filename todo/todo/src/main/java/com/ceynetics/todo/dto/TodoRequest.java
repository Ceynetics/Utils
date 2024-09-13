package com.ceynetics.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequest {
    private String title;
    private String description;
    private boolean completed;
    private String priority; // "URGENT_AND_IMPORTANT", "IMPORTANT_BUT_NOT_URGENT", "URGENT_BUT_NOT_IMPORTANT", "NOT_URGENT_AND_NOT_IMPORTANT"
}
