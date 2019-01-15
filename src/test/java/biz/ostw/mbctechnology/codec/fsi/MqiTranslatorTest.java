package biz.ostw.mbctechnology.codec.fsi;

import biz.ostw.fsi.translator.InputStreamSource;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.net.URI;

public class MqiTranslatorTest {

    @Test
    public void testLoad() throws Exception {
        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        Assert.assertNotNull(mqiFile);
    }

    @Test
    public void testLoadVersion() throws Exception {
        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        Assert.assertNotNull(mqiFile);
        Assert.assertEquals("1.0", mqiFile.version());

        mqiFile.version("2.0");
        Assert.assertEquals("2.0", mqiFile.version());
    }

    @Test
    public void testLoadDatatype() throws Exception {
        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        Assert.assertNotNull(mqiFile);
        Assert.assertEquals("QC", mqiFile.datatype());

        mqiFile.datatype("2.0");
        Assert.assertEquals("2.0", mqiFile.datatype());
    }

    @Test
    public void testLoadLot() throws Exception {
        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        Assert.assertNotNull(mqiFile);
        Assert.assertEquals("BC1901BH", mqiFile.lot());

        mqiFile.lot("2.0");
        Assert.assertEquals("2.0", mqiFile.lot());
    }

    @Test
    public void testLoadValiddate() throws Exception {
        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        Assert.assertNotNull(mqiFile);
        Assert.assertEquals("2019-03-05", mqiFile.validdate());

        mqiFile.validdate("2.0");
        Assert.assertEquals("2.0", mqiFile.validdate());
    }
}
