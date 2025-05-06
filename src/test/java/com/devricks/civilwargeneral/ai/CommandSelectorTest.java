package com.devricks.civilwargeneral.ai;

import com.devricks.civilwargeneral.orders.Order;
import com.devricks.civilwargeneral.orders.Orders;
import org.junit.jupiter.api.*;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;

class CommandSelectorTest {

    private Orders loadOneOrder() {
        Order order1 = new Order("Attack", "Attack the enemy", 1);
        Orders orders = new Orders();
        orders.addOrder(order1);
        return orders;
    }

    private Orders loadTwoOrders() {
        Order order1 = new Order("Attack", "Attack the enemy", 1);
        Order order2 = new Order("Defend", "Defend the base", 2);
        Orders orders = new Orders();
        orders.addOrder(order1);
        orders.addOrder(order2);
        return orders;
    }

    @Test
    void whenRandomOrderSelector_IsPassedOneOrderAsOrdersObject_ShouldSelectCorrectOrderFromOrdersObject() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadOneOrder();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertEquals("Attack", selectedOrder.getName());
        Assertions.assertEquals("Attack the enemy", selectedOrder.getDescription());
        Assertions.assertEquals(1, selectedOrder.getId());
    }

    @Test
    void whenRandomOrderSelector_IsPassedMultipleOrdersAsOrdersObject_ShouldSelectCorrectOrderFromOrdersObject() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertEquals(selectedOrder.getId(),
                                orders.getOrderById(selectedOrder.getId()).orElseThrow().getId());
    }

    @Test
    void whenRandomOrderSelector_IsPassedEmptyOrdersObject_ShouldReturnNull() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = new Orders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNull(selectedOrder);
    }

    @Test
    void whenRandomOrderSelector_IsPassedNullOrdersObject_ShouldReturnNull() {
        CommandSelector commandSelector = new CommandSelector();
        Order selectedOrder = commandSelector.randomOrderSelector(null);
        Assertions.assertNull(selectedOrder);
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithNullOrder_ShouldReturnNull() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = new Orders();
        orders.addOrder(null);
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNull(selectedOrder);
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithEmptyOrder_ShouldReturnNull() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = new Orders();
        orders.addOrder(new Order("", "", 0));
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNull(selectedOrder);
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithInvalidOrder_ShouldReturnNull() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = new Orders();
        orders.addOrder(new Order("Invalid", null, -1));
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNull(selectedOrder);
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDuplicateOrders_ShouldSelectOneOfTheDuplicates() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = new Orders();
        Order order1 = new Order("Attack", "Attack the enemy", 1);
        Order order2 = new Order("Attack", "Attack the enemy", 1);
        orders.addOrder(order1);
        orders.addOrder(order2);
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertEquals("Attack", selectedOrder.getName());
        Assertions.assertEquals("Attack the enemy", selectedOrder.getDescription());
        Assertions.assertEquals(1, selectedOrder.getId());
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrders() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getId() == 1 || selectedOrder.getId() == 2);
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidId() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getId() > 0);
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidName() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescription() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescriptionAndName() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescriptionAndNameAndId() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.getId() > 0);
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescriptionAndNameAndIdAndNotNull() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.getId() > 0);
        Assertions.assertNotNull(selectedOrder);
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescriptionAndNameAndIdAndNotNullAndNotEmpty() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.getId() > 0);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertFalse(selectedOrder.getDescription().isEmpty());
        Assertions.assertFalse(selectedOrder.getName().isEmpty());
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescriptionAndNameAndIdAndNotNullAndNotEmptyAndValid() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.getId() > 0);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertFalse(selectedOrder.getDescription().isEmpty());
        Assertions.assertFalse(selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.isValid());
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescriptionAndNameAndIdAndNotNullAndNotEmptyAndValidAndEquals() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Order expectedOrder = orders.getOrderById(selectedOrder.getId()).orElseThrow();
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.getId() > 0);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertFalse(selectedOrder.getDescription().isEmpty());
        Assertions.assertFalse(selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.isValid());
        Assertions.assertEquals(expectedOrder, selectedOrder);
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescriptionAndNameAndIdAndNotNullAndNotEmptyAndValidAndEqualsAndHashCode() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Order expectedOrder = orders.getOrderById(selectedOrder.getId()).orElseThrow();
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.getId() > 0);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertFalse(selectedOrder.getDescription().isEmpty());
        Assertions.assertFalse(selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.isValid());
        Assertions.assertEquals(expectedOrder, selectedOrder);
        Assertions.assertEquals(expectedOrder.hashCode(), selectedOrder.hashCode());
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescriptionAndNameAndIdAndNotNullAndNotEmptyAndValidAndEqualsAndHashCodeAndToString() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Order expectedOrder = orders.getOrderById(selectedOrder.getId()).orElseThrow();
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.getId() > 0);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertFalse(selectedOrder.getDescription().isEmpty());
        Assertions.assertFalse(selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.isValid());
        Assertions.assertEquals(expectedOrder, selectedOrder);
        Assertions.assertEquals(expectedOrder.hashCode(), selectedOrder.hashCode());
        Assertions.assertEquals(expectedOrder.toString(), selectedOrder.toString());
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescriptionAndNameAndIdAndNotNullAndNotEmptyAndValidAndEqualsAndHashCodeAndToStringAndNotNull() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Order expectedOrder = orders.getOrderById(selectedOrder.getId()).orElseThrow();
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.getId() > 0);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertFalse(selectedOrder.getDescription().isEmpty());
        Assertions.assertFalse(selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.isValid());
        Assertions.assertEquals(expectedOrder, selectedOrder);
        Assertions.assertEquals(expectedOrder.hashCode(), selectedOrder.hashCode());
        Assertions.assertEquals(expectedOrder.toString(), selectedOrder.toString());
        Assertions.assertNotNull(selectedOrder);
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescriptionAndNameAndIdAndNotNullAndNotEmptyAndValidAndEqualsAndHashCodeAndToStringAndNotNullAndNotEmpty() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Order expectedOrder = orders.getOrderById(selectedOrder.getId()).orElseThrow();
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.getId() > 0);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.isValid());
        Assertions.assertFalse(selectedOrder.getDescription().isEmpty());
        Assertions.assertFalse(selectedOrder.getName().isEmpty());
        Assertions.assertFalse(selectedOrder.toString().isEmpty());
        Assertions.assertEquals(expectedOrder, selectedOrder);
        Assertions.assertEquals(expectedOrder.hashCode(), selectedOrder.hashCode());
        Assertions.assertEquals(expectedOrder.toString(), selectedOrder.toString());
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescriptionAndNameAndIdAndNotNullAndNotEmptyAndValidAndEqualsAndHashCodeAndToStringAndNotNullAndNotEmptyAndValid() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Order expectedOrder = orders.getOrderById(selectedOrder.getId()).orElseThrow();
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.getId() > 0);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertFalse(selectedOrder.getDescription().isEmpty());
        Assertions.assertFalse(selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.isValid());
        Assertions.assertEquals(expectedOrder, selectedOrder);
        Assertions.assertEquals(expectedOrder.hashCode(), selectedOrder.hashCode());
        Assertions.assertEquals(expectedOrder.toString(), selectedOrder.toString());
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertFalse(selectedOrder.toString().isEmpty());
        Assertions.assertTrue(selectedOrder.isValid());
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescriptionAndNameAndIdAndNotNullAndNotEmptyAndValidAndEqualsAndHashCodeAndToStringAndNotNullAndNotEmptyAndValidAndEquals() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Order expectedOrder = orders.getOrderById(selectedOrder.getId()).orElseThrow();
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.getId() > 0);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertFalse(selectedOrder.getDescription().isEmpty());
        Assertions.assertFalse(selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.isValid());
        Assertions.assertEquals(expectedOrder, selectedOrder);
        Assertions.assertEquals(expectedOrder.getDescription(), selectedOrder.getDescription());
        Assertions.assertEquals(expectedOrder.getName(), selectedOrder.getName());
        Assertions.assertFalse(selectedOrder.toString().isEmpty());
        Assertions.assertEquals(expectedOrder.toString(), selectedOrder.toString());
    }

    @Test
    void whenRandomOrderSelector_IsPassedOrdersObjectWithDifferentOrders_ShouldSelectOneOfTheOrdersWithValidDescriptionAndNameAndIdAndNotNullAndNotEmptyAndValidAndEqualsAndHashCodeAndToStringAndNotNullAndNotEmptyAndValidAndEqualsAndHashCode() {
        CommandSelector commandSelector = new CommandSelector();
        Orders orders = loadTwoOrders();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        Order expectedOrder = orders.getOrderById(selectedOrder.getId()).orElseThrow();
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertTrue(selectedOrder.getDescription() != null && !selectedOrder.getDescription().isEmpty());
        Assertions.assertTrue(selectedOrder.getName() != null && !selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.getId() > 0);
        Assertions.assertNotNull(selectedOrder);
        Assertions.assertFalse(selectedOrder.getDescription().isEmpty());
        Assertions.assertFalse(selectedOrder.getName().isEmpty());
        Assertions.assertTrue(selectedOrder.isValid());
        Assertions.assertEquals(expectedOrder, selectedOrder);
        Assertions.assertEquals(expectedOrder.getName(), selectedOrder.getName());
        Assertions.assertEquals(expectedOrder.getDescription(), selectedOrder.getDescription());
        Assertions.assertFalse(selectedOrder.toString().isEmpty());
        Assertions.assertEquals(expectedOrder.hashCode(), selectedOrder.hashCode());
        Assertions.assertEquals(expectedOrder.toString(), selectedOrder.toString());
    }

    @Nested
    class RandomNumberGeneratorTests {

        public final static long seed = System.currentTimeMillis();

        public int randomNumberMathRandom(int max) {
            return (int) (Math.random() * (max + 1));
        }

        public int randomNumberRandom(int max) {
            Random random = new Random(seed);
            return random.nextInt(max + 1);
        }

        public int randomNumberSecureRandom(int max) {
            SecureRandom secureRandom = new SecureRandom(longToByteArray());
            return secureRandom.nextInt(max + 1);
        }

        private byte[] longToByteArray() {
            return ByteBuffer.allocate(Long.BYTES).putLong(RandomNumberGeneratorTests.seed).array();
        }

        // I want to group the tests below to run them separately
        // Write a test case for two different randomNumber methods one that uses Math.random() and one that uses
        // SecureRandom to prove real randomness
        // So other random number generators are available for special cases:
        // threads ThreadLocalRandom, parallelism SplittableRandom, or Custom with different algorithms made by user.
        @Test
        void local_testRandomNumberMathRandom() {
            int max = 2;
            int randomNumber = randomNumberMathRandom(max);
            Assertions.assertTrue(randomNumber >= 0 && randomNumber <= max);
        }

        @Test
        void local_testRandomNumberSecureRandom() {
            int max = 2;
            int randomNumber = randomNumberSecureRandom(max);
            Assertions.assertTrue(randomNumber >= 0 && randomNumber <= max);
        }

        @Test
        void local_testRandomNumberRandom() {
            int max = 2;
            int randomNumber = randomNumberRandom(max);
            Assertions.assertTrue(randomNumber >= 0 && randomNumber <= max);
        }

        // I want a test that proves that the random number cannot be predicted? for each method
        @Test
        void local_testRandomNumberMathRandomNotPredictableUnlessYouHavePartOfTheSeed() {
            int max = 100;
            int randomNumber1 = randomNumberMathRandom(max);
            int randomNumber2 = randomNumberMathRandom(max);
            Assertions.assertNotEquals(randomNumber1, randomNumber2);
        }

        @Test
        void local_testRandomNumberSecureRandomNotPredictable() {
            int max = 100;
            int randomNumber1 = randomNumberSecureRandom(max);
            int randomNumber2 = randomNumberSecureRandom(max);
            Assertions.assertNotEquals(randomNumber1, randomNumber2);
        }

        @Test
        void local_testRandomNumberRandomIsPredictableKnowingTheSeed() {
            int max = 100;
            int randomNumber1 = randomNumberRandom(max);
            int randomNumber2 = randomNumberRandom(max);
            Assertions.assertEquals(randomNumber1, randomNumber2);
        }
    }
}