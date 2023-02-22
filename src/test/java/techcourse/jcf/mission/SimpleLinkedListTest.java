package techcourse.jcf.mission;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("링크드 리스트")
class SimpleLinkedListTest {
    @DisplayName("추가한다. - version01")
    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c", "ab", "bc", "ac", "bc"})
    void add_v1(final String value) {
        final SimpleList linkedList = new SimpleLinkedList();
        final boolean isAdded = linkedList.add(value);

        assertThat(isAdded).isTrue();
    }

    @DisplayName("추가한다. - version02")
    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c", "ab", "bc", "ac", "bc"})
    void add_v2(final String value) {
        final SimpleList linkedList = new SimpleLinkedList();
        linkedList.add("x");
        linkedList.add("y");
        linkedList.add(1, value);
        final String findValue = linkedList.get(1);

        assertThat(findValue).isEqualTo(value);
    }

    @DisplayName("해당 인덱스에 해당하는 객체에 값을 저장하고 값을 반환한다.")
    @ParameterizedTest
    @MethodSource("stringsDummy")
    void set(final List<String> values) {
        final String changeValue = "변경된 값";
        final SimpleList linkedList = new SimpleLinkedList();
        values.forEach(linkedList::add);
        final String setValue = linkedList.set(0, changeValue);

        assertThat(setValue).isEqualTo(changeValue);
    }

    @DisplayName("가져온다.")
    @ParameterizedTest
    @MethodSource("stringsDummy")
    void get(final List<String> values) {
        final SimpleList linkedList = new SimpleLinkedList();
        values.forEach(linkedList::add);
        final String findValue = linkedList.get(0);

        assertThat(findValue).isEqualTo(findValue);
    }

    @DisplayName("범위를 넘은 인덱스를 통해서 가져올 경우 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("stringsDummy")
    void throwExceptionWhenGetIndexOutOfRange(final List<String> values) {
        final SimpleList linkedList = new SimpleLinkedList();
        values.forEach(linkedList::add);

        assertThatThrownBy(() -> linkedList.get(100000000))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("포함한다.")
    @ParameterizedTest
    @MethodSource("stringsWithTargetDummy")
    void contains(final List<String> values, final String targetValue) {
        final SimpleList linkedList = new SimpleLinkedList();
        values.forEach(linkedList::add);
        final boolean isContainTrue = linkedList.contains(targetValue);

        assertThat(isContainTrue).isTrue();
    }

    @DisplayName("값이 저장된 인덱스를 가져온다.")
    @ParameterizedTest
    @MethodSource("stringsWithTargetIndexDummy")
    void indexOf(final List<String> values, final String targetValue, final int targetIndex) {
        final SimpleList linkedList = new SimpleLinkedList();
        values.forEach(linkedList::add);
        final int findIndex = linkedList.indexOf(targetValue);

        assertThat(findIndex).isEqualTo(targetIndex);
    }

    @DisplayName("저장되지 않은 값을 indexOf로 조회할 경우 예외가 발생한다.")
    @Test
    void throwExceptionWhenValueNotExistsByIndexOf() {
        final SimpleList linkedList = new SimpleLinkedList();

        assertThatThrownBy(() -> linkedList.indexOf("존재하지 않는 값"))
                .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("저장된 데이터 목록 사이즈를 가져온다.")
    @ParameterizedTest
    @MethodSource("stringsWithSizeDummy")
    void size(final List<String> values, final int expectedSize) {
        final SimpleList linkedList = new SimpleLinkedList();
        values.forEach(linkedList::add);
        final int linkedListSize = linkedList.size();

        assertThat(linkedListSize).isEqualTo(expectedSize);
    }

    @DisplayName("링크드 리스트에 저장된 데이터가 없을 경우 사이즈로 0을 가져온다.")
    @Test
    void getZeroSizeWhenLinkedListIsEmpty() {
        final SimpleList linkedList = new SimpleLinkedList();
        final int linkedListSize = linkedList.size();

        assertThat(linkedListSize).isZero();
    }

    @DisplayName("데이터 목록이 비어있는지 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c", "ab", "bc", "ac", "bc"})
    void isEmpty(final String value) {
        final SimpleList linkedList = new SimpleLinkedList();
        linkedList.add(value);
        final boolean isLinkedListEmpty = linkedList.isEmpty();

        assertThat(isLinkedListEmpty).isFalse();
    }

    @DisplayName("존재하는 특정 값을 삭제한다.")
    @ParameterizedTest
    @MethodSource("stringsWithTargetDummy")
    void remove(final List<String> values, final String targetValue) {
        final SimpleList linkedList = new SimpleLinkedList();
        values.forEach(linkedList::add);
        final boolean isRemoved = linkedList.remove(targetValue);

        assertThat(isRemoved).isTrue();
    }

    @DisplayName("존재하지 않는 특정 값을 삭제한다.")
    @ParameterizedTest
    @MethodSource("stringsDummy")
    void removeValueNotExists(final List<String> values) {
        final SimpleList linkedList = new SimpleLinkedList();
        values.forEach(linkedList::add);
        final boolean isRemoved = linkedList.remove("존재하지 않는 값");

        assertThat(isRemoved).isFalse();
    }

    @DisplayName("특정 인덱스에 있는 값을 삭제한다.")
    @ParameterizedTest
    @MethodSource("stringsWithTargetIndexDummy")
    void removeByIndex(final List<String> values, final String targetValue, final int targetIndex) {
        final SimpleList linkedList = new SimpleLinkedList();
        values.forEach(linkedList::add);
        final String removedValue = linkedList.remove(targetIndex);

        assertThat(removedValue).isEqualTo(targetValue);
    }

    @DisplayName("특정 인덱스가 가능한 범위를 넘어갈 경우 예외가 발생한다.")
    @Test
    void throwExceptionWhenOverIndexRange() {
        final SimpleList linkedList = new SimpleLinkedList();

        assertThatThrownBy(() -> linkedList.remove(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("모든 데이터를 비운다.")
    @ParameterizedTest
    @MethodSource("stringsDummy")
    void clear(final List<String> values) {
        final SimpleList linkedList = new SimpleLinkedList();
        values.forEach(linkedList::add);
        linkedList.clear();
        final int linkedListSize = linkedList.size();

        assertThat(linkedListSize).isZero();
    }

    static Stream<Arguments> stringsDummy() {
        return Stream.of(
                Arguments.arguments(List.of("a")),
                Arguments.arguments(List.of("a", "b")),
                Arguments.arguments(List.of("a", "b", "c")),
                Arguments.arguments(List.of("a", "b", "c", "d")),
                Arguments.arguments(List.of("a", "b", "c", "d", "e")),
                Arguments.arguments(List.of("a", "b", "c", "d", "e", "f"))
        );
    }

    static Stream<Arguments> stringsWithTargetDummy() {
        return Stream.of(
                Arguments.arguments(List.of("a"), "a"),
                Arguments.arguments(List.of("a", "b"), "b"),
                Arguments.arguments(List.of("a", "b", "c"), "b"),
                Arguments.arguments(List.of("a", "b", "c", "d"), "c"),
                Arguments.arguments(List.of("a", "b", "c", "d", "e"), "d"),
                Arguments.arguments(List.of("a", "b", "c", "d", "e", "f"), "f")
        );
    }

    static Stream<Arguments> stringsWithTargetIndexDummy() {
        return Stream.of(
                Arguments.arguments(List.of("a"), "a", 0),
                Arguments.arguments(List.of("a", "b"), "b", 1),
                Arguments.arguments(List.of("a", "b", "c"), "c", 2),
                Arguments.arguments(List.of("a", "b", "c", "d"), "c", 2),
                Arguments.arguments(List.of("a", "b", "c", "d", "e"), "d", 3),
                Arguments.arguments(List.of("a", "b", "c", "d", "e", "f"), "f", 5)
        );
    }

    static Stream<Arguments> stringsWithSizeDummy() {
        return Stream.of(
                Arguments.arguments(List.of("a"), 1),
                Arguments.arguments(List.of("a", "b"), 2),
                Arguments.arguments(List.of("a", "b", "c"),3),
                Arguments.arguments(List.of("a", "b", "c", "d"),4),
                Arguments.arguments(List.of("a", "b", "c", "d", "e"), 5),
                Arguments.arguments(List.of("a", "b", "c", "d", "e", "f"), 6)
        );
    }
}