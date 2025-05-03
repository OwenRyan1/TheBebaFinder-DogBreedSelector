package com.example.demo.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// All models create JSON2CSharp (https://json2csharp.com/code-converters/json-to-pojo)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dog{
    public Weight weight;
    public Height height;
    public Image image;
    public int id;
    public String name;
    public String bred_for;
    public String breed_group;
    public String life_span;
    public String temperament;
    public String origin;
    public String reference_image_id;
    public String country_code;
    public String description;
    public String history;

    // getters
    public String getBreed() {
        return name;
    }
    public int getId() {
        return id;
    }
    public Weight getWeight() {
        return weight;
    }
    public Height getHeight() {
        return height;
    }
    public Image getImage() {
        return image;
    }
    public String getBredFor() {
        return bred_for;
    }
    public String getBreedGroup() {
        return breed_group;
    }
    public String getLifeSpan() {
        return life_span;
    }
    public String getTemperament() {
        return temperament;
    }
    public String getOrigin() {
        return origin;
    }
    public String getReferenceImageId() {
        return reference_image_id;
    }
    public String getCountryCode() {
        return country_code;
    }
    public String getDescription() {
        return description;
    }
    public String getHistory() {
        return history;
    }
}






