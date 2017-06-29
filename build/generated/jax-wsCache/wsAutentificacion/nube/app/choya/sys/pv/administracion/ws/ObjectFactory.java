
package nube.app.choya.sys.pv.administracion.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nube.app.choya.sys.pv.administracion.ws package. 
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

    private final static QName _DoAutentifica_QNAME = new QName("http://ws.administracion.pv.sys.choya.app.nube/", "doAutentifica");
    private final static QName _GetArticulosMatriz_QNAME = new QName("http://ws.administracion.pv.sys.choya.app.nube/", "getArticulosMatriz");
    private final static QName _DoAutentificaResponse_QNAME = new QName("http://ws.administracion.pv.sys.choya.app.nube/", "doAutentificaResponse");
    private final static QName _GetArticulosMatrizResponse_QNAME = new QName("http://ws.administracion.pv.sys.choya.app.nube/", "getArticulosMatrizResponse");
    private final static QName _HelloResponse_QNAME = new QName("http://ws.administracion.pv.sys.choya.app.nube/", "helloResponse");
    private final static QName _GetArticulos_QNAME = new QName("http://ws.administracion.pv.sys.choya.app.nube/", "getArticulos");
    private final static QName _Hello_QNAME = new QName("http://ws.administracion.pv.sys.choya.app.nube/", "hello");
    private final static QName _GetArticulosResponse_QNAME = new QName("http://ws.administracion.pv.sys.choya.app.nube/", "getArticulosResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nube.app.choya.sys.pv.administracion.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DoAutentifica }
     * 
     */
    public DoAutentifica createDoAutentifica() {
        return new DoAutentifica();
    }

    /**
     * Create an instance of {@link GetArticulosMatriz }
     * 
     */
    public GetArticulosMatriz createGetArticulosMatriz() {
        return new GetArticulosMatriz();
    }

    /**
     * Create an instance of {@link DoAutentificaResponse }
     * 
     */
    public DoAutentificaResponse createDoAutentificaResponse() {
        return new DoAutentificaResponse();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link GetArticulosMatrizResponse }
     * 
     */
    public GetArticulosMatrizResponse createGetArticulosMatrizResponse() {
        return new GetArticulosMatrizResponse();
    }

    /**
     * Create an instance of {@link GetArticulos }
     * 
     */
    public GetArticulos createGetArticulos() {
        return new GetArticulos();
    }

    /**
     * Create an instance of {@link GetArticulosResponse }
     * 
     */
    public GetArticulosResponse createGetArticulosResponse() {
        return new GetArticulosResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link JsonObject }
     * 
     */
    public JsonObject createJsonObject() {
        return new JsonObject();
    }

    /**
     * Create an instance of {@link HashMap }
     * 
     */
    public HashMap createHashMap() {
        return new HashMap();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoAutentifica }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.administracion.pv.sys.choya.app.nube/", name = "doAutentifica")
    public JAXBElement<DoAutentifica> createDoAutentifica(DoAutentifica value) {
        return new JAXBElement<DoAutentifica>(_DoAutentifica_QNAME, DoAutentifica.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetArticulosMatriz }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.administracion.pv.sys.choya.app.nube/", name = "getArticulosMatriz")
    public JAXBElement<GetArticulosMatriz> createGetArticulosMatriz(GetArticulosMatriz value) {
        return new JAXBElement<GetArticulosMatriz>(_GetArticulosMatriz_QNAME, GetArticulosMatriz.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoAutentificaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.administracion.pv.sys.choya.app.nube/", name = "doAutentificaResponse")
    public JAXBElement<DoAutentificaResponse> createDoAutentificaResponse(DoAutentificaResponse value) {
        return new JAXBElement<DoAutentificaResponse>(_DoAutentificaResponse_QNAME, DoAutentificaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetArticulosMatrizResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.administracion.pv.sys.choya.app.nube/", name = "getArticulosMatrizResponse")
    public JAXBElement<GetArticulosMatrizResponse> createGetArticulosMatrizResponse(GetArticulosMatrizResponse value) {
        return new JAXBElement<GetArticulosMatrizResponse>(_GetArticulosMatrizResponse_QNAME, GetArticulosMatrizResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.administracion.pv.sys.choya.app.nube/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetArticulos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.administracion.pv.sys.choya.app.nube/", name = "getArticulos")
    public JAXBElement<GetArticulos> createGetArticulos(GetArticulos value) {
        return new JAXBElement<GetArticulos>(_GetArticulos_QNAME, GetArticulos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.administracion.pv.sys.choya.app.nube/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetArticulosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.administracion.pv.sys.choya.app.nube/", name = "getArticulosResponse")
    public JAXBElement<GetArticulosResponse> createGetArticulosResponse(GetArticulosResponse value) {
        return new JAXBElement<GetArticulosResponse>(_GetArticulosResponse_QNAME, GetArticulosResponse.class, null, value);
    }

}
