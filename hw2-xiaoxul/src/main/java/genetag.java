

/* First created by JCasGen Tue Oct 07 00:48:49 EDT 2014 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import edu.cmu.deiis.types.Annotation;


/** 
 * Updated by JCasGen Fri Oct 10 14:46:35 EDT 2014
 * XML source: /Users/apple/git/hw2-xiaoxul/hw2-xiaoxul/src/main/resources/descriptors/deiis_types.xml
 *  */
public class genetag extends Annotation {
  /** 
   *  
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(genetag.class);
  /** 
   *  
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** 
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   *  */
  protected genetag() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * 
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public genetag(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** 
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public genetag(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** 
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public genetag(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   *  modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: ID

  /** getter for ID - gets 
   * 
   * @return value of the feature 
   */
  public String getID() {
    if (genetag_Type.featOkTst && ((genetag_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "genetag");
    return jcasType.ll_cas.ll_getStringValue(addr, ((genetag_Type)jcasType).casFeatCode_ID);}
    
  /** setter for ID - sets  
   * 
   * @param v value to set into the feature 
   */
  public void setID(String v) {
    if (genetag_Type.featOkTst && ((genetag_Type)jcasType).casFeat_ID == null)
      jcasType.jcas.throwFeatMissing("ID", "genetag");
    jcasType.ll_cas.ll_setStringValue(addr, ((genetag_Type)jcasType).casFeatCode_ID, v);}    
   
    
  //*--------------*
  //* Feature: Content

  /** getter for Content - gets 
   * 
   * @return value of the feature 
   */
  public String getContent() {
    if (genetag_Type.featOkTst && ((genetag_Type)jcasType).casFeat_Content == null)
      jcasType.jcas.throwFeatMissing("Content", "genetag");
    return jcasType.ll_cas.ll_getStringValue(addr, ((genetag_Type)jcasType).casFeatCode_Content);}
    
  /** setter for Content - sets  
   * 
   * @param v value to set into the feature 
   */
  public void setContent(String v) {
    if (genetag_Type.featOkTst && ((genetag_Type)jcasType).casFeat_Content == null)
      jcasType.jcas.throwFeatMissing("Content", "genetag");
    jcasType.ll_cas.ll_setStringValue(addr, ((genetag_Type)jcasType).casFeatCode_Content, v);}    
  }

    