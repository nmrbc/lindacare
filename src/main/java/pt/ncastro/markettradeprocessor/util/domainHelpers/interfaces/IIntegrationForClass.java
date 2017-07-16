package pt.ncastro.markettradeprocessor.util.domainHelpers.interfaces;

/**
 * This interface is to be used on the classes that are result of integrations,
 * such as Trade.java
 * 
 * @author Nuno de Castro
 *
 */
public interface IIntegrationForClass<K extends IExchangeableByJSON> {

	/**
	 * Create the DTO representation for this class to be used on integrations.
	 * 
	 * @return
	 */
	public K toDTOClass();


	/**
	 * Fills the object with the DTO class that represents the object coming
	 * from integrations.
	 * 
	 * @param dto
	 */
	public void fromDTOClass(K dto);
}
