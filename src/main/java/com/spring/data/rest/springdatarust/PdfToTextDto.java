package com.spring.data.rest.springdatarust;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PdfToTextDto {

    private String lang;

    private String content;
}
