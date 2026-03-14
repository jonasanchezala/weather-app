package org.interview.parser;

import java.util.Optional;

public interface MessageParser<I, O> {
    Optional<O> parse(I input);
}
