package app.piruma_java.model;

public class SelectRoom2 {
    private String kapasitas;
    private String fasilitas;

    public SelectRoom2(String fasilitas, String kapasitas){
        this.fasilitas = fasilitas;
        this.kapasitas = kapasitas;
    }
    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }


}

