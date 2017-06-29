
package nube.app.choya.sys.pv.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WsNubePVChoya", targetNamespace = "http://ws.pv.sys.choya.app.nube/", wsdlLocation = "http://app.contraloria.bcs.gob.mx:8080/pvnube/WsNubePVChoya?wsdl")
public class WsNubePVChoya_Service
    extends Service
{

    private final static URL WSNUBEPVCHOYA_WSDL_LOCATION;
    private final static WebServiceException WSNUBEPVCHOYA_EXCEPTION;
    private final static QName WSNUBEPVCHOYA_QNAME = new QName("http://ws.pv.sys.choya.app.nube/", "WsNubePVChoya");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://app.contraloria.bcs.gob.mx:8080/pvnube/WsNubePVChoya?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSNUBEPVCHOYA_WSDL_LOCATION = url;
        WSNUBEPVCHOYA_EXCEPTION = e;
    }

    public WsNubePVChoya_Service() {
        super(__getWsdlLocation(), WSNUBEPVCHOYA_QNAME);
    }

    public WsNubePVChoya_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSNUBEPVCHOYA_QNAME, features);
    }

    public WsNubePVChoya_Service(URL wsdlLocation) {
        super(wsdlLocation, WSNUBEPVCHOYA_QNAME);
    }

    public WsNubePVChoya_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSNUBEPVCHOYA_QNAME, features);
    }

    public WsNubePVChoya_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WsNubePVChoya_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WsNubePVChoya
     */
    @WebEndpoint(name = "WsNubePVChoyaPort")
    public WsNubePVChoya getWsNubePVChoyaPort() {
        return super.getPort(new QName("http://ws.pv.sys.choya.app.nube/", "WsNubePVChoyaPort"), WsNubePVChoya.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WsNubePVChoya
     */
    @WebEndpoint(name = "WsNubePVChoyaPort")
    public WsNubePVChoya getWsNubePVChoyaPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.pv.sys.choya.app.nube/", "WsNubePVChoyaPort"), WsNubePVChoya.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSNUBEPVCHOYA_EXCEPTION!= null) {
            throw WSNUBEPVCHOYA_EXCEPTION;
        }
        return WSNUBEPVCHOYA_WSDL_LOCATION;
    }

}
