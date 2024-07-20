package com.finance.backend_api.repositories;

import com.finance.backend_api.models.AnalyticsReport;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsReportRepository extends CassandraRepository<AnalyticsReport, Long> {
    // Custom Method
}
