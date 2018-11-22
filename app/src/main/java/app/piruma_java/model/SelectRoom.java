package app.piruma_java.model;

public class SelectRoom {
    private String dept;
    private String fak;
    private String jml;
    private String ruang;
    private String fasilitas;
    private String jadwal;
    private String kapasitas;

    public SelectRoom(String dept, String fak, String jml, String ruang, String fasilitas, String jadwal, String kapasitas){
        this.dept = dept;
        this.fak = fak;
        this.jml = jml;
        this.ruang = ruang;
        this.fasilitas = fasilitas;
        this.jadwal = jadwal;
        this.kapasitas = kapasitas;
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

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }
}


