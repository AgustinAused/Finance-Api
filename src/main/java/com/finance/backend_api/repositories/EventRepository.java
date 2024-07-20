package com.finance.backend_api.repositories;

import com.finance.backend_api.models.Event;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CassandraRepository<Event, Long> {
    // Custom Method
}
