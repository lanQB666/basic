package org.example.Collection;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ArrayList测试类
 * @author LanQibin
 */
@DisplayName("ArrayList类测试")
class ArrayListTest {
    private List<String> list;

    @BeforeAll
    static void beforeAll() {
        System.out.println("开始测试ArrayList类");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("ArrayList类测试完成");
    }

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
        System.out.println("创建新的ArrayList实例");
    }

    @AfterEach
    void tearDown() {
        list = null;
        System.out.println("清理ArrayList实例");
    }

    @Test
    @DisplayName("测试添加元素到列表末尾")
    void testAdd() {
        assertTrue(list.add("测试1"));
        assertEquals(1, list.size());
        assertEquals("测试1", list.get(0));

        // 测试添加多个元素
        list.add("测试2");
        list.add("测试3");
        assertEquals(3, list.size());
        assertEquals("测试3", list.get(2));
    }

    @Test
    @DisplayName("测试在指定位置添加元素")
    void testAddAtIndex() {
        list.add("测试1");
        list.add("测试2");
        list.add(1, "测试3");
        
        assertEquals("测试1", list.get(0));
        assertEquals("测试3", list.get(1));
        assertEquals("测试2", list.get(2));
        assertEquals(3, list.size());

        // 测试在无效位置添加的异常
        assertAll(
            () -> assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "测试4")),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> list.add(4, "测试4"))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 1, 100})
    @DisplayName("测试获取无效索引的异常")
    void testGetWithInvalidIndex(int index) {
        list.add("测试1");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(index));
    }

    @Test
    @DisplayName("测试通过索引删除元素")
    void testRemoveByIndex() {
        // 准备测试数据
        list.add("测试1");
        list.add("测试2");
        list.add("测试3");

        assertEquals("测试2", list.remove(1));
        assertAll(
            () -> assertEquals(2, list.size()),
            () -> assertEquals("测试3", list.get(1))
        );
    }

    @Test
    @DisplayName("测试通过元素值删除")
    void testRemoveByElement() {
        // 准备测试数据
        list.add("测试1");
        list.add("测试2");
        list.add("测试3");

        assertAll(
            () -> assertTrue(list.remove("测试2")),
            () -> assertEquals(2, list.size()),
            () -> assertEquals("测试3", list.get(1)),
            () -> assertFalse(list.remove("不存在"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    @DisplayName("测试设置元素值")
    void testSet(String originalValue, String newValue) {
        list.add(originalValue);
        assertEquals(originalValue, list.set(0, newValue));
        assertEquals(newValue, list.get(0));
        
        // 测试索引越界情况
        assertAll(
            () -> assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "测试")),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, "测试"))
        );
    }

    static Stream<Arguments> provideTestData() {
        return Stream.of(
            Arguments.of("测试1", "新测试1"),
            Arguments.of("测试2", "新测试2"),
            Arguments.of("", "非空值")
        );
    }

    @Test
    @DisplayName("测试列表大小变化")
    void testSize() {
        assertAll(
            () -> assertEquals(0, list.size()),
            () -> {
                list.add("测试1");
                assertEquals(1, list.size());
            },
            () -> {
                list.add("测试2");
                assertEquals(2, list.size());
            },
            () -> {
                list.remove(0);
                assertEquals(1, list.size());
            }
        );
    }

    @Test
    @DisplayName("测试列表空状态")
    void testIsEmpty() {
        assertAll(
            () -> assertTrue(list.isEmpty()),
            () -> {
                list.add("测试1");
                assertFalse(list.isEmpty());
            },
            () -> {
                list.remove(0);
                assertTrue(list.isEmpty());
            }
        );
    }

    @Test
    @DisplayName("测试清空列表")
    void testClear() {
        list.add("测试1");
        list.add("测试2");
        list.clear();
        assertAll(
            () -> assertTrue(list.isEmpty()),
            () -> assertEquals(0, list.size())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"测试1", "不存在", ""})
    @DisplayName("测试元素包含判断")
    void testContains(String element) {
        assertFalse(list.contains(element));
        list.add("测试1");
        assertEquals("测试1".equals(element), list.contains(element));
    }

    @Test
    @DisplayName("测试查找元素索引")
    void testIndexOf() {
        assertEquals(-1, list.indexOf("测试1"));
        list.add("测试1");
        list.add("测试2");
        list.add("测试1");
        assertAll(
            () -> assertEquals(0, list.indexOf("测试1")),
            () -> assertEquals(1, list.indexOf("测试2")),
            () -> assertEquals(-1, list.indexOf("不存在"))
        );
    }

    @Test
    @DisplayName("测试列表扩容机制")
    void testResizeCapacity() {
        // 测试扩容机制
        int initialSize = 10;  // INIT_SIZE
        
        // 添加超过初始容量的元素，触发扩容
        for (int i = 0; i < 15; i++) {
            list.add("测试" + i);
        }
        
        assertAll(
            () -> assertEquals(15, list.size()),
            () -> {
                for (int i = 0; i < 15; i++) {
                    assertEquals("测试" + i, list.get(i));
                }
            }
        );
        
        // 继续添加元素，验证第二次扩容
        for (int i = 15; i < 25; i++) {
            list.add("测试" + i);
        }
        
        assertAll(
            () -> assertEquals(25, list.size()),
            () -> {
                for (int i = 0; i < 25; i++) {
                    assertEquals("测试" + i, list.get(i));
                }
            }
        );
    }
} 