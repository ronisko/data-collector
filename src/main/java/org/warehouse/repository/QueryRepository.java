package org.warehouse.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.warehouse.dto.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryRepository {

    private static final String FIRST_QUERY = "select * from shop_tab sh where sh.id = (select sl.shop_id as shopId " +
            "from sales_tab sl group by sl.transaction_id, sl.shop_id order by avg(sl.revenue * sl.quantity) desc limit 1);";

    private static final String SECOND_QUERY = "select shop_name, category_name, sale_value as sale from (select " +
            "sh.name as shop_name, p.category_id, c.name as category_name, sum(s.sales) as sale_value, row_number() " +
            "over (partition by category_name order by sale_value desc) as rn from sales_tab s join shop_tab sh on " +
            "sh.id = s.shop_id join product_tab p on s.product_id = p.id join category_tab c on p.category_id = " +
            "c.id group by (sh.name, p.category_id, c.name)) as sub where sub.rn = 1;";

    private static final String THIRD_QUERY = "select mt.id, mt.firstname, mt.lastname, sum(st.revenue * st.quantity) " +
            "as suma from manager_tab mt join shop_tab sh on mt.id = sh.manager_id join sales_tab st on sh.id = st.shop_id " +
            "group by mt.id, mt.firstname, mt.lastname, st.shop_id order by suma desc limit 10;";

    private static final String FOURTH_QUERY = "select h1.shop_name, saleValue_h1, saleValue_h2 from (select sh.id as " +
            "shop_id, sh.name as shop_name, sum(s.sales) as saleValue_h1 from sales_tab s join transaction_tab t on " +
            "s.transaction_id = t.id join shop_tab sh on s.shop_id = sh.id where date_part('day', t.date) < 16 group " +
            "by sh.id, sh.name) as h1 join (select sh.id as shop_id, sh.name as shop_name, sum(s.sales) as saleValue_h2 " +
            "from sales_tab s join transaction_tab t on s.transaction_id = t.id join shop_tab sh on s.shop_id = sh.id " +
            "where date_part('day', t.date) > 15 group by sh.id, sh.name) as h2 on h1.shop_id = h2.shop_id;";

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

    public List<ObservableDto> secondQuery() {
        return jdbcTemplate.query(SECOND_QUERY,
                rs -> {
                    List<ObservableDto> dtos = new ArrayList<>();
                    while (rs.next()) {
                        String name = rs.getString("shop_name");
                        String categoryName = rs.getString("category_name");
                        double sale = rs.getDouble("sale");
                        dtos.add(new CategoryDto(name, categoryName, sale));
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

    public List<ObservableDto> fourthQuery() {
        return jdbcTemplate.query(FOURTH_QUERY,
                rs -> {
                    List<ObservableDto> dtos = new ArrayList<>();
                    while (rs.next()) {
                        String name = rs.getString("shop_name");
                        double firstHalf = rs.getDouble("salevalue_h1");
                        double secondHalf = rs.getDouble("salevalue_h2");
                        dtos.add(new HalfSalesDto(name, firstHalf, secondHalf));
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
