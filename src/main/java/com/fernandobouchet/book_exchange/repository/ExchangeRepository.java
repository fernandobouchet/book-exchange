package com.fernandobouchet.book_exchange.repository;

import com.fernandobouchet.book_exchange.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange,Long> {
}
