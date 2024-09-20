package com.AnujSingh.vector.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitRequest {
    private String email;
    private Long projectId;
}
