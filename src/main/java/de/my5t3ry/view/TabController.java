package de.my5t3ry.view;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TabController implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(TabController.class);

    @FXML
    private TabPane mainTab;

    @FXML
    private Tab projectsTab;

    @FXML
    private Tab configurationTab;


   

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        mainTab.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable,
                                                                        Tab oldValue, Tab newValue) -> {
            if (newValue == projectsTab) {
                System.out.println("- 2.Tab bar -");
                System.out.println("xxx_tab2bar_xxxController=" + projectsTab); //if =null => inject problem
                //                xxx_tab2bar_xxxController.handleTab2ButtonBar();
            } else if (newValue == configurationTab) {
                System.out.println("- 1.Tab foo -");
                System.out.println("xxx_tab1foo_xxxController=" + configurationTab); //if =null => inject problem
                // xxx_tab1foo_xxxController.handleTab1ButtonFoo();
            } else {
                System.out.println("- another Tab -");
            }
        });

    }
}
