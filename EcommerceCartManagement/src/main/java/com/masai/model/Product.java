package com.masai.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	@NotNull
	@Size(min = 3, max = 20, message = "Product Name should be in between 3 to 20 lenght")
	private String productName;
	
	@NotNull
	@Size(min = 10, max = 200, message = "Description should be in between 10 to 200 lenght")
	private String description;
	
	@NotNull
	@Size(min = 3, max = 20, message = "Brand Name should be in between 3 to 20 lenght")
	private String brand;
	
	@NotNull
	private Double markPrice;
	
	@NotNull
	private Double salePrice;
	
	@NotNull
	private Integer availableQuantity;
	
	@NotNull
	@ElementCollection
	private List<String> CategoryList = new ArrayList<String>();

	@Override
	public int hashCode() {
		return Objects.hash(productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productId, other.productId);
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", description=" + description
				+ ", brand=" + brand + ", markPrice=" + markPrice + ", salePrice=" + salePrice + ", availableQuantity="
				+ availableQuantity + ", CategoryList=" + CategoryList + "]";
	}
	
	

}
