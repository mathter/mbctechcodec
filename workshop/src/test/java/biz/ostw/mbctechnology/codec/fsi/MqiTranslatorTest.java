package biz.ostw.mbctechnology.codec.fsi;

import biz.ostw.fsi.translator.InputStreamSource;
import biz.ostw.fsi.translator.OutputStreamDestination;
import biz.ostw.fsi.translator.PartSource;
import biz.ostw.fsi.xml.XmlTranslator;
import biz.ostw.mbctechnology.codec.fsi.mqi.MqiFile;
import biz.ostw.mbctechnology.codec.fsi.mqi.MqiFileDestination;
import biz.ostw.mbctechnology.codec.fsi.mqi.MqiTranslator;
import biz.ostw.mbctechnology.codec.ui.OrdinalParameter;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class MqiTranslatorTest {

    @Test
    public void testLoad() throws Exception {
        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        Assert.assertNotNull(mqiFile);
    }

    @Test
    public void testVersion() throws Exception {
        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        Assert.assertNotNull(mqiFile);
        Assert.assertEquals("1.0", mqiFile.version());

        mqiFile.version("2.0");
        Assert.assertEquals("2.0", mqiFile.version());
    }

    @Test
    public void testDatatype() throws Exception {
        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        Assert.assertNotNull(mqiFile);
        Assert.assertEquals("QC", mqiFile.datatype());

        mqiFile.datatype("2.0");
        Assert.assertEquals("2.0", mqiFile.datatype());
    }

    @Test
    public void testLot() throws Exception {
        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        Assert.assertNotNull(mqiFile);
        Assert.assertEquals("BC1901BH", mqiFile.lot());

        mqiFile.lot("2.0");
        Assert.assertEquals("2.0", mqiFile.lot());
    }

    @Test
    public void testValiddate() throws Exception {
        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        Assert.assertNotNull(mqiFile);
        Assert.assertEquals("2019-03-05", mqiFile.validdate());

        mqiFile.validdate("2.0");
        Assert.assertEquals("2.0", mqiFile.validdate());
    }

    @Test
    public void testLevel() throws Exception {
        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        Assert.assertNotNull(mqiFile);
        Assert.assertEquals("H", mqiFile.level());

        mqiFile.level("2.0");
        Assert.assertEquals("2.0", mqiFile.level());
    }

    @Test
    public void testMachineNames() throws Exception {
        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        Assert.assertEquals(new String[]{"BC-5300", "BC-5100"}, mqiFile.machineNames());

        mqiFile.machineNames(new String[]{"BC-5300", "BC-5100"});
        Assert.assertEquals(new String[]{"BC-5300", "BC-5100"}, mqiFile.machineNames());

        mqiFile.machineNames(null);
        Assert.assertEquals(new String[0], mqiFile.machineNames());

        mqiFile.machineNames(new String[0]);
        Assert.assertEquals(new String[0], mqiFile.machineNames());
    }

    @Test
    public void testParameters() throws Exception {

        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        Assert.assertNotNull(mqiFile.parameters());
        Assert.assertEquals(23, mqiFile.parameters().length);

        OrdinalParameter<String> parameter = mqiFile.parameters()[0];
        Assert.assertEquals("wbc", parameter.name());
        Assert.assertEquals("10^9/L", parameter.unit());
        Assert.assertEquals("17.80", parameter.value());
        Assert.assertEquals("2.50", parameter.limit());
    }

    @Test
    public void test() throws Exception {
        InputStream is = MqiTranslatorTest.class.getResourceAsStream("BC1901BH.mqi");
        MqiFile mqiFile = new MqiTranslator().translate(new InputStreamSource(is, new URI("urn:local")), new MqiFileDestination());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream os = baos;

        new XmlTranslator().translate(new PartSource(mqiFile), new OutputStreamDestination(os, new URI("urn:000:000")));
        os.close();
        System.out.println(baos.size());
    }
}
