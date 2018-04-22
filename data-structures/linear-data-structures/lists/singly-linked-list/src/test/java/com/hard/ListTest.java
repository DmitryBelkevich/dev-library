package com.hard;

import org.junit.Assert;
import org.junit.Test;

public class ListTest {
    public static class Size {
        @Test
        public void should_be_size_equals_1() {
            List<String> list = new LinkedList<>();

            list.add("a");

            Assert.assertEquals(1, list.size());
        }

        @Test
        public void should_be_size_equals_2() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");

            Assert.assertEquals(2, list.size());
        }

        @Test
        public void should_be_size_equals_3() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            Assert.assertEquals(3, list.size());
        }
    }

    public static class Get {
        @Test
        public void should_return_first_element() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            Assert.assertEquals("a", list.get(0));
        }

        @Test
        public void should_return_second_element() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            Assert.assertEquals("b", list.get(1));
        }

        @Test
        public void should_return_third_element() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            Assert.assertEquals("c", list.get(2));
        }

        @Test
        public void should_not_return_not_existed_element() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            Assert.assertNull(list.get(3));
        }
    }

    public static class Add {
        @Test
        public void should_add_one_element() {
            List<String> list = new LinkedList<>();

            list.add("a");

            Assert.assertEquals(1, list.size());
            Assert.assertEquals("a", list.get(0));
        }

        @Test
        public void should_add_two_elements() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");

            Assert.assertEquals(2, list.size());
            Assert.assertEquals("a", list.get(0));
            Assert.assertEquals("b", list.get(1));
        }

        @Test
        public void should_add_three_elements() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            Assert.assertEquals(3, list.size());
            Assert.assertEquals("a", list.get(0));
            Assert.assertEquals("b", list.get(1));
            Assert.assertEquals("c", list.get(2));
        }
    }

    public static class AddByIndex {
        @Test
        public void should_add_one_element_into_empty_array_by_index_0() {
            List<String> list = new LinkedList<>();

            list.add(0, "a");

            Assert.assertEquals(1, list.size());
            Assert.assertEquals("a", list.get(0));
        }

        @Test
        public void should_add_one_element_into_empty_array_by_index_1() {
            List<String> list = new LinkedList<>();

            list.add(1, "a");

            Assert.assertEquals(1, list.size());
            Assert.assertEquals("a", list.get(0));
        }

        @Test
        public void should_add_one_element_by_index_0_into_begin() {
            List<String> list = new LinkedList<>();

            list.add("b");
            list.add("c");

            list.add(0, "a");

            Assert.assertEquals(3, list.size());
            Assert.assertEquals("a", list.get(0));
            Assert.assertEquals("b", list.get(1));
            Assert.assertEquals("c", list.get(2));
        }

        @Test
        public void should_add_one_element_by_index_1_into_middle() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("c");
            list.add("d");

            list.add(1, "b");

            Assert.assertEquals(4, list.size());
            Assert.assertEquals("a", list.get(0));
            Assert.assertEquals("b", list.get(1));
            Assert.assertEquals("c", list.get(2));
            Assert.assertEquals("d", list.get(3));
        }

        @Test
        public void should_add_one_element_by_index_2_into_middle() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("d");

            list.add(2, "c");

            Assert.assertEquals(4, list.size());
            Assert.assertEquals("a", list.get(0));
            Assert.assertEquals("b", list.get(1));
            Assert.assertEquals("c", list.get(2));
            Assert.assertEquals("d", list.get(3));
        }

        @Test
        public void should_add_one_element_by_index_more_than_size_array_into_end() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");

            list.add(2, "c");

            Assert.assertEquals(3, list.size());
            Assert.assertEquals("a", list.get(0));
            Assert.assertEquals("b", list.get(1));
            Assert.assertEquals("c", list.get(2));
        }
    }

    public static class DeleteByIndex {
        @Test
        public void should_not_delete_element_by_index_from_empty_list() {
            List<String> list = new LinkedList<>();

            list.remove(0);

            Assert.assertEquals(0, list.size());
        }

        @Test
        public void should_delete_single_element_by_index_from_1_elements() {
            List<String> list = new LinkedList<>();

            list.add("a");

            list.remove(0);

            Assert.assertEquals(0, list.size());
        }

        @Test
        public void should_delete_first_element_by_index_from_3_elements() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            list.remove(0);

            Assert.assertEquals(2, list.size());
            Assert.assertEquals("b", list.get(0));
            Assert.assertEquals("c", list.get(1));
        }

        @Test
        public void should_delete_second_element_by_index_from_3_elements() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            list.remove(1);

            Assert.assertEquals(2, list.size());
            Assert.assertEquals("a", list.get(0));
            Assert.assertEquals("c", list.get(1));
        }

        @Test
        public void should_delete_third_element_by_index_from_3_elements() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            list.remove(2);

            Assert.assertEquals(2, list.size());
            Assert.assertEquals("a", list.get(0));
            Assert.assertEquals("b", list.get(1));
        }

        @Test
        public void should_not_delete_not_existed_element_by_index() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            list.remove(3);

            Assert.assertEquals(3, list.size());
            Assert.assertEquals("a", list.get(0));
            Assert.assertEquals("b", list.get(1));
            Assert.assertEquals("c", list.get(2));
        }
    }

    public static class DeleteByData {
        @Test
        public void should_not_delete_element_by_data_from_empty_list() {
            List<String> list = new LinkedList<>();

            list.remove("a");

            Assert.assertEquals(0, list.size());
        }

        @Test
        public void should_delete_single_element_by_data_from_1_elements() {
            List<String> list = new LinkedList<>();

            list.add("a");

            list.remove("a");

            Assert.assertEquals(0, list.size());
        }

        @Test
        public void should_delete_first_element_by_data_from_3_elements() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            list.remove("a");

            Assert.assertEquals(2, list.size());
            Assert.assertEquals("b", list.get(0));
            Assert.assertEquals("c", list.get(1));
        }

        @Test
        public void should_delete_second_element_by_data_from_3_elements() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            list.remove("b");

            Assert.assertEquals(2, list.size());
            Assert.assertEquals("a", list.get(0));
            Assert.assertEquals("c", list.get(1));
        }

        @Test
        public void should_delete_third_element_by_data_from_3_elements() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            list.remove("c");

            Assert.assertEquals(2, list.size());
            Assert.assertEquals("a", list.get(0));
            Assert.assertEquals("b", list.get(1));
        }

        @Test
        public void should_not_delete_not_existed_element_by_data() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            list.remove("d");

            Assert.assertEquals(3, list.size());
            Assert.assertEquals("a", list.get(0));
            Assert.assertEquals("b", list.get(1));
            Assert.assertEquals("c", list.get(2));
        }
    }

    public static class Print {
        @Test
        public void should_print_all_3_elements() {
            List<String> list = new LinkedList<>();

            list.add("a");
            list.add("b");
            list.add("c");

            list.print();
        }
    }
}
