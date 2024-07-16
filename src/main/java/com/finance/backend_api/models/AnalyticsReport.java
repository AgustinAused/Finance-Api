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
    private UUID report_id;
    private UUID Company_id;
    private Date period;
    private Map<String, Double> data;

    public AnalyticsReport() {
    }

}
