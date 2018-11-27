package app.piruma_java.model;

public class SelectRoom2 {
    private String jadwal_start;
    private String jadwal_end;
    private String jadwal_keterangan;
    public SelectRoom2(String jadwal_start, String jadwal_end, String jadwal_keterangan){
        this.jadwal_start = jadwal_start;
        this.jadwal_end = jadwal_end;
        this.jadwal_keterangan = jadwal_keterangan;
    }

    public String getJadwal_start() {
        return jadwal_start;
    }

    public void setJadwal_start(String jadwal_start) {
        this.jadwal_start = jadwal_start;
    }

    public String getJadwal_end() {
        return jadwal_end;
    }

    public void setJadwal_end(String jadwal_end) {
        this.jadwal_end = jadwal_end;
    }

    public String getJadwal_keterangan() {
        return jadwal_keterangan;
    }

    public void setJadwal_keterangan(String jadwal_keterangan) {
        this.jadwal_keterangan = jadwal_keterangan;
    }
}

