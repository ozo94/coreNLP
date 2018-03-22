//package com.sectong.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.trees.*;

import edu.stanford.nlp.naturalli.OpenIE;

public class CoreNLPSegment {


    public static void main(String[] args) throws IOException {

        String inputfile = "data/sentences.txt";
        String resultfile = "data/ners.txt";


        // StanfordCoreNLP pipeline = new StanfordCoreNLP("StanfordCoreNLP-chinese.properties");
        StanfordCoreNLP pipeline = new StanfordCoreNLP("CoreNLP-chinese.properties");

        // Add your text here!
        WriteToFileExample fileExample = new WriteToFileExample();

        BufferedReader bufread = fileExample.readFile(inputfile);
        String read;
        int flag = 1;
        while ((read = bufread.readLine()) != null) {
            System.out.println("read "+flag+": "+read);

            String text = read;
            Annotation document = new Annotation(text);
            // run all Annotators on this text
            pipeline.annotate(document);

            // output
            List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

            for (CoreMap sentence : sentences) {
                // traversing the words in the current sentence
                // a CoreLabel is a CoreMap with additional token-specific methods
                for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                    // this is the text of the token
                    String word = token.get(TextAnnotation.class);
                    // this is the POS tag of the token
                    String pos = token.get(PartOfSpeechAnnotation.class);
                    // this is the NER label of the token
                    String ne = token.get(NamedEntityTagAnnotation.class);

                    fileExample.writeFile(ne, resultfile);
                }
            }
            // get entity comb
            fileExample.writeFile("\n", resultfile);
            flag += 1;
        }



    }

}