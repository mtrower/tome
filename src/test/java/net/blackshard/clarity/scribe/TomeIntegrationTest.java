package net.blackshard.clarity.tome;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.experimental.categories.Category;
import org.junit.*;
//import org.junit.rules.ExpectedException;

import java.util.Date;

/**
 * Integration tests for Tome package.
 */
@Category(IntegrationTest.class)
public class TomeIntegrationTest {
    CPUReading reading;
    ReadingDAO<CPUReading> dao;

    @BeforeClass
    public static void setUpClass() {
        Library.open();
    }

    @AfterClass
    public static void tearDownClass() {
        Library.close();
    }

    @Before
    public void setUp() {
        reading = new CPUReading(1, new Date(), 10, 20, 70);
        dao = new ReadingDAOHibernate<CPUReading>();
    }

    @After
    public void tearDown() {
        dao = null;
        reading = null;
    }

    @Test public void insertVerify() {
        Long id = dao.insert(reading);
        assertThat(id, greaterThan(new Long(0)));

        CPUReading testReading = dao.get(CPUReading.class, id);

        assertThat(testReading.getMetricUser(), equalTo(reading.getMetricUser()));
        assertThat(testReading.getMetricSystem(), equalTo(reading.getMetricSystem()));
        assertThat(testReading.getMetricIdle(), equalTo(reading.getMetricIdle()));
    }
}
