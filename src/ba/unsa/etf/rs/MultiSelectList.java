package ba.unsa.etf.rs;

import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class MultiSelectList<T> extends ListView<T> {
    public boolean isMultiSelect() {
        return getSelectionModel().getSelectionMode() == SelectionMode.MULTIPLE;
    }

    public void setMultiSelect(boolean multiSelect) {
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}