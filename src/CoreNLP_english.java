import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.CoreMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;


public class CoreNLP_english {

    public static void main(String[] args) throws IOException {



        // StanfordCoreNLP pipeline = new StanfordCoreNLP("StanfordCoreNLP-chinese.properties");
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Add your text here!
        WriteToFileExample fileExample = new WriteToFileExample();

        BufferedReader bufread = fileExample.readFile("result.txt");
        String read;
        int flag = 1;
        while ((read = bufread.readLine()) != null) {
            System.out.println("read "+flag+": "+read+'\n');

            String text = read;
            Annotation document = new Annotation(text);

            // run all Annotators on this text
            pipeline.annotate(document);


            // output
            List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);


            fileExample.writeFile(flag+" " , "eng_ver.txt");

            for (CoreMap sentence : sentences) {
                // traversing the words in the current sentence
                // a CoreLabel is a CoreMap with additional token-specific methods
                for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                    // this is the text of the token
                    String word = token.get(CoreAnnotations.TextAnnotation.class);
                    // this is the POS tag of the token
                    String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                    // this is the NER label of the token
                    String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                    // System.out.println(word + "--" + pos + "--" + ne);

                    fileExample.writeFile(ne, "eng_ver.txt");
                }


                // this is the parse tree of the current sentence
                // Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
                // System.out.println(tree);


                // this is the Stanford dependency graph of the current sentence
                // SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
                // System.out.println(dependencies);

            }
            // get entity comb
            fileExample.writeFile("\n", "eng_ver.txt");
            flag += 1;
        }



    }

}
