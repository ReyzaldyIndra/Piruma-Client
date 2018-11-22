package app.piruma_java.model;


public class RoomItem {
    private String name_room;
    private String name_dept;

    public RoomItem(String name_room, String name_dept){
        this.name_room = name_room;
        this.name_dept = name_dept;
    }

    public String getName_room() {
                return name_room;
            }

    public void setName_room(String name_room) {
        this.name_room = name_room;
    }

    public String getName_dept() {
        return name_dept;
    }

    public void setName_dept(String name_dept) {
        this.name_dept = name_dept;
    }

}
