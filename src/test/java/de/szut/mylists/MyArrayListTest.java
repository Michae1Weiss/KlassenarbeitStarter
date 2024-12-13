package de.szut.mylists;

// unique entry point to get access to all assertThat methods and utility methods (e.g. entry)
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MyArrayListTest {
    private MyArrayList arrayList;

    @BeforeEach
    public void setup() {
        arrayList = new MyArrayList();
    }

    /* Testing size method */

    @Test
    public void givenEmptyArrayList_whenSize_thenZero() {
        /* Arrange */
        int expected = 0;
        /* Act     */
        int result = this.arrayList.size();
        /* Assert  */
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void givenArrayListHasThreeEntries_whenSize_thenThree() {
        /* Arrange */
        // add three numbers
        this.arrayList.add(0);
        this.arrayList.add(0);
        this.arrayList.add(0);
        this.arrayList.size();
        int expected = 3;
        /* Act     */
        int result = this.arrayList.size();
        /* Assert  */
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"3:3", "5:5", "7:7", "20:20", "30:30", "100:100"}, delimiter = ':')
    public void givenArrayListHasNEntries_whenSize_thenN(int n, int expected) {
        /* Arrange */
        // add N numbers
        for (int i = 0; i < n; i++) {
            this.arrayList.add(i);
        }
        /* Act     */
        int result = this.arrayList.size();
        /* Assert  */
        assertThat(result).isEqualTo(expected);
    }

    /* Testing add method */

    @Test
    public void givenEmptyArrayList_whenAdd1_thenSize1() {
        /* Arrange */
        int expected = 1;
        /* Act     */
        this.arrayList.add(0);
        /* Assert  */
        int actual = this.arrayList.size();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"3", "5", "7", "100", "1234567890", "-100"})
    public void givenEmptyArrayList_whenAddNumber_thenSize1(int number) {
        /* Arrange */
        int expected = 1;
        /* Act     */
        this.arrayList.add(number);
        /* Assert  */
        int actual = this.arrayList.size();
        assertThat(actual).isEqualTo(expected);
    }

    /* Testing get method */

    @Test
    public void givenEmptyArrayList_whenGetIndex0_thenException() {
        /* Arrange */
        int index = 0;  // this index does not exist
        /* Act     */
        /* Assert  */
        assertThrows(IllegalArgumentException.class, () -> this.arrayList.get(index));
    }

    @ParameterizedTest
    @CsvSource(value = {"-1", "5", "7", "100", "1234567890", "-100"})
    public void givenEmptyArrayList_whenGetIndexI_thenException(int i) {
        /* Arrange */
        /* Act     */
        /* Assert  */
        assertThrows(IllegalArgumentException.class, () -> this.arrayList.get(i));
    }

    @ParameterizedTest
    @CsvSource(value = {"3", "5", "7", "100", "1234567890", "-100"})
    public void givenArrayListWithNumberX_whenGetNumber_thenX(int x) {
        /* Arrange */
        int expected = x;
        this.arrayList.add(x);
        /* Act     */
        int actual = this.arrayList.get(0);
        /* Assert  */
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void givenArrayListWithTenEntries_whenGetIndexNegative_thenException() {
        /* Arrange */
        // add 10 entries
        for (int i = 0; i < 10; i++) {
            this.arrayList.add(i);
        }
        int index = -1;  // this index does not exist
        /* Act     */
        /* Assert  */
        assertThrows(IllegalArgumentException.class, () -> this.arrayList.get(index));
    }

    /* Testing remove method */

    @Test
    public void givenEmptyArrayList_whenRemoveIndex0_thenException() {
        /* Arrange */
        int index = 0;  // this index does not exist
        /* Act     */
        /* Assert  */
        assertThrows(IllegalArgumentException.class, () -> this.arrayList.remove(index));
    }

    @Test
    public void givenArrayListWithTenEntries_whenRemoveIndex0_thenSize9() {
        /* Arrange */
        // add 10 entries
        for (int i = 0; i < 10; i++) {
            this.arrayList.add(i);
        }
        int expected = 9;  // this index does not exist
        /* Act     */
        this.arrayList.remove(0);
        /* Assert  */
        int result = this.arrayList.size();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void givenArrayListWithTenEntries_whenRemoveIndex0TenTimes_thenSize0() {
        /* Arrange */
        // add 10 entries
        for (int i = 0; i < 10; i++) {
            this.arrayList.add(i);
        }
        int expected = 0;  // this index does not exist
        /* Act     */
        for (int i = 0; i < 10; i++) {
            this.arrayList.remove(0);
        }
        /* Assert  */
        int result = this.arrayList.size();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void givenArrayListWith100and200_whenRemoveIndex0_thenFirstElementIs200() {
        /* Arrange */
        this.arrayList.add(100);
        this.arrayList.add(200);
        int expected = 200;  // this index does not exist
        /* Act     */
        this.arrayList.remove(0);
        /* Assert  */
        int result = this.arrayList.get(0);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void givenArrayListWithTwoElement_whenRemoveLastIndexTwoTimes_thenException() {
        /* Arrange */
        this.arrayList.add(10);
        this.arrayList.add(20);
        /* Act     */
        this.arrayList.remove(1);
        /* Assert  */
        assertThrows(IllegalArgumentException.class, () -> this.arrayList.remove(1));
    }

    @Test
    public void givenArrayListWithTenEntries_whenRemoveNegativeIndex_thenException() {
        /* Arrange */
        // add 10 entries
        for (int i = 0; i < 10; i++) {
            this.arrayList.add(i);
        }
        int negativeIndex = -1;  // this index does not exist
        /* Act     */
        /* Assert  */
        assertThrows(IllegalArgumentException.class, () -> this.arrayList.remove(negativeIndex));
    }

    /* Testing contains method */

    @Test
    public void givenEmptyArrayList_whenContains0_thenFalse() {
        /* Arrange */
        /* Act     */
        boolean result = this.arrayList.contains(0);
        /* Assert  */
        assertThat(result).isFalse();
    }

    @Test
    public void givenArrayListWithNumber123_whenContains123_thenTrue() {
        /* Arrange */
        int n = 123;
        this.arrayList.add(n);
        /* Act     */
        boolean result = this.arrayList.contains(n);
        /* Assert  */
        assertThat(result).isTrue();
    }

    @Test
    public void givenArrayListWithNumberMinus100_whenContainsMinus100_thenTrue() {
        /* Arrange */
        int n = -100;
        this.arrayList.add(n);
        /* Act     */
        boolean result = this.arrayList.contains(n);
        /* Assert  */
        assertThat(result).isTrue();
    }

    @Test
    public void givenArrayListWithTenNumbers_whenContainsFirstNumber_thenTrue() {
        /* Arrange */
        // add 10 entries
        for (int i = 0; i < 10; i++) {
            this.arrayList.add(i);
        };
        /* Act     */
        int firstNumber = this.arrayList.get(0);
        boolean result = this.arrayList.contains(firstNumber);
        /* Assert  */
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"3", "5", "23", "100", "202", "1001"})
    public void givenArrayListWithNNumbers_whenContainsFirstNumber_thenTrue(int n) {
        /* Arrange */
        // add 10 entries
        for (int i = 0; i < n; i++) {
            this.arrayList.add(i);
        };
        /* Act     */
        int firstNumber = this.arrayList.get(0);
        boolean result = this.arrayList.contains(firstNumber);
        /* Assert  */
        assertThat(result).isTrue();
    }

    @Test
    public void givenArrayListWithTenNumbers_whenContainsLastNumber_thenTrue() {
        /* Arrange */
        // add 10 entries
        for (int i = 0; i < 10; i++) {
            this.arrayList.add(i);
        };
        /* Act     */
        int lastNumber = this.arrayList.get(9);
        boolean result = this.arrayList.contains(lastNumber);
        /* Assert  */
        assertThat(result).isTrue();
    }
}
