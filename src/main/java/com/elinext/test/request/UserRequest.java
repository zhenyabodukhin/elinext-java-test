package com.elinext.test.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRequest {

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100)
    private String login;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100)
    private String password;

    @Min(1)
    @Max(2147483647)
    private Long positionId;
}
