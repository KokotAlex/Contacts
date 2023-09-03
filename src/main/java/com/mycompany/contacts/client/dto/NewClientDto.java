package com.mycompany.contacts.client.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewClientDto {

    @NotBlank
    @Size(max = 100)
    private String name;
}
