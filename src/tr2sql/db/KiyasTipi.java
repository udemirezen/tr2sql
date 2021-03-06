package tr2sql.db;

public enum KiyasTipi {
    ESIT,
    ESIT_DEGIL,
    BASI_BENZER,
    BASI_BENZEMEZ,
    SONU_BENZER,
    SONU_BENZEMEZ,
    KUCUK,
    BUYUK,
    KUCUK_ESIT,
    BUYUK_ESIT,
    NULL,
    NULL_DEGIL;

    public KiyasTipi tersi() {
        switch (this) {
            case BUYUK:
                return KUCUK_ESIT;
            case KUCUK:
                return BUYUK_ESIT;
            case BUYUK_ESIT:
                return KUCUK;
            case KUCUK_ESIT:
                return BUYUK;
            case ESIT:
                return ESIT_DEGIL;
            case ESIT_DEGIL:
                return ESIT;
            case NULL:
                return NULL_DEGIL;
            case NULL_DEGIL:
                return NULL;
            case BASI_BENZER:
                return BASI_BENZEMEZ;
            case BASI_BENZEMEZ:
                return BASI_BENZER;
            case SONU_BENZER:
                return SONU_BENZEMEZ;
            case SONU_BENZEMEZ:
                return SONU_BENZER;
        }
        throw new IllegalArgumentException("beklenmeyen deger!");
    }

    public static KiyasTipi kavramdanTipBul(Kavram kavram) {
        String ad = kavram.getAd();
        if (ad.equals("BUYUK"))
            return BUYUK;
        else if (ad.equals("KUCUK"))
            return KUCUK;
        else if (ad.equals("ESIT"))
            return ESIT;
        else if (ad.equals("ESIT_DEGIL"))
            return ESIT_DEGIL;
        else if (ad.equals("BASLAMAK"))
            return BASI_BENZER;
        else if (ad.equals("BITMEK"))
            return SONU_BENZER;
        else if (ad.equals("NULL"))
            return NULL;
        else return null;
    }
}
