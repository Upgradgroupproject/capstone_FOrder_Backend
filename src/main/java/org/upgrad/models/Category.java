package org.upgrad.models;
@Entity
@Table(name = "Category")
public class Category {
    @Column(name = "Categoryname")
    private String CategoryName;
}
public Category(String CategoryName) {
    this.CategoryName = CategoryName;
  
}

public Integer getAllCategories() {
    return CategoryName;
}

public void setAllCategories() {
    this.CategoryName = CategoryName;
}

public String getCategoryByName() {
    return RestaurantName;
}

public void setCategoryByName(String CategoryName) {
    this.CategoryName = CategoryName;
}