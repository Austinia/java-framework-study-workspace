package com.austinia.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "authority")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDto {
    @Id
    private String authorityName;
}
