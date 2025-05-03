package com.example.demo.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Layout;
import com.example.demo.service.APIService;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Layout  // marks the class as the main layout
public class MainLayout extends AppLayout {

    @Autowired
    public MainLayout(APIService apiService) {
        H3 title = new H3("The Beba Finder");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)").set("margin", "0")
                .set("position", "absolute");

        HorizontalLayout navigation = getNavigation();
        navigation.getElement();
        addToNavbar(title, navigation);
    }

    /**
     * Creates and returns a horizontal navigation bar with links. (from vaadin)
     *<p>
     * This method sets up a styled {@link HorizontalLayout} containing navigation links
     * to various sections of the application, such as Dashboard, Dog Breeds, Dog Selector, and About.
     *
     * @return A {@link HorizontalLayout} containing the navigation links.
     */
    private HorizontalLayout getNavigation() {
        HorizontalLayout navigation = new HorizontalLayout();
        navigation.addClassNames(LumoUtility.JustifyContent.CENTER,
                LumoUtility.Gap.SMALL, LumoUtility.Height.MEDIUM,
                LumoUtility.Width.FULL);
        navigation.add(createLink("Dashboard"), createLink("Dog Breeds"), createLink("Dog Selector"), createLink("About"));
        return navigation;
    }

    /**
     * Creates a styled {@link RouterLink} based on the provided view name.
     *<p>
     * This method maps specific view names (e.g., "Dog Breeds", "About") to their corresponding route views
     * and returns a {@link RouterLink} styled with layout and text utilities.
     *
     * @param viewName The name of the view to link to.
     * @return A {@link RouterLink} component configured with the appropriate route and styles.
     */
    private RouterLink createLink(String viewName) {
        RouterLink link = new RouterLink();
        link.add(viewName);

        //determine route
        if (Objects.equals(viewName, "Dog Breeds")){
            link.setRoute(DogBreedView.class);
        }
        else if (Objects.equals(viewName, "About")){
            link.setRoute(AboutView.class);
        }
        else if (Objects.equals(viewName, "Dashboard")){
            link.setRoute(DashboardView.class);
        }
        else if (Objects.equals(viewName, "Dog Selector")){
            link.setRoute(DogSelector.class);
        }

        //styling
        link.addClassNames(LumoUtility.Display.FLEX,
                LumoUtility.AlignItems.CENTER,
                LumoUtility.Padding.Horizontal.MEDIUM,
                LumoUtility.TextColor.SECONDARY, LumoUtility.FontWeight.MEDIUM);
        link.getStyle().set("text-decoration", "none");

        return link;
    }
}
