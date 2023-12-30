public class Gorevlendirici {
    private Kuyruk prosesler;
    private Kuyruk gercekZamanliProsesler;
    private Kuyruk kullaniciProsesleri;
    private Kuyruk yuksekOncelikKuyrugu;
    private Kuyruk ortaOncelikKuyrugu;
    private Kuyruk dusukOncelikKuyrugu;
    private int zaman;
    private int kalanSure;
    private Proses gercekZamanliCalisanProses;
    private Proses kullaniciProsesi;
    private int sistemyazici;
    private int sistemtarayici;
    private int sistemmodem;
    private int sistemcd;
    private int GercekZamanliPhafiza;
    private int kullaniciproseshafiza;
    private int GüncelGercekZamanliPhafiza;
    private  int GüncelKullaniciHafiza;
    private int Güncelsistemyazici;
    private int Güncelsistemtarayici;
    private  int Güncelsistemmodem;
    private int Güncelsistemcd;
    public Gorevlendirici(Kuyruk prosesler){
        this.gercekZamanliProsesler = new Kuyruk();
        this.kullaniciProsesleri = new Kuyruk();
        this.yuksekOncelikKuyrugu = new Kuyruk();
        this.ortaOncelikKuyrugu = new Kuyruk();
        this.dusukOncelikKuyrugu = new Kuyruk();
        this.prosesler = prosesler;
        this.gercekZamanliCalisanProses=null;
        this.kullaniciProsesi = null;
        this.zaman=0;
        this.kalanSure=0;
        this.sistemyazici=2;
        this.sistemtarayici=1;
        this.sistemmodem=1;
        this.sistemcd=2;
        this.GercekZamanliPhafiza=64;
        this.kullaniciproseshafiza=960;
        this.GüncelGercekZamanliPhafiza=0;
        this.GüncelKullaniciHafiza=0;
        this.Güncelsistemyazici=0;
        this.Güncelsistemtarayici=0;
        this.Güncelsistemmodem=0;
        this.Güncelsistemcd=0;

    }

    public void baslat(){

        while(true)
        {
            //prosesleri zamana bagli olarak ilgili kuyruklara atanıyor
            prosesleriKuyruklaraAta();
            //gercekZamanliProsesler kuyrugu bos degilse önce onlar calistirilacaktir
            if(!gercekZamanliProsesler.bosmu()||gercekZamanliCalisanProses!=null){
                //gercekZamanliProsesler calismadan önce calisan kullanici prosesi varsa onu bekleme moduna alınıyor
                if(kullaniciProsesi!=null){
                if(kullaniciProsesi.getProsesDurumu()=="proses basladi"){
                    kullaniciProsesi.setProsesDurumu("proses beklemede");
                    if (kullaniciProsesi.getOncelikDegeri()==1){
                        kullaniciProsesi.setOncelikDegeri(2);
                        yuksekOncelikKuyrugu.sil(kullaniciProsesi);
                        ortaOncelikKuyrugu.ekle(kullaniciProsesi);
                    }
                    else if(kullaniciProsesi.getOncelikDegeri()==2){
                        kullaniciProsesi.setOncelikDegeri(3);
                        ortaOncelikKuyrugu.sil(kullaniciProsesi);
                        dusukOncelikKuyrugu.ekle(kullaniciProsesi);
                    }
                    else{
                        dusukOncelikKuyrugu.sil(kullaniciProsesi);
                        dusukOncelikKuyrugu.ekle(kullaniciProsesi);
                    }
                    yazdir(kullaniciProsesi.getColor(),zaman,kullaniciProsesi.getProsesId(),kullaniciProsesi.getOncelikDegeri()
                            ,kullaniciProsesi.getProsesSuresi(),kullaniciProsesi.getProsesDurumu(), kullaniciProsesi.getHafizablok(),
                            kullaniciProsesi.getYazici(),kullaniciProsesi.getTarayici(),kullaniciProsesi.getModem(),kullaniciProsesi.getCd());
                }}
                if(gercekZamanliCalisanProses == null) {
                    //gercekZamanliCalisan  prosesleri baslatılıyor
                    gercekZamanliCalisanProses = gercekZamanliProsesler.ilkElemaniGetir();
                    gercekZamanliCalisanProses.setProsesDurumu("proses basladi");
                    GüncelGercekZamanliPhafiza=GercekZamanliPhafiza-gercekZamanliCalisanProses.getHafizablok();
                    yazdir(gercekZamanliCalisanProses.getColor(),zaman,gercekZamanliCalisanProses.getProsesId(),gercekZamanliCalisanProses.getOncelikDegeri()
                            ,gercekZamanliCalisanProses.getProsesSuresi(),gercekZamanliCalisanProses.getProsesDurumu(), gercekZamanliCalisanProses.getHafizablok(),
                            gercekZamanliCalisanProses.getYazici(),gercekZamanliCalisanProses.getTarayici(),gercekZamanliCalisanProses.getModem(),gercekZamanliCalisanProses.getCd());
                    kalanSure= gercekZamanliCalisanProses.getProsesSuresi()-1;
                    gercekZamanliCalisanProses.setProsesSuresi(kalanSure);

                }
                else{
                    //gercek zamanli prosesler calisirken kuyruga alinan kullanici proseslerinin zaman asimina ugrayip ugramadigi kontrol edilir
                    prosesZamanAsimiKontrol();
                    //prosesZamanAsimiKontrol();
                    //gercek zamanli calisan prosesler varsa isi bittiyse sonlandirilir bitmediyse yürütülür ve bilgileri ekrana yazdirilir
                    //zaman++;
                    if (gercekZamanliCalisanProses.getProsesSuresi()==0){
                        gercekZamanliCalisanProses.setProsesDurumu("proses sonlandi");
                        GüncelGercekZamanliPhafiza=GercekZamanliPhafiza+gercekZamanliCalisanProses.getHafizablok();
                        yazdir(gercekZamanliCalisanProses.getColor(),zaman,gercekZamanliCalisanProses.getProsesId(),gercekZamanliCalisanProses.getOncelikDegeri()
                                ,gercekZamanliCalisanProses.getProsesSuresi(),gercekZamanliCalisanProses.getProsesDurumu(), gercekZamanliCalisanProses.getHafizablok(),
                                gercekZamanliCalisanProses.getYazici(),gercekZamanliCalisanProses.getTarayici(),gercekZamanliCalisanProses.getModem(),gercekZamanliCalisanProses.getCd());
                        gercekZamanliCalisanProses = null;
                    }else {
                        gercekZamanliCalisanProses.setProsesDurumu("proses yurutuluyor");
                       /* yazdir(gercekZamanliCalisanProses.getColor(),zaman,gercekZamanliCalisanProses.getProsesId(),gercekZamanliCalisanProses.getOncelikDegeri()
                                ,gercekZamanliCalisanProses.getProsesSuresi(),gercekZamanliCalisanProses.getProsesDurumu(), gercekZamanliCalisanProses.getHafizablok(),
                                gercekZamanliCalisanProses.getYazici(),gercekZamanliCalisanProses.getTarayici(),gercekZamanliCalisanProses.getModem(),gercekZamanliCalisanProses.getCd());*/
                        kalanSure = gercekZamanliCalisanProses.getProsesSuresi() - 1;
                        gercekZamanliCalisanProses.setProsesSuresi(kalanSure);
                    }
                }
                continue;
            }
            else if((!dusukOncelikKuyrugu.bosmu()) || (!ortaOncelikKuyrugu.bosmu()) || (!yuksekOncelikKuyrugu.bosmu()) || kullaniciProsesi!=null ){
                //calisan gercek zamanli prosesler olmadigi zaman kullanici prosesleri algoritmaya göre calistirilir
                //öncelikle yuksek öncelik kuyruguna bakilir orada elemanlar varsa ilk onlar calistirilir
                if (gercekZamanliCalisanProses==null) {
                    if (!yuksekOncelikKuyrugu.bosmu()) {
                        kullaniciProsesi = yuksekOncelikKuyrugu.ilkElemaniGetir();

                    } else if (!ortaOncelikKuyrugu.bosmu()) {
                        kullaniciProsesi = ortaOncelikKuyrugu.ilkElemaniGetir();

                    } else if(!dusukOncelikKuyrugu.bosmu()) {
                        kullaniciProsesi = dusukOncelikKuyrugu.ilkElemaniGetir();
                    }
                    if(kullaniciProsesi.getProsesDurumu()=="proses kuyrukta"){
                        //kuyruktaki prosesler 1sn olacak sekilde calistirilmaya baslanir
                        //prosesZamanAsimiKontrol();
                        kullaniciProsesi.setProsesDurumu("proses basladi");
                        GüncelKullaniciHafiza=kullaniciproseshafiza-kullaniciProsesi.getHafizablok();
                        Güncelsistemyazici=sistemyazici-kullaniciProsesi.getYazici();
                        Güncelsistemtarayici=sistemtarayici-kullaniciProsesi.getTarayici();
                        Güncelsistemmodem=sistemmodem-kullaniciProsesi.getModem();
                        Güncelsistemcd=sistemcd-kullaniciProsesi.getCd();
                        yazdir(kullaniciProsesi.getColor(),zaman,kullaniciProsesi.getProsesId(),kullaniciProsesi.getOncelikDegeri()
                                ,kullaniciProsesi.getProsesSuresi(),kullaniciProsesi.getProsesDurumu(),kullaniciProsesi.getHafizablok(),
                                kullaniciProsesi.getYazici(),kullaniciProsesi.getTarayici(),kullaniciProsesi.getModem(),kullaniciProsesi.getCd());
                        prosesBekletmeVeyaSonlandirma();
                        //proses 1sn calistirilir isi biterse sonlandirilir bitmezse beklemeye alinip onceligi düsürülür
                        continue;

                    }
                    else if(kullaniciProsesi.getProsesDurumu()=="proses beklemede"){
                        //proses beklemede ise yürütülür ve tekrar bekletmeye alinir veya sonlandirilir
                        prosesZamanAsimiKontrol();
                        kullaniciProsesi.setBeklemeSuresi(kullaniciProsesi.getBeklemeSuresi()+1);
                        kullaniciProsesi.setProsesDurumu("proses yurutuluyor");

                        yazdir(kullaniciProsesi.getColor(),zaman,kullaniciProsesi.getProsesId(),kullaniciProsesi.getOncelikDegeri()
                                ,kullaniciProsesi.getProsesSuresi(),kullaniciProsesi.getProsesDurumu(), kullaniciProsesi.getHafizablok(),
                                kullaniciProsesi.getYazici(),kullaniciProsesi.getTarayici(),kullaniciProsesi.getModem(),kullaniciProsesi.getCd());

                        prosesBekletmeVeyaSonlandirma();
                        continue;
                    }
                }
            }
            //tüm proses kuyruklari bos ise calistirilcak proses kalmamistir ve program sonlandirilir
            if(prosesler.bosmu()&& kullaniciProsesleri.bosmu()&&gercekZamanliProsesler.bosmu()&& dusukOncelikKuyrugu.bosmu()&&
                    yuksekOncelikKuyrugu.bosmu()&& ortaOncelikKuyrugu.bosmu()&&kullaniciProsesi==null&&gercekZamanliCalisanProses==null){
                break;
            }
        }
    }
    private void prosesleriKuyruklaraAta()
    {
        if(!prosesler.bosmu())
        {
                for (var proses : prosesler.getAll().stream().toList()) {
                         if (proses.getVarisZamani() == zaman)
                        {
                            if (proses.getOncelikDegeri() == 0) {
                                if (proses.getYazici()<=sistemyazici && proses.getTarayici()<=sistemtarayici && proses.getModem()<=sistemmodem && proses.getCd()<=sistemcd )
                                {
                                    if(proses.getHafizablok()<=GercekZamanliPhafiza){
                                    gercekZamanliProsesler.ekle(proses);}
                                    else System.out.println(proses.getColor()+proses.getProsesId()+"  HATA - Gercek-zamanli proses (64MB) tan daha fazla bellek talep ediyor - proses silindi");
                                }
                                else {System.out.println(proses.getColor()+proses.getProsesId()+"  HATA - Gercek-zamanli proses cok sayida kaynak talep ediyor - proses silindi ");}
                            }
                            else {
                                if (proses.getYazici()<=sistemyazici && proses.getTarayici()<=sistemtarayici && proses.getModem()<=sistemmodem && proses.getCd()<=sistemcd ) {
                                    if(proses.getHafizablok()<=kullaniciproseshafiza){
                                    kullaniciProsesleri.ekle(proses);}
                                    else {System.out.println(proses.getColor()+proses.getProsesId()+" HATA - Proses (960 MB) tan daha fazla bellek talep ediyor – proses silindi");}
                                }
                                else {System.out.println(proses.getColor()+proses.getProsesId()+"  HATA - Proses cok sayida kaynak talep ediyor - proses silindi");}
                            }
                            prosesler.sil(proses);
                        }
                        else {
                            try {
                                Thread.sleep(60);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                }++zaman;

        }
        if(!kullaniciProsesleri.bosmu())
        {
            //kullanici proseslerini kuyruklara atildiginda durumunu proses kuyrukta olarak güncelliyorum
            for (var kullaniciProses : kullaniciProsesleri.getAll().stream().toList())
            {

                    kullaniciProses.setProsesDurumu("proses kuyrukta");
                    if (kullaniciProses.getOncelikDegeri() == 1) {
                        yuksekOncelikKuyrugu.ekle(kullaniciProses);
                    } else if (kullaniciProses.getOncelikDegeri() == 2) {
                        ortaOncelikKuyrugu.ekle(kullaniciProses);
                    } else {
                        dusukOncelikKuyrugu.ekle(kullaniciProses);
                    }



                kullaniciProsesleri.sil(kullaniciProses);
            }
        }
    }
    private void prosesBekletmeVeyaSonlandirma()
    {
        prosesZamanAsimiKontrol();
        //zaman++;
        //proses bekletmeye alindiginda bekleme süresi tekrardan sifirlanir
        kullaniciProsesi.setBeklemeSuresi(0);
        kalanSure = kullaniciProsesi.getProsesSuresi()-1;
        kullaniciProsesi.setProsesSuresi(kalanSure);
        if(kullaniciProsesi.getProsesSuresi()!=0)
        {
            //burada prosesler sonlanmadiysa öncelikleri düsürülür ve bir alt kuyruklara atama islemleri yapilir
            if(kullaniciProsesi.getOncelikDegeri()==1)
            {
                kullaniciProsesi.setOncelikDegeri(2);
                ortaOncelikKuyrugu.ekle(kullaniciProsesi);
            }
            else if(kullaniciProsesi.getOncelikDegeri()==2)
            {
                kullaniciProsesi.setOncelikDegeri(3);
                dusukOncelikKuyrugu.ekle(kullaniciProsesi);
            }
            else if(kullaniciProsesi.getOncelikDegeri()==3)
            {
                dusukOncelikKuyrugu.ekle(kullaniciProsesi);
            }

            kullaniciProsesi.setProsesDurumu("proses beklemede");
            /*yazdir(kullaniciProsesi.getColor(),zaman,kullaniciProsesi.getProsesId(),kullaniciProsesi.getOncelikDegeri()
                    ,kullaniciProsesi.getProsesSuresi(),kullaniciProsesi.getProsesDurumu(), kullaniciProsesi.getHafizablok(),
                    kullaniciProsesi.getYazici(),kullaniciProsesi.getTarayici(),kullaniciProsesi.getModem(),kullaniciProsesi.getCd());*/
        }
        else
        {
            //prosesin isi bittiyse sonlandirilir ve ilgili kuyruktan proses silinir
            GüncelKullaniciHafiza=GüncelKullaniciHafiza+kullaniciProsesi.getHafizablok();
            Güncelsistemyazici=Güncelsistemyazici+ kullaniciProsesi.getYazici();
            Güncelsistemtarayici=Güncelsistemtarayici+kullaniciProsesi.getTarayici();
            Güncelsistemmodem=Güncelsistemmodem+kullaniciProsesi.getModem();
            Güncelsistemcd=Güncelsistemcd+kullaniciProsesi.getCd();
            kullaniciProsesi.setProsesDurumu("proses sonlandi");
            yazdir(kullaniciProsesi.getColor(),zaman,kullaniciProsesi.getProsesId(),kullaniciProsesi.getOncelikDegeri()
                    ,kullaniciProsesi.getProsesSuresi(),kullaniciProsesi.getProsesDurumu(), kullaniciProsesi.getHafizablok(),
                    kullaniciProsesi.getYazici(),kullaniciProsesi.getTarayici(),kullaniciProsesi.getModem(),kullaniciProsesi.getCd());
            if(kullaniciProsesi.getOncelikDegeri()==3) 
            {
                dusukOncelikKuyrugu.sil(kullaniciProsesi);
            }
            else if(kullaniciProsesi.getOncelikDegeri()==2)
            {
                ortaOncelikKuyrugu.sil(kullaniciProsesi);
            }
            else{
                yuksekOncelikKuyrugu.sil(kullaniciProsesi);
            }
            kullaniciProsesi = null;
        }
    }

   private void prosesZamanAsimiKontrol()
    {
        //kullanici prosesleri ilgili tüm kuyruklarda prosesler saniye saniye kontrol edilir ve bekleme süreleri 1 er 1 er arttırılır
        // zaman asimina ugrayan proses varsa proses sonlandirilir
        if(!yuksekOncelikKuyrugu.bosmu())
        {
            for(var proses : yuksekOncelikKuyrugu.getAll().stream().toList())
            {

                if(proses.getProsesDurumu()=="proses beklemede"||proses.getProsesDurumu()=="proses kuyrukta"){

                    if(proses.getBeklemeSuresi()==20)
                    {
                        proses.setProsesDurumu("proses zaman asimi");
                        System.out.println(proses.getColor()+proses.getProsesId() +"   HATA - Proses zaman asimi (20 sn de tamamlanamadi)");
                        GüncelKullaniciHafiza=GüncelKullaniciHafiza+kullaniciProsesi.getHafizablok();
                        Güncelsistemyazici=Güncelsistemyazici+ kullaniciProsesi.getYazici();
                        Güncelsistemtarayici=Güncelsistemtarayici+kullaniciProsesi.getTarayici();
                        Güncelsistemmodem=Güncelsistemmodem+kullaniciProsesi.getModem();
                        Güncelsistemcd=Güncelsistemcd+kullaniciProsesi.getCd();
                        yuksekOncelikKuyrugu.sil(proses);
                    }
                    else
                    {
                        proses.setBeklemeSuresi(proses.getBeklemeSuresi()+1);
                    }
                }
            }
        }
        if(!ortaOncelikKuyrugu.bosmu())
        {
            for(var proses : ortaOncelikKuyrugu.getAll().stream().toList())
            {

                if(proses.getProsesDurumu()=="proses beklemede"||proses.getProsesDurumu()=="proses kuyrukta")
                {

                    if(proses.getBeklemeSuresi()==20)
                    {
                        proses.setProsesDurumu("proses zaman asimi");
                        System.out.println(proses.getProsesId() +"  HATA - Proses zaman asimi (20 sn de tamamlanamadi)");
                        GüncelKullaniciHafiza=GüncelKullaniciHafiza+kullaniciProsesi.getHafizablok();
                        Güncelsistemyazici=Güncelsistemyazici+ kullaniciProsesi.getYazici();
                        Güncelsistemtarayici=Güncelsistemtarayici+kullaniciProsesi.getTarayici();
                        Güncelsistemmodem=Güncelsistemmodem+kullaniciProsesi.getModem();
                        Güncelsistemcd=Güncelsistemcd+kullaniciProsesi.getCd();
                        ortaOncelikKuyrugu.sil(proses);
                    }
                    else
                    {
                        proses.setBeklemeSuresi(proses.getBeklemeSuresi()+1);
                    }
                }
            }
        }
        if(!dusukOncelikKuyrugu.bosmu())
        {
            for(var proses : dusukOncelikKuyrugu.getAll().stream().toList())
            {
                if(proses.getProsesDurumu()=="proses beklemede"||proses.getProsesDurumu()=="proses kuyrukta")
                {
                    if(proses.getBeklemeSuresi()==20)
                    {
                        proses.setProsesDurumu("proses zaman asimi");
                        System.out.println(proses.getProsesId() +"  HATA - Proses zaman asimi (20 sn de tamamlanamadi)\n");
                        GüncelKullaniciHafiza=GüncelKullaniciHafiza+kullaniciProsesi.getHafizablok();
                        Güncelsistemyazici=Güncelsistemyazici+ kullaniciProsesi.getYazici();
                        Güncelsistemtarayici=Güncelsistemtarayici+kullaniciProsesi.getTarayici();
                        Güncelsistemmodem=Güncelsistemmodem+kullaniciProsesi.getModem();
                        Güncelsistemcd=Güncelsistemcd+kullaniciProsesi.getCd();
                        dusukOncelikKuyrugu.sil(proses);
                    }
                    else
                    {
                        proses.setBeklemeSuresi(proses.getBeklemeSuresi()+1);
                    }
                }
            }
        }
    }
    private void yazdir(String color, int zaman, int prosesId, int oncelik, int kalanSure,String prosesDurumu,int hafizablok, int yazici, int tarayici, int modem, int cd)
    {
        String esitlenmisProses = prosesDurumDuzenle(prosesDurumu,18);
        System.out.print(color);
        //if(zaman < 10)
           // System.out.print(" ");
        System.out.print(prosesId+"    varis "+zaman);
        if(prosesId<10)
            System.out.printf(" ");
        System.out.printf("   "+"oncelik: "+oncelik+"    "+ "kalan sure: "+kalanSure);
        if(kalanSure < 10)
            System.out.print(" ");
        System.out.print(" hafiza blogu "+hafizablok);
        if(kalanSure < 10)
            System.out.print(" ");
        System.out.print("yazizi "+yazici);
        if(kalanSure < 10)
            System.out.print(" ");
        System.out.print("tarayici "+tarayici);
        if(kalanSure < 10)
            System.out.print(" ");
        System.out.print("modem "+modem);
        if(kalanSure < 10)
            System.out.print(" ");
        System.out.print(" cd "+cd);
        System.out.println("  "+ esitlenmisProses);
    }
    private String prosesDurumDuzenle(String alinanString, int boyut)//String boyutunu düzenleme
    {
        if (alinanString.length() >= boyut)
        {
            return alinanString;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(alinanString);
        while (sb.length() < boyut)
        {
            sb.append(' ');
        }
        return sb.toString();
    }
}
