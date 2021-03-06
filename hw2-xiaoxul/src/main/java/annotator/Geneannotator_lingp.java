package annotator;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import MyTypeSystem.Genetag;
import MyTypeSystem.Sentence;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.chunk.ConfidenceChunker;
import com.aliasi.util.AbstractExternalizable;
//import com.aliasi.util.Strings;


public class Geneannotator_lingp extends JCasAnnotator_ImplBase {
	
	/**
	 * Annotator for Genetag, using lingpipe, processing Sentence.
	 *
	 */

	//this const should be chosen really seriously
	//N-gram annotation here
	private static final int MAX_N_BEST_CHUNKS = 5;


  public void process(JCas aCas) throws AnalysisEngineProcessException {
	  /**
		 * The process function uses lingpipe as a gene name source,manipulate the split Sentences 
		 * from the cas, retrive the gene name and store them.
		 */
    // TODO Auto-generated method stub
    JCas jcas = aCas;
    //int count = 0;
    
    // File modelFile = new File("src/main/resources/inputData/ne-en-bio-Genetag.HmmChunker");
    //System.out.println("Reading chunker from file =" + modelFile);   
   // String pathofgene ="/Users/apple/git/hw2-xiaoxul/hw2-xiaoxul/src/main/resources/inputData/ne-en-bio-Genetag.HmmChunker";
    String pathofgene ="/inputData/ne-en-bio-genetag.HmmChunker";
    FSIterator it = jcas.getAnnotationIndex(Sentence.type).iterator();

    ConfidenceChunker chunker = null;
	try {
		//chunker = (ConfidenceChunker) AbstractExternalizable.readObject(modelFile);
		chunker = (ConfidenceChunker) AbstractExternalizable.readResourceObject(pathofgene);

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   
	DecimalFormat ft = new DecimalFormat("#0.0000");
	//System.out.println("Rank          Conf      Span    Type     Gene");
    while(it.hasNext()){
    	//System.out.println("iterator");
        Sentence ann = (Sentence)it.get();  
        String sen = ann.getContent();
        String id = ann.getID();
        char[] senten = sen.toCharArray();
        Iterator<Chunk> itera = 
        		chunker.nBestChunks(senten, 0, senten.length, MAX_N_BEST_CHUNKS);
        //Chunking chunking = chunker.chunk(ann.getContent());     
        //System.out.println("Chunking=" + chunking);
       String gene;
 
        //Set<Chunk> set = chunking.chunkSet();
        //Iterator iter = set.iterator();
       for(int n = 0; itera.hasNext(); n++){
            Chunk c = (Chunk) itera.next();
            //System.out.println(c.score());
            double conf = Math.pow(2.0, c.score());
            //System.out.println(ft.format(conf));
            
            if(conf > 0.05){//only leave those whose confidence make sense
            
            	gene = (sen.substring(c.start(), c.end()));
            	//System.out.println(c.start());
            	//System.out.println(c.end());
            	//System.out.println(gene);
                 
//            	System.out.println(n + " "
//            			+ ft.format(conf)
//            			+ "       (" + c.start()
//            			+ ", " + c.end()
//            			+ ")       " + c.type()
//            			+ "         " + gene);
            
            	//do not forget that the space should be got rid of 
            	int begin = c.start() ;
            	int end = c.end();
            	begin = begin - countBlank(sen.substring(0,begin)) ;
            	end = begin + gene.length() - countBlank(gene) - 1;

            	Genetag gt = new Genetag(aCas);
            	gt.setID(id);
            	gt.setContent(gene);
            	gt.setConfidence(conf);
            	gt.setCasProcessorId("lingp");
            	gt.setBegin(begin);
            	gt.setEnd(end);
            	gt.addToIndexes();
           
            }
        //System.out.println(count++);
        it.next();
       } 
    }
 }
    
    
  private int countBlank(String s){
    int count = 0;
    for(int i = 0; i < s.length(); i++) {
        if(Character.isWhitespace(s.charAt(i))) {
            count++;
        }
     }
    return count;
  }
  
 
}