package com.spring.data.rest.springdatarust;

import org.springframework.data.rest.core.config.Projection;

@Projection(
        name="websiteuser",
        types={WebsiteUser.class}
)
public interface Custom {

    Long getNumber();
    String getEmail();
}
