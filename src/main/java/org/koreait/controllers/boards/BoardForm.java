package org.koreait.controllers.boards;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BoardForm {
    @NotBlank
    private String subject;
    @NotBlank
    private String content;
}
