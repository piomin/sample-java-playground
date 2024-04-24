package pl.piomin.service.background;

import org.junit.jupiter.api.Test;

import java.util.stream.Gatherers;
import java.util.stream.Stream;

import static one.util.streamex.IntCollector.summing;

public class StreamGathererTests {

    @Test
    void slidingWindow() {
        var errors = Stream
                .of(2, 0, 1, 3, 4, 2, 3, 0, 3, 1, 0, 0, 1)
                .gather(Gatherers.windowSliding(4))
                .map(x -> x.stream().collect(summing()) > 10)
                .toList();
        System.out.println(errors);
    }
}
