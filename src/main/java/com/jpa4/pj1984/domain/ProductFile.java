package com.jpa4.pj1984.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFile {
    private String orgFileName;
    private String storedFileName;
}
