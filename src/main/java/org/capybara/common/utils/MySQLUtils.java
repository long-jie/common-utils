package org.capybara.common.utils;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.capybara.common.utils.g4.mysql.LineBasedListener;
import org.capybara.common.utils.g4.mysql.MySQLLexer;
import org.capybara.common.utils.g4.mysql.MySQLParser;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * MySQL工具类
 *
 * @author Long Jie
 * @since 2024/11/29 9:15
 */
public class MySQLUtils {

    public List<String> split(InputStream is) {
        return split(is, Charset.defaultCharset());
    }

    /**
     * 读入一个SQL文件，将里面的所有SQ语句切分成一条条语句
     *
     * @param is      sql file
     * @param charset 字符集
     * @return 每个元素都是一条单独的SQL语句
     */
    public List<String> split(InputStream is, Charset charset) {
        CharStream charStream;
        try {
            charStream = CharStreams.fromStream(is, charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        MySQLLexer lexer = new MySQLLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        MySQLParser parser = new MySQLParser(tokenStream);
        ParseTree tree = parser.queries();

        ParseTreeWalker walker = new ParseTreeWalker();
        LineBasedListener listener = new LineBasedListener(parser);
        walker.walk(listener, tree);
        return listener.getSqlStatements();
    }

}
