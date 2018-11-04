package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest
{

    private Varasto varasto;
    private static final double TARKKUUS = 0.0001;
    private static final double ISONEG = -20.0;
    private static final double NORMNEG = -10.0;
    private static final double ISOPOS = 20.0;
    private static final double NORMPOS = 10.0;
    private static final double PIENIPOS = 6.0;

    @Before
    public void setUp() {
        varasto = new Varasto(NORMPOS);
    }

    @Test
    public void virheellisenVarastonTilavuusNolla() {
        Varasto dummy = new Varasto(ISONEG);
        assertEquals(0, dummy.getTilavuus(), TARKKUUS);
    }
    
    @Test
    public void virheellisenVarastonTilavuusNolla2() {
        Varasto dummy = new Varasto(ISONEG,0);
        assertEquals(0, dummy.getTilavuus(), TARKKUUS);
    }
    
    @Test
    public void virheellisenVarastonSaldoNolla() {
        Varasto dummy = new Varasto(ISONEG,PIENIPOS);
        assertEquals(0, dummy.getSaldo(), TARKKUUS);
    }
    
    @Test
    public void virheellinenVarastoSaldoNolla() {
        Varasto dummy = new Varasto(NORMPOS,ISONEG);
        assertEquals(0, dummy.getSaldo(), TARKKUUS);
    }

    @Test
    public void oikeaAlkusaldoUudellaVarastolla() {
        Varasto varasto2 = new Varasto(NORMPOS,PIENIPOS);
        assertEquals(PIENIPOS, varasto2.getSaldo(), TARKKUUS);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), TARKKUUS);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(NORMPOS, varasto.getTilavuus(), TARKKUUS);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(PIENIPOS);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(PIENIPOS, varasto.getSaldo(), TARKKUUS);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(PIENIPOS);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 4
        assertEquals(NORMPOS-PIENIPOS, varasto.paljonkoMahtuu(), TARKKUUS);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(NORMPOS);

        double saatuMaara = varasto.otaVarastosta(PIENIPOS);

        assertEquals(PIENIPOS, saatuMaara, TARKKUUS);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(NORMPOS);

        varasto.otaVarastosta(PIENIPOS);

        // varastossa pitäisi olla tilaa 10-10+6  =6
        assertEquals(PIENIPOS, varasto.paljonkoMahtuu(), TARKKUUS);
    }
    
    @Test
    public void eiAnnaLisataNegatiivistaMaaraaVarastoon() {
        varasto.lisaaVarastoon(NORMNEG);
        assertEquals(0.0, varasto.getSaldo(), TARKKUUS);
    }
    
    @Test
    public void liikaaTavaraaVarastoon() {
        varasto.lisaaVarastoon(ISOPOS);
        // Varastoon pitäisi mahtua vain 10.0
        assertEquals(NORMPOS, varasto.getSaldo(), TARKKUUS);
    }

    @Test
    public void eiOtaVarastostaEnemmanKuinSiellaOn() {
        varasto.lisaaVarastoon(PIENIPOS);
        double otettiin = varasto.otaVarastosta(NORMPOS);
        assertEquals(PIENIPOS, otettiin, TARKKUUS);
    }
    
    @Test
    public void eiAnnaOttaaNegatiivistaMaaraaVarastosta() {
        double otettiin = varasto.otaVarastosta(ISONEG);
        assertEquals(0.0, otettiin, TARKKUUS);
    }
    
    @Test
    public void varastonMerkkijonoEsitysOikein() {
        varasto.lisaaVarastoon(PIENIPOS);
        assertEquals("saldo = 6.0, vielä tilaa 4.0", varasto.toString());
    }
}