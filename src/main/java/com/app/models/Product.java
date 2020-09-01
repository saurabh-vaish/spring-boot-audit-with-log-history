package com.app.models;

import com.app.dto.ProductDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Audited
@Table(name = "product")
public class Product extends VersionEntityManager{

    @Column(name = "product_id",unique = true)
    private int productId;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity_per_unit")
    private int quantityPerUnit;

    @Column(name = "unit_price")
    private double unitPrice;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    public Product(ProductDTO productDTO) {
        this.name = productDTO.getName();
        this.description = productDTO.getDescription();
        this.quantityPerUnit = productDTO.getQuantityPerUnit();
        this.unitPrice = productDTO.getUnitPrice();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("productId", productId)
                .append("description", description)
                .append("name", name)
                .append("quantityPerUnit", quantityPerUnit)
                .append("unitPrice", unitPrice)
                .append("orderDetails", orderDetails)
                .toString();
    }

}