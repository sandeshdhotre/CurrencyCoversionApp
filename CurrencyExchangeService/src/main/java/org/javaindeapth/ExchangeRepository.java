package org.javaindeapth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<ExchangeValue, Long> {

	public Optional<ExchangeValue> findByFromAndTo(String from, String to);
}
