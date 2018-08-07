package com.reserv.incubator.loader.model;

import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class DataModel {
    //{"query_type":"QueryCPValues","cp_values":[["cpoTz",37.7],["cpoHz",55],["cpoVZ",100],["cpoT",37.65],["cpoH",56],["cpoV",100]]}
    String query_type;
    ValueArray[] cp_values;

}

class ValueArray {
    String name;
    BigDecimal value;
}
