package org.capybara.common.utils.g4.mysql;

/** SQL modes that control parsing behavior. */
public enum SqlMode {
    NoMode,
    AnsiQuotes,
    HighNotPrecedence,
    PipesAsConcat,
    IgnoreSpace,
    NoBackslashEscapes
}