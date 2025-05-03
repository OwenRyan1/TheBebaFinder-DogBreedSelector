package com.example.demo.views;

import com.example.demo.models.Dog;
import com.example.demo.service.APIService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.component.html.Image;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Route("dog-breed-details/")
@PageTitle("Dog Breed Details")
public class DogBreedDetailedView extends VerticalLayout implements HasUrlParameter<String> {

    private final APIService apiService;

    public DogBreedDetailedView(APIService apiService) {
        this.apiService = apiService;
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    /**
     * Sets the parameters for displaying dog breed information based on the breed name.
     * <p>
     * This method retrieves all dog breed data from the API, searches for the breed matching the provided breed name,
     * and displays the breed's information if found. If the breed information is unavailable, a message is displayed to
     * inform the user. The breed details such as image, height, weight, breed group, and more are displayed dynamically.
     *
     * @param event The {@link BeforeEvent} event that triggered this method (not used in this implementation).
     * @param breedName The name of the dog breed for which information is being displayed.
     */
    @Override
    public void setParameter(BeforeEvent event, String breedName) {
        List<Dog> dogs = null;

        // get all dog breeds information
        try {
            dogs = apiService.fetchBreedsInformation();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // find users selected breed
        Optional<Dog> breed = dogs.stream()
                .filter(d -> d.getBreed().equals(breedName))
                .findFirst();

        // ensure breed has information and show to user
        if (breed.isEmpty()) {
            add(new Div("Dog Information is not available at this time, try again later!"));
        } else {
            breed.ifPresent(b -> {

                if (b.getImage() != null && b.getImage().getImage() != null) {
                    Image breedImage = new Image(b.getImage().getImage(), "Dog Breed Image");
                    breedImage.setWidth("700px");
                    breedImage.setHeight("500px");
                    breedImage.getStyle().set("object-fit", "cover");
                    add(breedImage);
                }

                // Add text fields if not null
                addIfNotNull("Breed Name", b.getBreed());
                addIfNotNull("Height", b.getHeight() != null ? b.getHeight().getHeight() + " inches" : null);
                addIfNotNull("Weight", b.getWeight() != null ? b.getWeight().getWeight() + " lbs" : null);
                addIfNotNull("Breed Group", b.getBreedGroup());
                addIfNotNull("Bred For", b.getBredFor());
                addIfNotNull("History", b.getHistory());
                addIfNotNull("Description", b.getDescription());
                addIfNotNull("Life Span", b.getLifeSpan());
                addIfNotNull("Origin", b.getOrigin());
                addIfNotNull("Temperament", b.getTemperament());
            });
        }
    }

    /**
     * Adds a label and its corresponding value to the UI if the value is not null or empty.
     *<p>
     * The label is displayed in bold, followed by the value in regular text, inside a new {@link Div} element.
     * This method is typically used to dynamically display information on the UI.
     *
     * @param label The label to be displayed in bold.
     * @param value The value associated with the label to be displayed.
     */
    private void addIfNotNull(String label, String value) {
        if (value != null && !value.trim().isEmpty()) {
            Div div = new Div();

            // make bold
            Span labelSpan = new Span(label + ": ");
            labelSpan.getStyle().set("font-weight", "bold");  // Bold label text

            // add value
            Span valueSpan = new Span(value);
            div.add(labelSpan, valueSpan);

            add(div);
        }
    }
}
