//url="http://localhost:9090/job/3_filecheck_done/build?delay=30sec"
url=args[0]

import java.net.URL
import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.WebRequestSettings
import com.gargoylesoftware.htmlunit.html.HtmlHorizontalRule as HR
import com.gargoylesoftware.htmlunit.HttpMethod
import com.gargoylesoftware.htmlunit.util.NameValuePair


import com.gargoylesoftware.htmlunit.IncorrectnessListener
import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler
import org.w3c.css.sac.*

//
// see javadoc http://htmlunit.sourceforge.net/apidocs/index.html
//

class MyIncorrectnessListener implements IncorrectnessListener{
    @Override
    public void notify(String arg0, Object arg1){}
}     

class MycssErrorHandler implements ErrorHandler{
    public void warning(CSSParseException exception) throws CSSException{}
    public void error(CSSParseException exception) throws CSSException{}
    public void fatalError(CSSParseException exception) throws CSSException{}
} 



c = new WebClient(BrowserVersion.FIREFOX_3);

IncorrectnessListener ilisten = new MyIncorrectnessListener() 
ErrorHandler ierr = new MycssErrorHandler() 
c.setIncorrectnessListener(ilisten)
c.setCssErrorHandler(ierr)

WebRequestSettings requestSettings = new WebRequestSettings(new URL(url), HttpMethod.HEAD)

// Then we set the request parameters
//requestSettings.setRequestParameters(new ArrayList())
//requestSettings.getRequestParameters().add(new NameValuePair("name of value to post", "value"))

allIndexPage = c.getPage(requestSettings);

