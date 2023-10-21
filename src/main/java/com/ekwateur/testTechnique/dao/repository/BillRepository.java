package com.ekwateur.testTechnique.dao.repository;

import com.ekwateur.testTechnique.dao.models.Bill;
import com.ekwateur.testTechnique.dao.models.QBill;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface BillRepository extends JpaRepository<Bill, Long>, QuerydslPredicateExecutor<Bill>, QuerydslBinderCustomizer<QBill> {

  //  Bill findBillByClientAndDate(Long clientId, LocalDate date);
    @Query("SELECT b FROM Bill b WHERE b.client.id = :clientId AND b.date = :date")
    Bill findBillByClientAndDate(@Param("clientId") Long clientId, @Param("date") LocalDate date);
    default void customize(QuerydslBindings bindings, QBill root) {

        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
  @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END " +
          "FROM Bill b " +
          "WHERE b.client.id = :clientId " +
          "AND EXTRACT(MONTH FROM b.date) = :month " +
          "AND EXTRACT(YEAR FROM b.date) = :year")
  boolean existsBillForClientOnMonthAndYear(
          @Param("clientId") Long clientId,
          @Param("month") int month,
          @Param("year") int year
  );
}
