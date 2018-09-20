package sample.domain;

import java.util.HashMap;
import java.util.Map;

public class FunctionsRepository {

    public static Map<String, IPhiFunction> repository;

    static {
        repository = new HashMap<>();
        repository.put("gauss",
                (x, a, m, sigma) ->
                        a * Math.exp(-((x - m) * (x - m)) / 2 / sigma / sigma)
        );

        repository.put("double-gauss",
                (x, a, m, sigma) ->
                        a * Math.exp(-((x - m) * (x - m)) / 2 / sigma / sigma)
                                - 0.9 * (a * Math.exp(-((x + m) * (x + m)) / 2 / sigma / sigma))
        );
    }
}
