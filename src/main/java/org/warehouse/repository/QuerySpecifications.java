package org.warehouse.repository;

import org.springframework.data.jpa.domain.Specification;
import org.warehouse.model.Category;
import org.warehouse.model.Category_;
import org.warehouse.model.Product;
import org.warehouse.model.Product_;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public class QuerySpecifications {

    public static Specification<Product> isCategoryId(Integer id) {
        return (root, query, cb) -> {
            Join<Product, Category> categoryJoin = getOrCreateJoin(root, Product_.category);
            return cb.equal(categoryJoin.get(Category_.id), id);
        };
    }

    public static <T_Source, T_Target> Join<T_Source, T_Target> getOrCreateJoin(final Root<T_Source> root,
                                                                                final SingularAttribute<? super T_Source, T_Target> attribute) {
        return getOrCreateJoin(root, attribute, JoinType.INNER);
    }

    public static <T_Source, T_Target> Join<T_Source, T_Target> getOrCreateJoin(final Root<T_Source> root,
                                                                                final SingularAttribute<? super T_Source, T_Target> attribute,
                                                                                final JoinType joinType) {
        return root.getJoins()
                .stream()
                .filter(join -> join.getAttribute().equals(attribute) && join.getJoinType().equals(joinType))
                .findFirst()
                .map(join -> {
                    @SuppressWarnings("unchecked")
                    Join<T_Source, T_Target> unchecked = (Join<T_Source, T_Target>) join;
                    return unchecked;
                })
                .orElseGet(() -> root.join(attribute, joinType));
    }
}
