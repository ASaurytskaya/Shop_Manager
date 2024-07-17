package by.fin.shop_manager.dao.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column
    private String model;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false, precision = 2)
    private double price;

    @Column(nullable = false)
    private String weight;

    @Column
    private Double rating;

    @Column
    private String warranty;

    @Column(nullable = false)
    private int availableUnits;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images;

    @Column(nullable = false)
    private String features;

    private Product() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }

    public void setAvailableUnits(int availableUnits) {
        this.availableUnits = availableUnits;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public void addImage(ProductImage image) {
        images.add(image);
        image.setProduct(this);
    }

    public void removeImage(ProductImage image) {
        images.remove(image);
        image.setProduct(null);
    }

    @Transient
    public List<String> getFeatures() {
        return Arrays.stream(this.features.split(";"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @Transient
    public void setFeatures(List<String> features) {
        this.features = String.join(";", features);
    }

    @Transient
    public void generateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" ").append(brand);
        if (model != null && !model.isEmpty()) {
            sb.append(" ").append(model);
        }
        sb.append(", цвет: ").append(color).append(". ");
        sb.append("Особенности: ").append(features.replace(";", ", ")).append(".");
        this.description = sb.toString();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String brand;
        private String model;
        private int availableUnits;
        private String weight;
        private Double rating;
        private String category;
        private String description;
        private String color;
        private double price;
        private List<ProductImage> images;
        private String features;
        private String warranty;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setAvailableUnits(int availableUnits) {
            this.availableUnits = availableUnits;
            return this;
        }

        public Builder setWeight(String weight) {
            this.weight = weight;
            return this;
        }

        public Builder setRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setImages(List<ProductImage> images) {
            this.images = images;
            return this;
        }

        public Builder setFeatures(String features) {
            this.features = features;
            return this;
        }

        public Builder setWarranty(String warranty) {
            this.warranty = warranty;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.id = this.id;
            product.name = this.name;
            product.brand = this.brand;
            product.model = this.model;
            product.availableUnits = this.availableUnits;
            product.weight = this.weight;
            product.rating = this.rating;
            product.category = this.category;
            product.description = this.description;
            product.color = this.color;
            product.price = this.price;
            product.images = this.images;
            product.features = this.features;
            product.warranty = this.warranty;
            product.generateDescription();
            return product;
        }
    }

}
