package com.example.demo.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    public DashboardView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H1 title = new H1("Welcome to The Beba Finder");
        title.getStyle().set("font-size", "40px");

        // add images
        Image leftImage = new Image("photos/bebaDashboard1.jpeg", "Dog 1");
        Image rightImage = new Image("photos/bebaDashboard2.jpeg", "Dog 2");
        styleImage(leftImage);
        styleImage(rightImage);

        // images and title together
        HorizontalLayout layout = new HorizontalLayout(leftImage, title, rightImage);
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        add(layout);
    }

    // crop and format images
    public static void styleImage(Image image) {
        image.setWidth("400px");
        image.setHeight("250px");
        image.getStyle().set("object-fit", "cover");
    }
}
