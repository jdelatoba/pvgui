
package nube.app.choya.sys.pv.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nube.app.choya.sys.pv.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Hello_QNAME = new QName("http://ws.pv.sys.choya.app.nube/", "hello");
    private final static QName _AgregarSucursalResponse_QNAME = new QName("http://ws.pv.sys.choya.app.nube/", "agregarSucursalResponse");
    private final static QName _AgregarSucursal_QNAME = new QName("http://ws.pv.sys.choya.app.nube/", "agregarSucursal");
    private final static QName _AgregarSucursalM2Response_QNAME = new QName("http://ws.pv.sys.choya.app.nube/", "agregarSucursalM2Response");
    private final static QName _HelloResponse_QNAME = new QName("http://ws.pv.sys.choya.app.nube/", "helloResponse");
    private final static QName _AgregarSucursalM2_QNAME = new QName("http://ws.pv.sys.choya.app.nube/", "agregarSucursalM2");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nube.app.choya.sys.pv.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AgregarSucursalM2 }
     * 
     */
    public AgregarSucursalM2 createAgregarSucursalM2() {
        return new AgregarSucursalM2();
    }

    /**
     * Create an instance of {@link AgregarSucursalM2Response }
     * 
     */
    public AgregarSucursalM2Response createAgregarSucursalM2Response() {
        return new AgregarSucursalM2Response();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link AgregarSucursalResponse }
     * 
     */
    public AgregarSucursalResponse createAgregarSucursalResponse() {
        return new AgregarSucursalResponse();
    }

    /**
     * Create an instance of {@link AgregarSucursal }
     * 
     */
    public AgregarSucursal createAgregarSucursal() {
        return new AgregarSucursal();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.pv.sys.choya.app.nube/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarSucursalResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.pv.sys.choya.app.nube/", name = "agregarSucursalResponse")
    public JAXBElement<AgregarSucursalResponse> createAgregarSucursalResponse(AgregarSucursalResponse value) {
        return new JAXBElement<AgregarSucursalResponse>(_AgregarSucursalResponse_QNAME, AgregarSucursalResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarSucursal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.pv.sys.choya.app.nube/", name = "agregarSucursal")
    public JAXBElement<AgregarSucursal> createAgregarSucursal(AgregarSucursal value) {
        return new JAXBElement<AgregarSucursal>(_AgregarSucursal_QNAME, AgregarSucursal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarSucursalM2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.pv.sys.choya.app.nube/", name = "agregarSucursalM2Response")
    public JAXBElement<AgregarSucursalM2Response> createAgregarSucursalM2Response(AgregarSucursalM2Response value) {
        return new JAXBElement<AgregarSucursalM2Response>(_AgregarSucursalM2Response_QNAME, AgregarSucursalM2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.pv.sys.choya.app.nube/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarSucursalM2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.pv.sys.choya.app.nube/", name = "agregarSucursalM2")
    public JAXBElement<AgregarSucursalM2> createAgregarSucursalM2(AgregarSucursalM2 value) {
        return new JAXBElement<AgregarSucursalM2>(_AgregarSucursalM2_QNAME, AgregarSucursalM2 .class, null, value);
    }

}
