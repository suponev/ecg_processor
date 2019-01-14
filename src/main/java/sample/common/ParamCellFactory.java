package sample.common;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ParamCellFactory implements Callback<ListView<String>, ListCell<String>> {
    @Override
    public ListCell<String> call(ListView<String> param) {
        return new ListCell<String>() {
            @Override
            protected void updateItem(String myBean, boolean b) {
                super.updateItem(myBean, b);
                if (!b) {
                    HBox box = new HBox();
                    box.setSpacing(50);
                    box.getChildren().add(new Label(myBean));
                    box.getChildren().add(new TextField());
                    setGraphic(box);
                } else {
                    setGraphic(null);
                }
            }
        };
    }
}
