package app.piruma_java.model;

public class SelectRoom {
    private String dept;
    private String fak;
    private String jml;
    public String ruang;
    private String id_ruangan;
//    private String fasilitas;
//    private String kapasitas;

    public SelectRoom(String ruang, String id_ruangan, String dept){
        this.dept = dept;
        this.fak = fak;
        this.jml = jml;
        this.ruang = ruang;
        this.id_ruangan = id_ruangan;
//        this.fasilitas = fasilitas;
//        this.kapasitas = kapasitas;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getFak() {
        return fak;
    }

    public void setFak(String fak) {
        this.fak = fak;
    }

    public String getJml() {
        return jml;
    }

    public void setJml(String jml) {
        this.jml = jml;
    }

    public String getId_ruangan() {
        return id_ruangan;
    }

    public void setId_ruangan(String id_ruangan) {
        this.id_ruangan = id_ruangan;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

//    public String getFasilitas() {
//        return fasilitas;
//    }
//
//    public void setFasilitas(String fasilitas) {
//        this.fasilitas = fasilitas;
//    }
//
//    public String getKapasitas() {
//        return kapasitas;
//    }
//
//    public void setKapasitas(String kapasitas) {
//        this.kapasitas = kapasitas;
//    }
}


