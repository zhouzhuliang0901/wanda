package reindeer.base.ws.smsServer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.wondersgroup.aci.sms.ws.smsServer package. 
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

    private final static QName _LoginResponse_QNAME = new QName("http://www.xuhui.gov.cn/sms", "loginResponse");
    private final static QName _SendMessage_QNAME = new QName("http://www.xuhui.gov.cn/sms", "sendMessage");
    private final static QName _SendMessageResponse_QNAME = new QName("http://www.xuhui.gov.cn/sms", "sendMessageResponse");
    private final static QName _SendTimingMessageResponse_QNAME = new QName("http://www.xuhui.gov.cn/sms", "sendTimingMessageResponse");
    private final static QName _Logoff_QNAME = new QName("http://www.xuhui.gov.cn/sms", "logoff");
    private final static QName _SendTimingMessage_QNAME = new QName("http://www.xuhui.gov.cn/sms", "sendTimingMessage");
    private final static QName _LogoffResponse_QNAME = new QName("http://www.xuhui.gov.cn/sms", "logoffResponse");
    private final static QName _Login_QNAME = new QName("http://www.xuhui.gov.cn/sms", "login");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.wondersgroup.aci.sms.ws.smsServer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Logoff }
     * 
     */
    public Logoff createLogoff() {
        return new Logoff();
    }

    /**
     * Create an instance of {@link SendMessageResponse }
     * 
     */
    public SendMessageResponse createSendMessageResponse() {
        return new SendMessageResponse();
    }

    /**
     * Create an instance of {@link LogoffResponse }
     * 
     */
    public LogoffResponse createLogoffResponse() {
        return new LogoffResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link SendTimingMessage }
     * 
     */
    public SendTimingMessage createSendTimingMessage() {
        return new SendTimingMessage();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link SendMessage }
     * 
     */
    public SendMessage createSendMessage() {
        return new SendMessage();
    }

    /**
     * Create an instance of {@link SendTimingMessageResponse }
     * 
     */
    public SendTimingMessageResponse createSendTimingMessageResponse() {
        return new SendTimingMessageResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xuhui.gov.cn/sms", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xuhui.gov.cn/sms", name = "sendMessage")
    public JAXBElement<SendMessage> createSendMessage(SendMessage value) {
        return new JAXBElement<SendMessage>(_SendMessage_QNAME, SendMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xuhui.gov.cn/sms", name = "sendMessageResponse")
    public JAXBElement<SendMessageResponse> createSendMessageResponse(SendMessageResponse value) {
        return new JAXBElement<SendMessageResponse>(_SendMessageResponse_QNAME, SendMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendTimingMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xuhui.gov.cn/sms", name = "sendTimingMessageResponse")
    public JAXBElement<SendTimingMessageResponse> createSendTimingMessageResponse(SendTimingMessageResponse value) {
        return new JAXBElement<SendTimingMessageResponse>(_SendTimingMessageResponse_QNAME, SendTimingMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Logoff }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xuhui.gov.cn/sms", name = "logoff")
    public JAXBElement<Logoff> createLogoff(Logoff value) {
        return new JAXBElement<Logoff>(_Logoff_QNAME, Logoff.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendTimingMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xuhui.gov.cn/sms", name = "sendTimingMessage")
    public JAXBElement<SendTimingMessage> createSendTimingMessage(SendTimingMessage value) {
        return new JAXBElement<SendTimingMessage>(_SendTimingMessage_QNAME, SendTimingMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoffResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xuhui.gov.cn/sms", name = "logoffResponse")
    public JAXBElement<LogoffResponse> createLogoffResponse(LogoffResponse value) {
        return new JAXBElement<LogoffResponse>(_LogoffResponse_QNAME, LogoffResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xuhui.gov.cn/sms", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

}
