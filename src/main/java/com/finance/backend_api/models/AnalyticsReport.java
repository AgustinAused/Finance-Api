package com.finance.backend_api.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Table
@Getter
@Setter
@AllArgsConstructor
public class AnalyticsReport {

    @PrimaryKey
    private Long report_id;
    private Long Company_id;
    private String period;
    private Map<String, Double> data;

    public AnalyticsReport() {
    }

}
