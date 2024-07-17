package by.fin.shop_manager.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductCreateDto {

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private String brand;

    @JsonProperty(required = true)
    private String category;

    @JsonProperty(required = true)
    private String color;

    @JsonProperty(required = true)
    private double price;

    @JsonProperty(required = true)
    private String weight;

    @JsonProperty(value = "available_units", required = true)
    private int availableUnits;

    private String model;

    private Double rating;

    private String warranty;

    @JsonProperty(value = "special_features", required = true)
    private List<String> features;

    public ProductCreateDto() {
    }

    private ProductCreateDto(Builder builder) {
        this.name = builder.name;
        this.brand = builder.brand;
        this.category = builder.category;
        this.color = builder.color;
        this.price = builder.price;
        this.weight = builder.weight;
        this.availableUnits = builder.availableUnits;
        this.model = builder.model;
        this.rating = builder.rating;
        this.warranty = builder.warranty;
        this.features = builder.features;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
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

    public int getAvailableUnits() {
        return availableUnits;
    }

    public String getModel() {
        return model;
    }

    public Double getRating() {
        return rating;
    }

    public String getWarranty() {
        return warranty;
    }

    public List<String> getFeatures() {
        return features;
    }

    public static class Builder {
        private String name;
        private String brand;
        private String category;
        private String color;
        private double price;
        private String weight;
        private int availableUnits;
        private String model;    // Optional
        private Double rating;    // Optional
        private String warranty;   // Optional
        private List<String> features;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
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

        public Builder setAvailableUnits(int availableUnits) {
            this.availableUnits = availableUnits;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
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

        public Builder setFeatures(List<String> features) {
            this.features = features;
            return this;
        }

        public ProductCreateDto build() {
            return new ProductCreateDto(this);
        }
    }
}
