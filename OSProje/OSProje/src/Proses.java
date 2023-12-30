public class Proses {
    private final int prosesId;
    private String prosesDurumu;
    private int varisZamani;
    private int oncelikDegeri;
    private int prosesSuresi;
    private final String color;
    private int beklemeSuresi;
    private  int hafizablok;
    private int yazici;
    private int tarayici;
    private  int modem;
    private int cd;

    public Proses(int prosesId, int varisZamani, int oncelikDegeri, int prosesSuresi, int hafizablok, int yazici, int tarayici,int modem, int cd, String color){
        this.prosesId = prosesId;
        this.varisZamani = varisZamani;
        this.oncelikDegeri = oncelikDegeri;
        this.prosesSuresi = prosesSuresi;
        this.hafizablok=hafizablok;
        this.yazici=yazici;
        this.tarayici=tarayici;
        this.modem=modem;
        this.cd=cd;
        this.color = color;
        this.prosesDurumu = "";
        this.beklemeSuresi=0;
    }

    public int getVarisZamani() {
        return varisZamani;
    }

    public String getColor() {
        return color;
    }

    public int getProsesId() {
        return prosesId;
    }

    public String getProsesDurumu() {
        return prosesDurumu;
    }

    public void setProsesDurumu(String prosesDurumu) {
        this.prosesDurumu = prosesDurumu;
    }

    public int getOncelikDegeri() {
        return oncelikDegeri;
    }

    public void setOncelikDegeri(int oncelikDegeri) {
        this.oncelikDegeri = oncelikDegeri;
    }

    public int getProsesSuresi() {
        return prosesSuresi;
    }

    public void setProsesSuresi(int prosesSuresi) {
        this.prosesSuresi = prosesSuresi;
    }

    public int getBeklemeSuresi() {
        return beklemeSuresi;
    }

    public void setBeklemeSuresi(int beklemeSuresi) {
        this.beklemeSuresi = beklemeSuresi;
    }
   public int getHafizablok() {
       return hafizablok;
   }

    public int getYazici() {
        return yazici;
    }

    public int getTarayici() {
        return tarayici;
    }
    public int getModem() {
        return modem;
    }
    public int getCd() {
        return cd;
    }


}
