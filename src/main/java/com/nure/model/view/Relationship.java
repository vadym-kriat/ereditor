package com.nure.model.view;

import com.nure.model.schema.table.Column;
import com.nure.model.schema.table.ForeignKey;
import com.sun.prism.paint.Paint;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.List;

public class Relationship {
    private Parent root;

    private VisualEntity startVE;
    private VisualEntity endVE;

    private Line line;
    private Circle circle;

    public Relationship(Parent root, VisualEntity startVE, VisualEntity endVE) {
        this.root = root;
        this.startVE = startVE;
        this.endVE = endVE;

        this.line = new Line();
        this.circle = new Circle(7);
        line.getStyleClass().add("line");
        circle.getStyleClass().add("circle");

        bindPositions();
    }

    private void bindPositions() {
        circle.centerXProperty().bind(line.startXProperty());
        circle.centerYProperty().bind(line.startYProperty());

        System.out.println(circle.getCenterX());

        int index = getFKIndex();
        double transitionY = (index + 1) * VisualEntity.ROW_HEIGHT + VisualEntity.ROW_HEIGHT / 3;

        setSide();
        setCircleStyle(index);

        line.startYProperty().bind(startVE.layoutYProperty().add(transitionY));
        line.endYProperty().bind(endVE.layoutYProperty().add(VisualEntity.ROW_HEIGHT / 2));
    }

    private void setCircleStyle(int fkIndex) {
        boolean isNotNull = startVE.getEntity().getAllColumns().get(fkIndex).isNotNull();
        circle.setFill(isNotNull ? Color.web("#993939") : Color.WHITE);
    }

    private int getFKIndex() {
        List<Column> columns = startVE.getEntity().getAllColumns();
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i) instanceof ForeignKey &&
                    ((ForeignKey) columns.get(i)).getReferencedTableName().equals(endVE.getEntity().getName())) {
                return i;
            }
        }
        throw new NullPointerException("Foreign key for referenced table not found.");
    }

    public void delete() {
        //todo delete relas,daopafoaoni
    }

    public void draw() {
        ((AnchorPane)root).getChildren().addAll(line, circle);
    }

    public void setSide() {
        if (startVE.getLayoutX() < endVE.getLayoutX()) {
            line.startXProperty().bind(startVE.layoutXProperty().add(startVE.getWidth()));
            line.endXProperty().bind(endVE.layoutXProperty());
        } else {
            line.startXProperty().bind(startVE.layoutXProperty());
            line.endXProperty().bind(endVE.layoutXProperty().add(endVE.getWidth()));
        }
    }
}
