package org.warehouse.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.warehouse.dto.ManagerDto;
import org.warehouse.dto.ObservableDto;
import org.warehouse.dto.ProductDto;
import org.warehouse.dto.ShopDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryRepository {

    private static final String FIRST_QUERY = "select * from shop_tab sh where sh.id = (select sl.shop_id as shopId " +
            "from sales_tab sl group by sl.transaction_id, sl.shop_id order by avg(sl.revenue * sl.quantity) desc limit 1);";

    private static final String THIRD_QUERY = "select mt.id, mt.firstname, mt.lastname, sum(st.revenue * st.quantity) " +
            "as suma from manager_tab mt join shop_tab sh on mt.id = sh.manager_id join sales_tab st on sh.id = st.shop_id " +
            "group by mt.id, mt.firstname, mt.lastname, st.shop_id order by suma desc limit 10;";

    private static final String FIFTH_QUERY = "select pt.id, pt.name, ct.name as category_name, sum(st.quantity) " +
            "as quantity_sum from product_tab pt join sales_tab st on pt.id = st.product_id join category_tab ct " +
            "on pt.category_id = ct.id group by pt.id, ct.name, pt.name order by quantity_sum desc limit 10;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ObservableDto> firstQuery() {
        return jdbcTemplate.query(FIRST_QUERY,
                rs -> {
                    List<ObservableDto> dtos = new ArrayList<>();
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        int locationId = rs.getInt("location_id");
                        int managerId = rs.getInt("manager_id");
                        dtos.add(new ShopDto(id, name, locationId, managerId));
                    }
                    return dtos;
                }
        );
    }

    public List<ObservableDto> thirdQuery() {
        return jdbcTemplate.query(THIRD_QUERY,
                rs -> {
                    List<ObservableDto> dtos = new ArrayList<>();
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String firstname = rs.getString("firstname");
                        String lastname = rs.getString("lastname");
                        int sum = rs.getInt("suma");
                        dtos.add(new ManagerDto(id, firstname, lastname, sum));
                    }
                    return dtos;
                }
        );
    }

    public List<ObservableDto> fifthQuery() {
        return jdbcTemplate.query(FIFTH_QUERY,
                rs -> {
                    List<ObservableDto> dtos = new ArrayList<>();
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String categoryName = rs.getString("category_name");
                        int quantitySum = rs.getInt("quantity_sum");
                        dtos.add(new ProductDto(id, name, categoryName, quantitySum));
                    }
                    return dtos;
                }
        );
    }
}
