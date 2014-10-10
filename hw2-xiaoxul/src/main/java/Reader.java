import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;


public class Reader extends CollectionReader_ImplBase {
	/**
	 * Open the input file and put texts in it to the cas. 
	 */
//	private BufferedReader in;
//	private String contents = ""; 
	
	private boolean finish = true;
	private String text;
	public static final String PARAM_INPUTDIR = "inputFile";
	
	/**
	 * initialize() open the input file, try to use file2string, get rid of all the annoying stream.
	 */
	public void initialize() throws ResourceInitializationException {
		
		//File file = new File("src/main/resources/data/hw1.in");
		File directory = new File(((String) getConfigParameterValue(PARAM_INPUTDIR)).trim());
		try {
			//File file = new File(directory);
			text = FileUtils.file2String(directory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    System.out.println("Initializing the reader~");	  
	    //System.out.println(text);
	    
//		try{
//			FileReader file = new FileReader(directory);
//			in = new BufferedReader(file);
//			while(in.readLine() != null){
//				contents = in.NextLine();	
//			}
//			
//			in.close();
//		}catch(FileNotFoundException e){
//			e.printStackTrace();
//		}catch(IOException e){
//			e.printStackTrace();	
//
//		}	
//	}
	}
	
	
	/**
	 * getNext(CAS aCAS) put what Initialize get from the file to CAS.
	 * 
	 */
	@Override
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		// TODO Auto-generated method stub
		
		  JCas jcas;
		  try {
		    jcas = aCAS.getJCas();
		  } catch (CASException e) {
		    throw new CollectionException(e);
		  }
		  System.out.println("geting next~");
		  //System.out.println(text);	
		  jcas.setDocumentText(text);
		  
		  finish = false;
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		// TODO Auto-generated method stub
		return finish;
	}

	@Override
	public Progress[] getProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		//in.close();
	}

}
