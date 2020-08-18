package com.elinext.test.request;

import lombok.*;

import javax.validation.constraints.*;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ReservationRequest {

    @Min(1)
    @Max(2147483647)
    private Long roomId;

    @Min(1)
    @Max(2147483647)
    private Long userId;

    @NotEmpty
    @NotNull
    @Size(min = 5, max = 100)
    private String operation;

    @NotEmpty
    @NotNull
    @Size(min = 5, max = 255)
    private String operationDescription;

    @NotEmpty
    @NotNull
    private Time startTime;

    @NotEmpty
    @NotNull
    private Time endTime;
}
