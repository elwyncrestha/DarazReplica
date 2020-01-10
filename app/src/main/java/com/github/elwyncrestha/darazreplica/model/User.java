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
public class User {
    private String name;
    private String email;
    private String password;
}
