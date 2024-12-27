package com.medaxis.pkuseg.plugin;

import org.apache.lucene.analysis.Analyzer;

import org.opensearch.index.analysis.AnalyzerProvider;
import org.opensearch.index.analysis.TokenizerFactory;
import org.opensearch.indices.analysis.AnalysisModule;
import org.opensearch.plugins.AnalysisPlugin;
import org.opensearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangcheng on 2017/1/17.
 */
public class AnalysisJiebaPlugin extends Plugin implements AnalysisPlugin {

    public static String PLUGIN_NAME = "analysis-jieba";

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
        Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> extra = new HashMap<>();

        extra.put("jieba_search", JiebaTokenizerFactory::getJiebaSearchTokenizerFactory);
        extra.put("jieba_index", JiebaTokenizerFactory::getJiebaIndexTokenizerFactory);

        return extra;
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> extra = new HashMap<>();

        extra.put("jieba_search", JiebaAnalyzerProvider::getJiebaSearchAnalyzerProvider);
        extra.put("jieba_index", JiebaAnalyzerProvider::getJiebaIndexAnalyzerProvider);

        return extra;
    }
}
