package com.lecture.car_rental1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileDTO {

    private String name;
    private String url;
    private String type;
    private long size;
}
