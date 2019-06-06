package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.warehouse.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer>, JpaSpecificationExecutor<Shop> {

    @Query("select s from Sales sl join sl.shop s join sl.transaction t where sl.shop = s and sl.transaction = t and s.id = 1")
    Shop findShopWithBestAverageIncome();
}
