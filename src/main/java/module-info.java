module chatroom.project4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens chatroom.project4 to javafx.fxml;
    exports chatroom.project4;
}