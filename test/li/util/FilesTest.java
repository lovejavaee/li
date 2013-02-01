package li.util;

import java.io.File;

import li.test.BaseTest;

import org.junit.Test;

public class FilesTest extends BaseTest {
    private static final Log log = Log.init();

    @Test
    public void root() {
        log.debug(Files.root());
    }

    @Test
    public void list() {
        log.debug(Files.list(Files.root(), ".*", true));
    }

    @Test
    public void load() {
        log.debug(Files.load(Files.list(Files.root(), ".xml", true).get(0)));
    }

    @Test
    public void read() {
        log.debug(Files.read(new File(Files.list(Files.root(), ".xml", true).get(0))));
    }

    @Test
    public void write() {
        log.debug("li.util.FilesTest.write()");
    }

    @Test
    public void xpath() {
        log.debug("li.util.FilesTest.xpath()");
    }
}