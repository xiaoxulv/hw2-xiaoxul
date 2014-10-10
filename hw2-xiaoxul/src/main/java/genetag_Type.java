
/* First created by JCasGen Tue Oct 07 00:48:49 EDT 2014 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import edu.cmu.deiis.types.Annotation_Type;

/** 
 * Updated by JCasGen Fri Oct 10 14:46:35 EDT 2014
 *  */
public class genetag_Type extends Annotation_Type {
  /**  
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /**  */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (genetag_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = genetag_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new genetag(addr, genetag_Type.this);
  			   genetag_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new genetag(addr, genetag_Type.this);
  	  }
    };
  /**  */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = genetag.typeIndexID;
  /**  
      */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("genetag");
 
  /**  */
  final Feature casFeat_ID;
  /**  */
  final int     casFeatCode_ID;
  /** 
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getID(int addr) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "genetag");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ID);
  }
  /** 
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setID(int addr, String v) {
        if (featOkTst && casFeat_ID == null)
      jcas.throwFeatMissing("ID", "genetag");
    ll_cas.ll_setStringValue(addr, casFeatCode_ID, v);}
    
  
 
  /**  */
  final Feature casFeat_Content;
  /**  */
  final int     casFeatCode_Content;
  /** 
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getContent(int addr) {
        if (featOkTst && casFeat_Content == null)
      jcas.throwFeatMissing("Content", "genetag");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Content);
  }
  /** 
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContent(int addr, String v) {
        if (featOkTst && casFeat_Content == null)
      jcas.throwFeatMissing("Content", "genetag");
    ll_cas.ll_setStringValue(addr, casFeatCode_Content, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * 
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public genetag_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_ID = jcas.getRequiredFeatureDE(casType, "ID", "uima.cas.String", featOkTst);
    casFeatCode_ID  = (null == casFeat_ID) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ID).getCode();

 
    casFeat_Content = jcas.getRequiredFeatureDE(casType, "Content", "uima.cas.String", featOkTst);
    casFeatCode_Content  = (null == casFeat_Content) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Content).getCode();

  }
}



    