package com.vmantek.chimera.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable
{
    @Id
    @GeneratedValue
    Long id;

    @NonNull
    String name;

    @NonNull
    String description;
}
