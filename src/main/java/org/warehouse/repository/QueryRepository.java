package org.warehouse.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.warehouse.dto.ShopDto;

@Component
public class QueryRepository {

    private static final String FIRST_QUERY = "select * from shop_tab sh where sh.id = (select sl.shop_id as shopId " +
            "from sales_tab sl group by sl.transaction_id, sl.shop_id order by avg(sl.revenue * sl.quantity) desc limit 1);";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ShopDto firstQuery() {
        return jdbcTemplate.query(FIRST_QUERY,
                rs -> {
                    Integer id = null, locationId = null, managerId = null;
                    String name = null;
                    while (rs.next()) {
                        id = rs.getInt("id");
                        name = rs.getString("name");
                        locationId = rs.getInt("location_id");
                        managerId = rs.getInt("manager_id");
                    }
                    return name != null ? new ShopDto(id, name, locationId, managerId) : null;

                }
        );
    }
}
