package com.robihidayat.shopie.promo.repository;

import com.robihidayat.shopie.promo.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {

}
