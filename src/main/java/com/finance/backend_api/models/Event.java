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
@Setter
@Getter
@AllArgsConstructor
public class Event {

    @PrimaryKey
    private UUID event_id;
    private UUID user_id;
    private String event_type;
    private Date timestamp;
    private Map<String, String> details;

    public Event() {
    }


}
