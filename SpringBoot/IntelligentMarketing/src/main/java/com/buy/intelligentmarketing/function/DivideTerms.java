package com.buy.intelligentmarketing.function;

import java.io.StringReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;

public class DivideTerms {
    public static List<String> divideTerms(String text) {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        return segmenter.sentenceProcess(text);

    }
//
//    public static void main(String[] args) {
//        String s = "File,Advertisement-01&Advertisement-02&Advertisement-03";
//        System.out.println(Arrays.toString(s.split(",")[1].split("&")));
//    }
}
