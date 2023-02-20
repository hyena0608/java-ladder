package laddergame.domain;

import laddergame.fixture.BooleanGeneratorFixture;
import laddergame.fixture.HeightFixture;
import laddergame.fixture.WidthFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static laddergame.fixture.LineCreatorFixture.TEST_LINE_CREATOR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("라인 Creator")
class LineCreatorTest {
    @DisplayName("BooleanGenerator가 null이면 RandomBooleanGenerator를 주입한다.")
    @Test
    void throwExceptionWhenBooleanGeneratorIsNull() {
        final BooleanGenerator generator = null;
        assertDoesNotThrow(() -> new LineCreator(generator));
    }

    @DisplayName("createLines 메서드의 인자인 Width가 null일 경우 예외가 발생한다.")
    @Test
    void throwExceptionWhenWidthIsNull() {
        final LineCreator lineCreator = new LineCreator(BooleanGeneratorFixture.TEST_BOOLEAN_GENERATOR);
        final Width width = null;
        final Height height = HeightFixture.createHeightValue3();
        assertThatThrownBy(() -> lineCreator.createLines(width, height))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("createLines 메서드의 인자인 Height가 null일 경우 예외가 발생한다.")
    @Test
    void throwExceptionWhenHeightIsNull() {
        final LineCreator lineCreator = new LineCreator(BooleanGeneratorFixture.TEST_BOOLEAN_GENERATOR);
        final Width width = WidthFixture.createWidthValue3();
        final Height height = null;
        assertThatThrownBy(() -> lineCreator.createLines(width, height))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("라인 사이즈는 세로 길이와 같다.")
    @ParameterizedTest(name = "heightValue = {0}")
    @MethodSource("createLinesParameterDummy")
    void returnsLinesSizeIsHeight(final Width width, final Height height) {
        final Lines lines = TEST_LINE_CREATOR.createLines(width, height);
        final List<Line> findLines = lines.getLines();

        assertThat(findLines).hasSize(height.getValue());
    }

    static Stream<Arguments> createLinesParameterDummy() {
        return Stream.of(
                Arguments.arguments(WidthFixture.createWidthValue1(), HeightFixture.createHeightValue1()),
                Arguments.arguments(WidthFixture.createWidthValue2(), HeightFixture.createHeightValue1()),
                Arguments.arguments(WidthFixture.createWidthValue3(), HeightFixture.createHeightValue1()),
                Arguments.arguments(WidthFixture.createWidthValue1(), HeightFixture.createHeightValue2()),
                Arguments.arguments(WidthFixture.createWidthValue1(), HeightFixture.createHeightValue3())
        );
    }
}