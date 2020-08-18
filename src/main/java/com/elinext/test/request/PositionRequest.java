package com.elinext.test.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PositionRequest {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String positionName;
}
