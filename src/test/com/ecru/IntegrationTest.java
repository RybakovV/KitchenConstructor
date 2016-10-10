package test.com.ecru;

import com.ecru.DataBaseManager;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Rybakov Vitaliy on 12.09.2016.
 */
public class IntegrationTest {

    private ConfigurableInputStream in;
    private ByteArrayOutputStream out;
    private DataBaseManager manager;

    @Before
    public void setup(){
        in = new ConfigurableInputStream();
        out = new ByteArrayOutputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));


        manager.connectToDataBase("kitchenkonstructor", "root", "root");


    }


    private String getData() {
        try {
            return new String(out.toByteArray(), "UTF-8").replace("\r\n","\n");
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }
}
