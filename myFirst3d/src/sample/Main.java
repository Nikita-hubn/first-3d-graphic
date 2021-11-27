package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;



public class Main extends Application {

        public static final int WIDTH = 1500;
        public static final int HEIGHT = 720;
        public static final int DELTA = 10;
        public static final int DEFAULT_ANGLE = 30;
    @Override
    public void start(Stage primaryStage) throws Exception{
            MeshView octahedron = createOctahedron(75);
            octahedron.translateXProperty().set(WIDTH/5);
            octahedron.translateYProperty().set(HEIGHT/2);
            octahedron.getTransforms().add(new Rotate(DEFAULT_ANGLE, Rotate.X_AXIS));
            octahedron.getTransforms().add(new Rotate(DEFAULT_ANGLE, Rotate.Y_AXIS));
            PhongMaterial material0 = new PhongMaterial();
            material0.setDiffuseColor(Color.YELLOW);
            octahedron.setMaterial(material0);



            Box box = new Box(100, 100, 100);
            box.translateXProperty().set(WIDTH/5*3);
            box.translateYProperty().set(HEIGHT/2);
            box.getTransforms().add(new Rotate(DEFAULT_ANGLE, Rotate.X_AXIS));
            box.getTransforms().add(new Rotate(DEFAULT_ANGLE, Rotate.Y_AXIS));
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseColor(Color.RED);
            box.setMaterial(material);

            MeshView tetrahedron = createTetrahedron(75);
            tetrahedron.translateXProperty().set(WIDTH/5*4);
            tetrahedron.translateYProperty().set(HEIGHT/2);
            tetrahedron.getTransforms().add(new Rotate(DEFAULT_ANGLE, Rotate.X_AXIS));
            tetrahedron.getTransforms().add(new Rotate(DEFAULT_ANGLE, Rotate.Y_AXIS));
            PhongMaterial material2 = new PhongMaterial();
            material2.setDiffuseColor(Color.GREEN);
            tetrahedron.setMaterial(material2);

            Group group = new Group();
            group.getChildren().add(octahedron);

            group.getChildren().add(box);
            group.getChildren().add(tetrahedron);

            Scene scene = new Scene(group, WIDTH, HEIGHT);
            scene.setFill(Color.BLACK);

            scene.setOnKeyPressed(e -> {
                    switch (e.getCode()){
                            case A:
                                    tetrahedron.translateXProperty().set(tetrahedron.getTranslateX() - DELTA);
                                    break;
                            case D:
                                    tetrahedron.translateXProperty().set(tetrahedron.getTranslateX() + DELTA);
                                    break;
                            case W:
                                    tetrahedron.translateYProperty().set(tetrahedron.getTranslateY() - DELTA);
                                    break;
                            case S:
                                    tetrahedron.translateYProperty().set(tetrahedron.getTranslateY() + DELTA);
                                    break;
                            case J:
                                    tetrahedron.getTransforms().add(new Rotate(DELTA, Rotate.Y_AXIS));
                                    break;
                            case L:
                                    tetrahedron.getTransforms().add(new Rotate(-DELTA, Rotate.Y_AXIS));
                                    break;
                            case I:
                                    tetrahedron.getTransforms().add(new Rotate(-DELTA, Rotate.X_AXIS));
                                    break;
                            case K:
                                    tetrahedron.getTransforms().add(new Rotate(DELTA, Rotate.X_AXIS));
                                    break;
                    }
            });

            primaryStage.setTitle("CG_Task3");
            primaryStage.setScene(scene);
            primaryStage.show();
    }


        public static void main(String[] args) {
                launch(args);
        }

        public static MeshView createOctahedron(int length){
                TriangleMesh trianglemesh = new TriangleMesh();
                trianglemesh.getPoints().addAll(
                        length,    0.0f,    0.0f,
                        -length,    0.0f,    0.0f,
                        0.0f,  length,    0.0f,
                        0.0f, -length,    0.0f,
                        0.0f,    0.0f,  length,
                        0.0f,    0.0f, -length
                );
                trianglemesh.getTexCoords().addAll(
                        0.50f, 1.00f,
                        0.75f, (float) (1.0-Math.sqrt(3.0)/4.0f),
                        0.25f, (float) (1.0-Math.sqrt(3.0)/4.0f),
                        1.00f, 1.00f,
                        0.50f, (float) (1.0-Math.sqrt(3.0)/2.0f),
                        0.00f, 1.00f
                );
                trianglemesh.getFaces().addAll(
                        4, 0, 0, 1, 2, 2,
                        4, 1, 2, 0, 1, 3,
                        4, 2, 1, 1, 3, 4,
                        4, 0, 3, 2, 0, 5,
                        5, 0, 2, 2, 0, 5,
                        5, 2, 1, 1, 2, 4,
                        5, 1, 3, 0, 1, 3,
                        5, 0, 0, 1, 3, 2
                );
                trianglemesh.getFaceSmoothingGroups().addAll(
                        0, 2, 4, 8, 16, 32, 64, 128
                );
                MeshView meshview = new MeshView(trianglemesh);
                return meshview;
        }

        public static MeshView createTetrahedron(int length){
                float p0 = length*1.0f;
                float p1 = 0.0f;
                float p2 = (float) (length*Math.sqrt(2.0)/2.0f);
                TriangleMesh trianglemesh = new TriangleMesh();
                trianglemesh.getPoints().addAll(//точки
                        p0, p1, -p2,
                        -p0, p1, -p2,
                        p1, p0, p2,
                        p1, -p0, p2
                );
                trianglemesh.getTexCoords().addAll(//координаты вершин
                        0.50f, 1.00f,
                        0.75f, (float) (1.0-Math.sqrt(3.0)/4.0f),
                        0.25f, (float) (1.0-Math.sqrt(3.0)/4.0f),
                        1.00f, 1.00f,
                        0.50f, (float) (1.0-Math.sqrt(3.0)/2.0f),
                        0.00f, 1.00f
                );
                trianglemesh.getFaces().addAll(//грани
                        0, 0, 1, 1, 2, 2,
                        1, 1, 0, 0, 3, 3,
                        2, 2, 1, 1, 3, 4,
                        0, 0, 2, 2, 3, 5
                );
                trianglemesh.getFaceSmoothingGroups().addAll(0, 2, 4, 8);
                MeshView meshview = new MeshView(trianglemesh);
                return meshview;
        }


}




