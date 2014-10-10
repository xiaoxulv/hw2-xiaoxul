package annotator;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import MyTypeSystem.Sentence;


public class Sentence_Annotator extends JCasAnnotator_ImplBase {
	/**
	 * Sentence annotator, take input file and split them and store them in cas.
	 */
	@Override
	public void process(JCas aCas) throws AnalysisEngineProcessException {
		/**
		 * Get the input file content into CAS, split them by Sentence
		 * Also, get the ID and content separately and then store them separately
		 */
		// TODO Auto-generated method stub
		JCas jcas = aCas;
		String text = jcas.getDocumentText().trim();
		
		//System.out.println(text);
		//split by line
		String contents[] = text.split("\\n");
		
		for(int i = 0; i < contents.length; i++){
			//what if we do not know the length of ID
			//making things safer
			int space = contents[i].indexOf(" ");
			
			String SentenceId = contents[i].substring(0, space);
			String SentenceContent = contents[i].substring(space).trim();
			
			Sentence sen = new Sentence(jcas);
			sen.setID(SentenceId);
			sen.setContent(SentenceContent);
			sen.addToIndexes();
		
		}	
	}

}
