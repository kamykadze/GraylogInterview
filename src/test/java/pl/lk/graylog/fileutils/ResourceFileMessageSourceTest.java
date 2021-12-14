package pl.lk.graylog.fileutils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lk.graylog.fileutils.exception.FileMessageSourceException;

import static org.junit.jupiter.api.Assertions.*;

class ResourceFileMessageSourceTest {

    private ResourceFileMessageSource toTest;

    @BeforeEach
    public void setUp() {
        toTest = new ResourceFileMessageSource();
    }

    @Test
    public void testThrowsExceptionOpeningWrongResourceFile() {
        assertThrows(FileMessageSourceException.class, () -> toTest.open("IamNotHere.txt"));
    }

    @Test
    public void testThrowsExceptionOpeningNullResourceFile() {
        assertThrows(FileMessageSourceException.class, () -> toTest.open(null));
    }

    @Test
    public void testThrowsExceptionOpeningSourceTwice() {
        toTest.open("test-messages.txt");
        assertThrows(FileMessageSourceException.class, () -> toTest.open("test-messages.txt"));
    }

    @Test
    public void testReadsFileLinesProperly() {
        toTest.open("test-messages.txt");
        assertTrue(toTest.hasNextMessage());
        assertEquals("Line1", toTest.getNextMessage());
        assertTrue(toTest.hasNextMessage());
        assertEquals("Line2", toTest.getNextMessage());
    }
}