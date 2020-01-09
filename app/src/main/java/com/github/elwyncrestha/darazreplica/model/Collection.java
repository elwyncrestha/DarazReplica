package com.github.elwyncrestha.darazreplica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Elvin Shrestha on 1/10/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection {
    private String id;
    private String title;
    private String description;
    private String image;
    private String background;
}
