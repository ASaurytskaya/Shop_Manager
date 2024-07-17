package by.fin.shop_manager.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class ProductDto {

    private Long id;

    private String name;

    private String brand;

    private String model;

    private String category;

    private String description;

    private String color;

    private double price;

    private String weight;

    private Double rating;

    private String warranty;

    @JsonProperty("available_units")
    private int availableUnits;

    private List<ImageDto> images;

    @JsonProperty("special_features")
    private List<String> features;

    public ProductDto() {
    }

    private ProductDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.brand = builder.brand;
        this.model = builder.model;
        this.category = builder.category;
        this.description = builder.description;
        this.color = builder.color;
        this.price = builder.price;
        this.weight = builder.weight;
        this.rating = builder.rating;
        this.warranty = builder.warranty;
        this.availableUnits = builder.availableUnits;
        this.images = builder.images;
        this.features = builder.features;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public String getWeight() {
        return weight;
    }

    public Double getRating() {
        return rating;
    }

    public String getWarranty() {
        return warranty;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public List<String> getFeatures() {
        return features;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDto that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getBrand(), that.getBrand()) && Objects.equals(getModel(), that.getModel()) && Objects.equals(getCategory(), that.getCategory()) && Objects.equals(getColor(), that.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBrand(), getCategory(), getColor());
    }

    public static class Builder {
        private Long id;
        private String name;
        private String brand;
        private String model;
        private String category;
        private String description;
        private String color;
        private double price;
        private String weight;
        private Double rating;
        private String warranty;
        private int availableUnits;
        private List<ImageDto> images;
        private List<String> features;

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

        public Builder setWeight(String weight) {
            this.weight = weight;
            return this;
        }

        public Builder setRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Builder setWarranty(String warranty) {
            this.warranty = warranty;
            return this;
        }

        public Builder setAvailableUnits(int availableUnits) {
            this.availableUnits = availableUnits;
            return this;
        }

        public Builder setImages(List<ImageDto> images) {
            this.images = images;
            return this;
        }

        public Builder setFeatures(List<String> features) {
            this.features = features;
            return this;
        }

        public ProductDto build() {
            return new ProductDto(this);
        }
    }
}
