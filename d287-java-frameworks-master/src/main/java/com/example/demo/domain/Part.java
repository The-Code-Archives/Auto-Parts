package com.example.demo.domain;

import com.example.demo.validators.EnufPartsValidator;
import com.example.demo.validators.InventoryValidator;
import com.example.demo.validators.ValidDeletePart;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 *
 *
 */
@Entity
@ValidDeletePart
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="part_type",discriminatorType = DiscriminatorType.INTEGER)
@Table(name="Parts")
public abstract class Part implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    @Min(value = 0, message = "Price value must be positive")
    double price;
    @Min(value = 0, message = "Inventory value must be positive")
    int inv;

    //Newly added fields
    int maxInv;
    @Min(value = 0, message ="Inventory minimum value must be greater than 0")
    int minInv;

    @ManyToMany
    @JoinTable(name="product_part", joinColumns = @JoinColumn(name="part_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    Set<Product> products= new HashSet<>();

    public Part() {
    }

    public Part(String name, double price, int inv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
    }
    //Added constructor here
    public Part(int maxInv, int minInv) {
        this.maxInv = maxInv;
        this.minInv = minInv;
    }

    public Part(long id, String name, double price, int inv) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inv = inv;
    }



  /* ********  This was code used for Task G before I replaced it with the invValidation() method for Task H************
    public boolean invCheck() {
        if(inv >= minInv && inv <= maxInv) {
            return true;
        } else {
            return false;
        }
    }
   */
    public InventoryValidator invValidation() {
        if(inv < minInv) {
            return InventoryValidator.BELOW_MINIMUM;
        } else if(inv > maxInv) {
            return InventoryValidator.ABOVE_MAXIMUM;
        } else {
            return InventoryValidator.VALID;
        }
    }



    //New getter and setter statements here
    public int getMinInv() { return minInv; }
    public int getMaxInv() { return maxInv; }
    public void setMaxInv(int maxInv) {this.maxInv = maxInv;}
    public void setMinInv(int minInv) {this.minInv = minInv;}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String toString(){
        return this.name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        return id == part.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}