package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class MasterSearchListCondition {
    private List<FieldSearchDTO> conditions;
}
