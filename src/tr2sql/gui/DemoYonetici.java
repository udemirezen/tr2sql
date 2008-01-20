package tr2sql.gui;

import net.zemberek.araclar.Kayitci;
import net.zemberek.araclar.turkce.YaziBirimi;
import net.zemberek.araclar.turkce.YaziBirimiTipi;
import net.zemberek.araclar.turkce.YaziIsleyici;
import net.zemberek.erisim.Zemberek;
import net.zemberek.islemler.TurkceYaziTesti;
import net.zemberek.yapi.DilBilgisi;
import net.zemberek.yapi.Kelime;
import net.zemberek.tr.yapi.TurkiyeTurkcesi;

import java.util.*;
import java.util.logging.Logger;

import tr2sql.KelimeEleyici;
import tr2sql.Tr2SQLKelimeEleyici;

/**
 */
public class DemoYonetici {

    private static Logger logger = Kayitci.kayitciUret(DemoYonetici.class);
    private Zemberek zemberek = new Zemberek(new TurkiyeTurkcesi());
    private DilBilgisi dilBilgisi = zemberek.dilBilgisi();
    private KelimeEleyici eleyici = new Tr2SQLKelimeEleyici(dilBilgisi);

    public DemoYonetici() {
    }

    /**
     * turkceyeozel karakterlerin dizisini dondurur
     * @return char dizisi. turkceye ozel karakterler yer alir.
     */
    public char[] ozelKarakterDizisiGetir() {
        return dilBilgisi.alfabe().asciiDisiHarfler();
    }

    public String islemUygula(String islemTipi, String giris) {

        IslemTipi islem;
        try {
            islem = IslemTipi.valueOf(islemTipi);
            return islemUygula(islem, giris);
        } catch (IllegalArgumentException e) {
            logger.severe("istenilen islem:" + islemTipi + " mevcut degil");
            return "";
        }
    }

    public String islemUygula(IslemTipi islemTipi, String giris) {
        switch (islemTipi) {
            case YAZI_COZUMLE:
                return yaziCozumle(giris);
            case KISITLI_COZUMLE:
                return kisitliCozumle(giris);
            default:
                return "";
        }
    }

    public String kisitliCozumle(String giris) {
        List<YaziBirimi> analizDizisi = YaziIsleyici.analizDizisiOlustur(giris);
        StringBuffer sonuc = new StringBuffer();
        for (YaziBirimi birim : analizDizisi) {
            if (birim.tip == YaziBirimiTipi.KELIME) {
                List<Kelime> cozumler = eleyici.ele(zemberek.kelimeCozumle(birim.icerik));
                sonuc.append(birim.icerik).append('\n');
                if (cozumler.size() == 0)
                    sonuc.append(" :cozulemedi\n");
                else {
                    for (Kelime cozum : cozumler)
                        sonuc.append(cozum).append("\n");
                }
            }
        }
        return sonuc.toString();
    }

    public String yaziCozumle(String giris) {
        List<YaziBirimi> analizDizisi = YaziIsleyici.analizDizisiOlustur(giris);
        StringBuffer sonuc = new StringBuffer();
        for (YaziBirimi birim : analizDizisi) {
            if (birim.tip == YaziBirimiTipi.KELIME) {
                Kelime[] cozumler = zemberek.kelimeCozumle(birim.icerik);
                sonuc.append(birim.icerik).append('\n');
                if (cozumler.length == 0)
                    sonuc.append(" :cozulemedi\n");
                else {
                    for (Kelime cozum : cozumler)
                        sonuc.append(cozum).append("\n");
                }
            }
        }
        return sonuc.toString();
    }
}
