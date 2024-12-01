package org.capybara.common.utils.g4.mysql;

import org.antlr.v4.runtime.TokenStream;

import java.util.ArrayList;
import java.util.List;

/**
 * 记录每一行
 *
 * @author Long Jie
 * @since 2024/12/1 8:25
 */
public class LineBasedListener extends MySQLParserBaseListener {

    private final List<String> sqlStatements = new ArrayList<>();
    private MySQLParser parser;

    public LineBasedListener(MySQLParser parser) {
        this.parser = parser;
    }

    public List<String> getSqlStatements() {
        return sqlStatements;
    }

    @Override
    public void exitQuery(MySQLParser.QueryContext ctx) {
        TokenStream tokenStream = parser.getTokenStream();
        sqlStatements.add(tokenStream.getText(ctx));
    }

}
