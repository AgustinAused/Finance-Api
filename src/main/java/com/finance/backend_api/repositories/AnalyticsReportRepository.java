package com.finance.backend_api.repositories;

import com.finance.backend_api.models.AnalyticsReport;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface AnalyticsReportRepository extends CassandraRepository<AnalyticsReport, Long> {
    // Custom Method
}
