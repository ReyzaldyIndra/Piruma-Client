package app.piruma_java.model;


public class RoomItem {
    private String id_pemesanan;
    private String name_room;
    private String name_dept;
    private String date;
    private String time;
    private String penanggung_jawab;
    private String telepon;
    private String keterangan;

    public RoomItem(String id_pemesanan, String name_room, String name_dept, String penanggung_jawab, String telepon, String keterangan, String date, String time){
        this.id_pemesanan = id_pemesanan;
        this.name_room = name_room;
        this.name_dept = name_dept;
        this.penanggung_jawab = penanggung_jawab;
        this.telepon = telepon;
        this.keterangan = keterangan;
        this.date = date;
        this.time = time;
    }
    public String getId_pemesanan() {
        return id_pemesanan;
    }

    public void setId_pemesanan(String id_pemesanan) {
        this.id_pemesanan = id_pemesanan;
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
    public String getPenanggung_jawab() {
        return penanggung_jawab;
    }

    public void setPenanggung_jawab(String penanggung_jawab) {
        this.penanggung_jawab = penanggung_jawab;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
