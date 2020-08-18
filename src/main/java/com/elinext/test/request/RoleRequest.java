package com.elinext.test.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RoleRequest {

    @Min(1)
    @Max(2147483647)
    private Long userId;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100)
    private String roleName;
}
