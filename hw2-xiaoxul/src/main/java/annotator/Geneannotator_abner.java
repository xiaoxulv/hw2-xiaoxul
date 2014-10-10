package annotator;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;

import MyTypeSystem.Genetag;
import MyTypeSystem.Sentence;
import abner.Tagger;


public class Geneannotator_abner extends JCasAnnotator_ImplBase {
	/**
	 * Annotator for Genetag, using abber, processing Sentence.
	 */
	@Override
	public void process(JCas aCas) throws AnalysisEngineProcessException {
		/**
		 * The process function uses abner as a gene name source,manipulate the split Sentences 
		 * from the cas, retrive the gene name and store them.
		 */
		// TODO Auto-generated method stub
		JCas jcas = aCas;
		FSIterator it = jcas.getAnnotationIndex(Sentence.type).iterator();
		Tagger t = new Tagger(1);//use BioCreative
		int begin;
		int end;
		int count = 0;
		while(it.hasNext()){
	        Sentence ann = (Sentence)it.get();  
	        String sen = ann.getContent();
	        String id = ann.getID();
			count++;
			//System.out.println("the count" + count);
	        //System.out.println(t.tokenize(sen));   
//	        t.tokenize(sen);
//	        t.tagABNER(sen);
//	        t.tagIOB(sen);
//	        t.tagSGML(sen);
    	    //System.out.println("abner analysis finished");
	
//			System.out.println(t.tagABNER(sen));
//			System.out.println(t.tagIOB(sen));
//			System.out.println(t.tagSGML(sen));
			
	        String[][] ents = t.getEntities(sen);
	        String gene;
	        
	    	for (int i = 0; i < ents[0].length; i++) {
	    	    //System.out.println(ents[i]);
	    	    gene = ents[0][i]; 
	    	    //System.out.println("get Entities");
	    	    //System.out.println(id);
	    	    //System.out.println(gene);

	    	    begin = sen.indexOf(gene);
	    	    end = begin + gene.length();
	    	    //System.out.println(begin);
	    	    //System.out.println(end);
	    	    if(begin != -1){
	    	    	begin = begin - countBlank(sen.substring(0,begin)) ;
	    	    	end = begin + gene.length() - countBlank(gene) - 1;
	    	    	//System.out.println(begin);
	    	    	//System.out.println(end);
	    	    
	    	    	Genetag gt = new Genetag(aCas);
	    	    	gt.setID(id);
	    	    	gt.setContent(gene);	    	    	
	    	    	gt.setBegin(begin);
	    	    	gt.setEnd(end);
	    	    	gt.setConfidence(0);
	    	    	gt.setCasProcessorId("abner");
	    	    	gt.addToIndexes();
	    	    }
	    	}
	    	it.next();
	    	
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
