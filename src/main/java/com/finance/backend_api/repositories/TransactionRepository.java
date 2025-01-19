package com.finance.backend_api.repositories;

import com.finance.backend_api.DTOs.MonthlyTransactionDTO;
import com.finance.backend_api.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCompanyId(Long companyId);

    List<Transaction> findByCompanyIdAndType(Long companyId, String transactionType);

    List<Transaction> findByCategoryIdAndCompanyId(Long categoryId, Long companyId);

    // Método para obtener las transacciones dentro de un rango de fechas
    List<Transaction> findByTimestampBetween(Date startDate, Date endDate);


    // Obtener las transacciones por mes y año (agregadas por tipo de transacción)
    @Query("SELECT new com.finance.backend_api.DTOs.MonthlyTransactionDTO(" +
            "MONTH(t.timestamp), " + // Mes de la transacción
            "YEAR(t.timestamp), " +  // Año de la transacción
            "SUM(CASE WHEN t.type = 'income' THEN t.amount ELSE 0 END), " +
            "SUM(CASE WHEN t.type = 'expense' THEN t.amount ELSE 0 END)) " +
            "FROM Transaction t " +
            "WHERE t.company.id = :companyId " +
            "GROUP BY MONTH(t.timestamp), YEAR(t.timestamp) " +
            "ORDER BY YEAR(t.timestamp), MONTH(t.timestamp)")
    List<MonthlyTransactionDTO> findMonthlyTransactionsByCompany(@Param("companyId") Long companyId);

}
