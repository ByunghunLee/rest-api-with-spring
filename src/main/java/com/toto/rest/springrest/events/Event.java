package com.toto.rest.springrest.events;

import lombok.*;

import java.time.LocalDateTime;

@Builder @AllArgsConstructor @NoArgsConstructor
@Setter @Getter @EqualsAndHashCode(of = "id")
public class Event {

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDataTime;
    private LocalDateTime closeEnrollmentDataTime;
    private LocalDateTime beginEventDataTime;
    private LocalDateTime closeEventDataTime;
    private String location;
    private int basePrice;
    private int maxPrice;
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    private EventStats eventStats;


}
