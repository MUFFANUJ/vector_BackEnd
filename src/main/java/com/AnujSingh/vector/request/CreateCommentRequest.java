package com.AnujSingh.vector.request;

import lombok.Data;

@Data
public class CreateCommentRequest {
    private String content;
    private Long issueId;
}
