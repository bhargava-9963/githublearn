package com.spring.data.rest.springdatarust;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TextToPdfResponse {

    private String success;

    private String message;

    private String data;
}
