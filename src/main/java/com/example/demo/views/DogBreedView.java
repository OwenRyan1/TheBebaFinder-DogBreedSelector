package com.example.demo.views;

import com.example.demo.models.Dog;
import com.example.demo.service.APIService;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;
import com.vaadin.flow.component.grid.Grid;


@Route("dog-breeds")
public class DogBreedView extends VerticalLayout {

    private final APIService apiService;

    public DogBreedView(APIService apiService) {
        this.apiService = apiService;

        // styling
        setSizeFull();
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        // create grid of dog names
        Grid<Dog> grid = new Grid<>(Dog.class, false);
        grid.addColumn(Dog::getBreed).setHeader("Dog Breeds");
        List<Dog> dogs = fetchDataFromApi();
        grid.setItems(dogs);

        // make each bread clickable
        grid.addItemClickListener(event -> {
            Dog clickedBreed = event.getItem();

            getUI().ifPresent(ui ->
                    ui.navigate("dog-breed-details/" + clickedBreed.getBreed())
            );
        });
        add(grid);
    }

    /**
     * Fetches dog breed data from the API service.
     * <p>
     * Attempts to retrieve a list of dog breeds using the API service. If an exception occurs,
     * a notification is shown to the user and an empty list is returned.
     *
     * @return A list of {@link Dog} objects, or an empty list if an error occurs.
     */
    private List<Dog> fetchDataFromApi() {
        try {
            return apiService.fetchBreedsInformation();
        } catch (Exception e) {
            Notification.show("Error fetching or processing data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
