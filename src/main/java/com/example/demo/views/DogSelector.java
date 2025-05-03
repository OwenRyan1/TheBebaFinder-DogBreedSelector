package com.example.demo.views;

import com.example.demo.models.Dog;
import com.example.demo.service.APIService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import java.io.IOException;
import java.util.List;

@Route("dog-selector")
public class DogSelector extends VerticalLayout {
    private final APIService apiService;

    public DogSelector(APIService apiService) {
        this.apiService = apiService;

        // styling
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // creating text area for user to send message
        TextArea userInput = new TextArea("What are you looking for in a dog breed?");
        userInput.setPlaceholder("Type your message here...");
        userInput.setMaxLength(100);
        userInput.setWidth("400px");
        userInput.setClearButtonVisible(true);

        userInput.setHelperText("Max 100 characters");
        userInput.getStyle()
                .set("--vaadin-input-field-label-font-size", "20px")
                .set("--vaadin-input-field-value-font-size", "14px")
                .set("--vaadin-input-field-helper-text-font-size", "14px");

        Button button = new Button("Send");

        // on send generate gpt message and send
        button.addClickListener(event -> {
            String inputText = userInput.getValue().trim();

            //ensure user input was provided
            if (inputText.isEmpty()) {
                Notification.show("Please enter a message.");
                return;
            }

            // format message for chatgpt to respond too (still playing around with for token efficiency)
            String message = null;
            try {
                message = "Pick dog breed. You may only select one of the" +
                        "following" + allDogBreeds() + "User wants: "
                        + inputText + ". CRUCIAL: return one of the exact spelling names i gave." +
                        "format answer as breeedname : reasoing. Ex) : Lab : reasoning .... ALSO, If they give no " +
                        "info pick a random and explain why its good";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // send message and show response to user
            try {
                String response = apiService.sendMessageToGPT(message);
                System.out.println("Response from GPT: " + response);
                showPopUp(response);
            } catch (IOException e) {
                Notification.show("Error: " + e.getMessage());
            }
        });

        // setting up the ui
        VerticalLayout userInputLayout = new VerticalLayout(userInput, button);
        userInputLayout.setAlignItems(Alignment.CENTER);

        // images
        Image leftImage = new Image("photos/dogWithAI.jpeg", "Dog 1");
        Image rightImage = new Image("photos/dogWithAI2.png", "Dog 2");
        DashboardView.styleImage(leftImage);
        DashboardView.styleImage(rightImage);

        // images and title together
        HorizontalLayout layout = new HorizontalLayout(leftImage, userInputLayout, rightImage);
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        add(layout);
    }

    /**
     * Displays a popup dialog with a message and navigation options based on AI response
     *<p>
     * Shows a modal dialog containing the provided message, a "Close" button to dismiss it,
     * and a button that navigates to the breed details page if the message contains a breed name.
     *
     * @param message The message to display in the popup. Expected format: "BreedName: additional info".
     */
    public void showPopUp(String message) {
        Dialog dialog = new Dialog();

        // Create content with the message
        Div content = new Div();
        content.setText(message);
        content.getStyle().set("padding", "20px");

        // Create the close button
        Button closeButton = new Button("Close", event -> dialog.close());

        // get breed name
        String[] parts = message.split(":");
        String breedName = parts[0].trim();

        // button to nav to breed info
        Button breedButton = new Button(breedName, event -> {
            dialog.close();
            UI.getCurrent().navigate("dog-breed-details/" + breedName);
        });

        // button locations
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        buttonLayout.add(closeButton, breedButton);

        dialog.add(content, buttonLayout);
        dialog.open();
    }

    /**
     * Retrieves a comma-separated list of all dog breed names, to send to chatgpt api
     *<p>
     * This method fetches breed information from the Dog API via the API service,
     * extracts the breed names, and returns them as a single comma-separated string.
     *
     * @return A string containing all dog breed names, separated by commas.
     * @throws IOException If there is an issue fetching the breed information from the API.
     */
    public String allDogBreeds() throws IOException {
        List<Dog> dogs = apiService.fetchBreedsInformation();

        StringBuilder stringBuilder = new StringBuilder();
        for (Dog dog : dogs) {
            stringBuilder.append(dog.getBreed()).append(", ");
        }

        //remove spaces and comma
        if (stringBuilder.length() > 2) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }

        return stringBuilder.toString();
    }

}

