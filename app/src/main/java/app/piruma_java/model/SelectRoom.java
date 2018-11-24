package app.piruma_java.model;

public class SelectRoom {
    private String dept;
    private String fak;
    private String jml;
    private String ruang;
    private String fasilitas;
    private String jadwal;
    private String kapasitas;
    private String id_ruangan;

    public SelectRoom(String ruang, String id_ruangan, String dept, String kapasitas, String fasilitas){
        this.dept = dept;
        this.fak = fak;
        this.jml = jml;
        this.ruang = ruang;
        this.fasilitas = fasilitas;
        this.jadwal = jadwal;
        this.kapasitas = kapasitas;
        this.id_ruangan = id_ruangan;
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

    public String getId_ruangan() {
        return id_ruangan;
    }

    public void setId_ruangan(String id_ruangan) {
        this.id_ruangan = id_ruangan;
    }
}


