package com.example.demo.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("about-page")
public class AboutView extends VerticalLayout {

    public AboutView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        Div aboutText = new Div();
        aboutText.setText("Welcome to The Beba Finder — your go-to guide for everything dog breed related. " +
                "Whether you're a first-time dog parent or a lifelong canine enthusiast, our app helps you " +
                "discover detailed information on all recognized dog breeds, from temperament and size to " +
                "life span and original breeding purpose. Not sure which breed is the right fit for your lifestyle? " +
                "Our built-in ChatGPT assistant can help recommend a breed tailored to your personality, space, " +
                "and activity level. This app was inspired by my own dog, Sebastian (affectionately known as Beba), " +
                "whose joyful spirit and loyal heart sparked the idea for helping others find their perfect pup " +
                "match. With The Beba Finder, finding the right dog isn't just about research — it’s about connection.");

        // styling
        aboutText.getStyle()
                .set("max-width", "700px")
                .set("text-align", "center")
                .set("font-size", "18px")
                .set("line-height", "1.6")
                .set("color", "#333");

        // images
        Image leftImage = new Image("photos/bebaAboutPage1.jpeg", "Dog 1");
        Image rightImage = new Image("photos/bebaAboutPage2.jpeg", "Dog 2");
        DashboardView.styleImage(leftImage);
        DashboardView.styleImage(rightImage);

        // images and title together
        HorizontalLayout layout = new HorizontalLayout(leftImage, aboutText, rightImage);
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);

        add(layout);
    }
}
