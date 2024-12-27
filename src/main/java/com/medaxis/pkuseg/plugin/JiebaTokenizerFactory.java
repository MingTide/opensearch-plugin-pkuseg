package com.medaxis.pkuseg.plugin;

import com.medaxis.analysis.jieba.JiebaSegmenter;
import org.apache.lucene.analysis.Tokenizer;
import org.opensearch.common.settings.Settings;
import org.opensearch.env.Environment;
import org.opensearch.index.IndexSettings;
import org.opensearch.index.analysis.AbstractTokenizerFactory;
import org.opensearch.index.analysis.TokenizerFactory;



public class JiebaTokenizerFactory extends AbstractTokenizerFactory {

    public static final String TokenizerName = "JiebaTokenizer";

    private String segMode;

    public JiebaTokenizerFactory(IndexSettings indexSettings, Environment env, Settings settings) {
        super(indexSettings, settings, TokenizerName);
        JiebaDict.init(env);
    }

    public static TokenizerFactory getJiebaSearchTokenizerFactory(IndexSettings indexSettings,
                                                                  Environment environment,
                                                                  String s,
                                                                  Settings settings) {
        JiebaTokenizerFactory jiebaTokenizerFactory = new JiebaTokenizerFactory(indexSettings,
                environment,
                settings);
        jiebaTokenizerFactory.setSegMode(JiebaSegmenter.SegMode.SEARCH.name());
        return jiebaTokenizerFactory;
    }

    public static TokenizerFactory getJiebaIndexTokenizerFactory(IndexSettings indexSettings,
                                                                 Environment environment,
                                                                 String s,
                                                                 Settings settings) {
        JiebaTokenizerFactory jiebaTokenizerFactory = new JiebaTokenizerFactory(indexSettings,
                environment,
                settings);
        jiebaTokenizerFactory.setSegMode(JiebaSegmenter.SegMode.INDEX.name());
        return jiebaTokenizerFactory;
    }

    @Override
    public Tokenizer create() {
        return new JiebaTokenizer(segMode);
    }

    public String getSegMode() {
        return segMode;
    }

    public void setSegMode(String segMode) {
        this.segMode = segMode;
    }
}
