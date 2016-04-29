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
    /*
    @Rule
    public ExpectedException exception = ExpectedException.none();
    */

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

    @Test public void insertAndDelete() {
        Long id = dao.insert(reading);
        assertThat(id, greaterThan(new Long(0)));
        dao.delete(CPUReading.class, id);
    }

    /*
    @Test public void shouldInsertAndDelete() {
        dao.insert(reading);
    }
    */
    /*
    @Test public void shouldRefuseEmptyString() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Can't parse an empty string!");
        parser.parse("");
    }

    @Test public void fieldsAreSetProperly() {
        parser.parse("10255984 0 0 10817544 0 303 1457 23 0 0 0 824 35 -0 36 -0 2629 68237 2931 1 2 97");
        Map<VMStatField, Integer> parsed = parser.getStats();

        assertThat(parsed.get(VMStatField.KTHREAD_RUN),    equalTo(10255984));
        assertThat(parsed.get(VMStatField.KTHREAD_BLOCK),  equalTo(0));
        assertThat(parsed.get(VMStatField.KTHREAD_WAIT),   equalTo(0));
        assertThat(parsed.get(VMStatField.MEM_SWAP),       equalTo(10817544));
        assertThat(parsed.get(VMStatField.MEM_FREE),       equalTo(0));
        assertThat(parsed.get(VMStatField.PAGE_RECLAIM),   equalTo(303));
        assertThat(parsed.get(VMStatField.PAGE_MINOR_FAULT), equalTo(1457));
        assertThat(parsed.get(VMStatField.PAGE_IN),        equalTo(23));
        assertThat(parsed.get(VMStatField.PAGE_OUT),       equalTo(0));
        assertThat(parsed.get(VMStatField.PAGE_FREED),     equalTo(0));
        assertThat(parsed.get(VMStatField.PAGE_SHORTFALL), equalTo(0));
        assertThat(parsed.get(VMStatField.PAGE_SCAN),      equalTo(824));
        assertThat(parsed.get(VMStatField.DISK0),          equalTo(35));
        assertThat(parsed.get(VMStatField.DISK1),          equalTo(0));
        assertThat(parsed.get(VMStatField.DISK2),          equalTo(36));
        assertThat(parsed.get(VMStatField.DISK3),          equalTo(0));
        assertThat(parsed.get(VMStatField.FAULT_IN),       equalTo(2629));
        assertThat(parsed.get(VMStatField.FAULT_SYSCALL),  equalTo(68237));
        assertThat(parsed.get(VMStatField.FAULT_CTX),      equalTo(2931));
        assertThat(parsed.get(VMStatField.CPU_USER),       equalTo(1));
        assertThat(parsed.get(VMStatField.CPU_SYS),        equalTo(2));
        assertThat(parsed.get(VMStatField.CPU_IDLE ),      equalTo(97));
    }

    @Test public void fieldIndicesFunction() {
        parser.parse("10255984 0 0 10817544 0 303 1457 23 0 0 0 824 35 -0 36 -0 2629 68237 2931 1 2 97");

        assertThat(parser.getStat(VMStatField.KTHREAD_RUN), equalTo(10255984));
        assertThat(parser.getStat(VMStatField.CPU_IDLE), equalTo(97));
    }

    @Test public void fieldEnumsFunction() {
        parser.parse("10255984 0 0 10817544 0 303 1457 23 0 0 0 824 35 -0 36 -0 2629 68237 2931 1 2 97");
        Map<VMStatField, Integer> fields = parser.getStats(new VMStatField[] {
                                             VMStatField.CPU_USER
                                           , VMStatField.CPU_SYS
                                           , VMStatField.CPU_IDLE });

        assertThat(fields.get(VMStatField.CPU_USER), equalTo(1));
        assertThat(fields.get(VMStatField.CPU_SYS), equalTo(2));
        assertThat(fields.get(VMStatField.CPU_IDLE), equalTo(97));
    }
    */
}
