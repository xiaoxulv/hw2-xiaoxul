import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;


public class annotator_Decider extends JCasAnnotator_ImplBase {
	/**
	 * This annotator is the last one, which process the result from previous annotators using NER. 
	 * All the genetags got both lingpipe and abner will go through this one 
	 * to make a decision about whether it will be showed in the last result, which means stored in the output
	 * file.
	 * 
	 */
	@Override
	public void process(JCas aCas) throws AnalysisEngineProcessException {
		/**
		 * The decider's process function is quite simple and straight forward: genetags from lingpipe with greater 
		 * confidence than 0.1 will be directly given to cas while those who are not will be looked up and evaluated
		 * in the results got from abner.
		 * 
		 */
		// TODO Auto-generated method stub
		JCas jcas = aCas;
		FSIterator it = jcas.getAnnotationIndex(genetag.type).iterator();
		genetag gene;
		String ID;
		String Content;
		int begin, end = -1;
		double conf = 0.0;
		HashSet<String> abners = new HashSet<String>();
		HashMap<String, Double> lingps = new HashMap<String, Double>();
		
		while(it.hasNext()){
			gene = (genetag)it.get();
			String processor = gene.getCasProcessorId();
			
			if(processor.equals("abner")){
				String key = gene.getID() + " " + String.format("%03d", gene.getBegin()) + " " 
							+ String.format("%03d",gene.getEnd()) + " " + gene.getContent() ;
				abners.add(key);
				//System.out.println(key);
			}
			
			if(processor.equals("lingp")){
				String key = gene.getID() + " " + String.format("%03d", gene.getBegin()) + " " 
						+ String.format("%03d",gene.getEnd()) + " " + gene.getContent() ;
				lingps.put(key, gene.getConfidence());	
				//System.out.println(key);
			}
			it.next();
		}
		
		//clear the previous jcas
		//jcas.reset();
		//this is not allowed, reset can't be called from annotator
		//will cause an exception
		//try to ues new type system to store in cas
		
		double div = 0.1; 
		Iterator lingpIt = lingps.entrySet().iterator();
		while(lingpIt.hasNext()){
			Map.Entry entry = (Map.Entry) lingpIt.next();
			Double val = (Double) entry.getValue();
			String Key = (String) entry.getKey(); 
			if(Key.charAt(14) == ' '){
				if(val >= div){
					Gene g = new Gene(jcas);
					//System.out.println(Key);
					g.setID(Key.substring(0, 14));
            		g.setBegin(Integer.parseInt(Key.substring(15, 18)));
            		g.setEnd(Integer.parseInt(Key.substring(19, 22)));
            		g.setContent(Key.substring(23));
            		g.addToIndexes();
				}
				if(val < div){
					if(abners.contains(Key)){
						Gene g = new Gene(jcas);
						g.setID(Key.substring(0, 14));
						//System.out.println(Key.substring(0, 14));
						g.setBegin(Integer.parseInt(Key.substring(15, 18)));
						//System.out.println(Key.substring(15, 17));
						g.setEnd(Integer.parseInt(Key.substring(19, 22)));
						g.setContent(Key.substring(23));
						g.addToIndexes();
					}
				}
			}
		}

	}

}
