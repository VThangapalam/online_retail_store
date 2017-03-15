

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxParser4BestDealXMLdataStore extends DefaultHandler {
     Product product;
    static List<Product> products = new ArrayList<Product>();
    String productXmlFileName;
    String elementValueRead;

    
    public SaxParser4BestDealXMLdataStore(String productXmlFileName) {
        this.productXmlFileName = productXmlFileName;
        products = new ArrayList<Product>();
        parseDocument();
        prettyPrint();
    }


    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(productXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }


    private void prettyPrint() {
	
        for (Product product: products) {
            	System.out.println("product #"+ product.id +":");
		System.out.println("\t\t category: " + product.category);
		System.out.println("\t\t name: " + product.name);
		System.out.println("\t\t brand " + product.brand);
        System.out.println("\t\t brand_rebate " + product.brand_rebate);
		for (String accessory: product.accessories) {
			System.out.println("\t\t accessory: " + accessory);
		}
        }
    }



    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("product")) {
            product = new Product();
            product.setId(attributes.getValue("id"));
            product.setCategory(attributes.getValue("category"));
        }

    }
 @SuppressWarnings("unchecked")
    @Override
	
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("product")) {
            products.add(product);
	    return;
        }
        if (element.equalsIgnoreCase("image")) {
            product.setImage(elementValueRead);
	    return;
        }
		 if (element.equalsIgnoreCase("brand")) {
            product.setBrand(elementValueRead);
	    return;
        }
		if (element.equalsIgnoreCase("manufacturer_rebate")) {
            product.setBrandRebate(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("name")) {
            product.setName(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("accessory")){
           product.getAccessories().add(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("price")){
            product.setPrice(Integer.parseInt(elementValueRead));
	    return;
        }

    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }
 
       public static Product getItem(String itemID) {
    	 List<Product> res = getProducts();
    	 Product result = null;
         for (Product p : res) {
        	if (p.getId().equals(itemID))
        	{
        		result = p;
        		System.out.println("found item!");
        		return result;
        	}
         }
         return null;
         
    }
    
 
    public static List<Product> getProducts() {
    	new SaxParser4BestDealXMLdataStore("F:\\595\\tomcat\\webapps\\A2\\WEB-INF\\classes\\ProductCatalog.xml");
        return products;
    	
    }
    
   

}
