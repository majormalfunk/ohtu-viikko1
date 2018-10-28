package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void virheellisenVarastonTilavuusNolla() {
        Varasto dummy = new Varasto(-20);
        assertEquals(0, dummy.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellisenVarastonTilavuusNolla2() {
        Varasto dummy = new Varasto(-20,0);
        assertEquals(0, dummy.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellinenVarastoSaldoNolla() {
        Varasto dummy = new Varasto(10,-20);
        assertEquals(0, dummy.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void oikeaAlkusaldoUudellaVarastolla() {
        Varasto varasto2 = new Varasto(10,5);
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiAnnaLisataNegatiivistaMaaraaVarastoon() {
        varasto.lisaaVarastoon(-10.0);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void liikaaTavaraaVarastoon() {
        varasto.lisaaVarastoon(20.0);
        // Varastoon pitäisi mahtua vain 10.0
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiOtaVarastostaEnemmanKuinSiellaOn() {
        varasto.lisaaVarastoon(5.0);
        double otettiin = varasto.otaVarastosta(10.0);
        assertEquals(5.0, otettiin, vertailuTarkkuus);
    }
    
    @Test
    public void eiAnnaOttaaNegatiivistaMaaraaVarastosta() {
        double otettiin = varasto.otaVarastosta(-10.0);
        assertEquals(0.0, otettiin, vertailuTarkkuus);
    }
    
    @Test
    public void varastonMerkkijonoEsitysOikein() {
        varasto.lisaaVarastoon(5.0);
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }
}