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
public class RoomRequest {

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100)
    private String type;
}
