package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ScanTag extends SimpleTagSupport
{
  public void doTag() throws JspException, IOException
  {
    JspWriter out = getJspContext().getOut();
    out.print(
    "    <script type=\"text/javascript\" src=\"Resources/dynamsoft.webtwain.initiate.js\"></script>\n" + 
    "    <script type=\"text/javascript\" src=\"Resources/dynamsoft.webtwain.config.js\"></script>\n" + 
    "      <select size=\"1\" id=\"source\" style=\"position: relative; width: 220px;\"></select>\n" + 
    "      <!--<input type=\"button\" value=\"ÇáãÓÍ ÇáÖæÆí Scan\" onclick=\"AcquireImage();UploadImage();\"/>-->\n" + 
    "      <button type=\"button\" class=\"normalButton\" onclick=\"AcquireImage();UploadImage();\">\n" + 
    "        <img src=\"images/scanner.png\" alt=\"scanner\"/>\n" + 
    "      </button>\n" + 
    "      <!--<input type=\"button\" value=\"ÍÝÙ\" onclick=\"UploadImage();\"/>-->\n" + 
    "      <input type=\"hidden\" id=\"scannedImage\"/>\n" + 
    "      <div id=\"dwtcontrolContainer\" style=\"width: 50px !important; height: 50px !important; visibility: hidden;\"></div>\n" + 
    "      \n" + 
    "      <script type=\"text/javascript\">\n" + 
    "        if (Dynamsoft.Lib.env.bMobile)\n" + 
    "        {\n" + 
    "          //alert(\"Mobile, Do not load DWT\")\n" + 
    "        }\n" + 
    "        else\n" + 
    "        {\n" + 
    "          Dynamsoft.DWT.Load();\n" + 
    "        }\n" + 
    "      </script>\n" + 
    "    \n" + 
    "      <script type=\"text/javascript\">\n" + 
    "        Dynamsoft.DWT.RegisterEvent('OnWebTwainReady', Dynamsoft_OnReady);\n" + 
    "        //Dynamsoft.DWT.RegisterEvent(\"OnPostAllTransfers\", Dynamsoft_OnPostTransfer);\n" + 
    "        // Register OnWebTwainReady event. This event fires as soon as Dynamic Web TWAIN is initialized and ready to be used\n" + 
    "        var DWObject;\n" + 
    "        \n" + 
    "        function Dynamsoft_OnReady()\n" + 
    "        {\n" + 
    "          //alert('in Dynamsoft_OnReady');\n" + 
    "          DWObject = Dynamsoft.DWT.GetWebTwain('dwtcontrolContainer');\n" + 
    "          DWObject.RegisterEvent(\"OnPostAllTransfers\", Dynamsoft_OnPostTransfer);\n" + 
    "          //alert(DWObject);\n" + 
    "          var i, L = document.getElementById(\"source\").options.length - 1;\n" + 
    "          for(i = L; i >= 0; i--) \n" + 
    "          {\n" + 
    "            selectElement.remove(i);\n" + 
    "          }\n" + 
    "          if (DWObject) {\n" + 
    "              var count = DWObject.SourceCount;\n" + 
    "              for (i = 0; i < count; i++)\n" + 
    "                  document.getElementById(\"source\").options.add(new Option(DWObject.GetSourceNameItems(i), i));\n" + 
    "              // Get Data Source names from Data Source Manager and put them in a drop-down box\n" + 
    "          }\n" + 
    "        }\n" + 
    "\n" + 
    "        function AcquireImage()\n" + 
    "        {\n" + 
    "          if (DWObject)\n" + 
    "          {\n" + 
    "            var OnAcquireImageSuccess, OnAcquireImageFailure;\n" + 
    "            OnAcquireImageSuccess = OnAcquireImageFailure = function ()\n" + 
    "            {\n" + 
    "              DWObject.CloseSource();\n" + 
    "            };\n" + 
    "\n" + 
    "            DWObject.SelectSourceByIndex(document.getElementById(\"source\").selectedIndex);\n" + 
    "            DWObject.OpenSource();\n" + 
    "            DWObject.IfDisableSourceAfterAcquire = true;// Scanner source will be disabled/closed automatically after the scan.\n" + 
    "            DWObject.IfFeederEnabled = true;\n" + 
    "            DWObject.IfShowUI = true;\n" + 
    "            DWObject.AcquireImage(OnAcquireImageSuccess, OnAcquireImageFailure);\n" + 
    "          }\n" + 
    "        }\n" + 
    "\n" + 
    "        function OnHttpUploadSuccess()\n" + 
    "        {\n" + 
    "          //alert('successful');\n" + 
    "        }\n" + 
    "\n" + 
    "        function OnHttpUploadFailure(errorCode, errorString, sHttpResponse)\n" + 
    "        {\n" + 
    "          alert(sHttpResponse + ' -- ' + errorCode + ' -- ' + errorString);\n" + 
    "        }\n" + 
    "\n" + 
    "        function UploadImage()\n" + 
    "        {\n" + 
    "          if (DWObject)\n" + 
    "          {\n" + 
    "            if (DWObject.HowManyImagesInBuffer == 0)\n" + 
    "              return;\n" + 
    "\n" + 
    "            var Digital = new Date();\n" + 
    "            var uploadfilename = Digital.getMilliseconds();// Uses milliseconds according to local time as the file name\n" + 
    "            DWObject.IfSSL = false;// Set whether SSL is used\n" + 
    "            //DWObject.HTTPPort = 7101;\n" + 
    "            var strHTTPServer2 = location.hostname;//The name of the HTTP server. \n" + 
    "            var CurrentPathName2 = unescape(location.pathname);\n" + 
    "            var CurrentPath2 = CurrentPathName2.substring(0, CurrentPathName2.lastIndexOf(\"/\") + 1);\n" + 
    "            var strActionPage2 = CurrentPath2 + \"scanuploadservlet\";\n" + 
    "            strActionPage2 = strActionPage2.replace('faces/', '');\n" + 
    "            DWObject.IfSSL = false;// Set whether SSL is used\n" + 
    "            DWObject.HTTPPort = location.port == \"\" ? 80 : location.port;\n" + 
    "\n" + 
    "            if (window.location.protocol == \"https:\")\n" + 
    "            {\n" + 
    "              DWObject.HTTPPort = 443;\n" + 
    "              DWObject.IfSSL = true; // if 443 is the port number of secure port\n" + 
    "            }\n" + 
    "            //alert(strHTTPServer2 + ' - ' + DWObject.HTTPPort + '-' + strActionPage2);\n" + 
    "            DWObject.HTTPUploadAllThroughPostAsPDF(strHTTPServer2, strActionPage2, uploadfilename + \".pdf\", OnHttpUploadSuccess, OnHttpUploadFailure);\n" + 
    "            DWObject.RemoveAllImages();\n" + 
    "          }\n" + 
    "          return uploadfilename;\n" + 
    "        }\n" + 
    "        \n" + 
    "        function Dynamsoft_OnPostTransfer()\n" + 
    "        {\n" + 
    "          //alert('Dynamsoft_OnPostTransfer');\n" + 
    "          var fileName = UploadImage();\n" + 
    "          //alert(fileName);\n" + 
    "          if (fileName != null)\n" + 
    "          {\n" + 
    "            //alert(\"in queue\");\n" + 
    "            var textField = AdfPage.PAGE.findComponentByAbsoluteId(\"page1:scanCompId\");\n" + 
    "            AdfCustomEvent.queue(textField, \"refreshFromScanner\", \n" + 
    "            {\n" + 
    "              currentFileName : \"\" + fileName\n" + 
    "            },\n" + 
    "            true);\n" + 
    "          }\n" + 
    "          else\n" + 
    "          {\n" + 
    "            //alert(\"null file\");\n" + 
    "          }\n" + 
    "        }\n" + 
    "      </script>");
  }
}
