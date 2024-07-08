package tags;

import gen.common.GenConstants;
import gen.common.GenLib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ViewerTag extends SimpleTagSupport
{
  private String filePath;
  private String filePathType;
  private String frameWidth;
  private String frameHeight;
  private String videoType;
  private String videoWidth;
  private String videoHeight;
  private String audioWidth;
  private String audioHeight;
  private String addWaterMark;

  public void doTag() throws JspException, IOException
  {
    String fileBase64 = null;
    JspWriter out = getJspContext().getOut();
    StringBuffer buffer = new StringBuffer();
    
    if(filePathType == null || filePathType.equals(""))
    {
      filePathType = GenConstants.PATH_TYPE_SYSTEM;
    }
    if(frameWidth == null || frameWidth.equals(""))
    {
      frameWidth = "100%";
    }
    if(frameHeight == null || frameHeight.equals(""))
    {
      frameHeight = "800px";
    }
    if ( videoType == null || videoType.trim().equals("") )
    {
      videoType = "video/mp4";
    }
    if ( videoWidth == null || videoWidth.trim().equals("") )
    {
      videoWidth = "100%";
    }
    if ( videoHeight == null || videoHeight.trim().equals("") )
    {
      videoHeight = "500";
    }
    if ( audioWidth == null || audioWidth.trim().equals("") )
    {
      audioWidth = "100%";
    }
    if ( audioHeight == null || audioHeight.trim().equals("") )
    {
      audioHeight = "50px";
    }
    
    if(filePath != null && filePathType != null)
    {
      buffer.append("<div style=\"overflow: hidden;\">");
      if(GenLib.isAudio(filePath))
      {
        fileBase64 = GenLib.getFileBase64(filePath, Integer.parseInt(filePathType));
        buffer.append(
          "<audio controls=\"controls\" style=\"width: " + audioWidth + "; height: " + audioHeight + ";\">" + 
          "<source src=\"data:audio/wav;base64, " + fileBase64 + "\">" +
          "</audio>");
      }
      else if(GenLib.isVideo(filePath))
      {
        fileBase64 = GenLib.getFileBase64(filePath, Integer.parseInt(filePathType));
        buffer.append(
          "<video width=\"" + videoWidth + "\" height=\"" + videoHeight + "\" controls=\"controls\">" + 
          "<source src=\"data:video/mp4;base64," + fileBase64 + "\" type=\"" + videoType + "\">" +
          "</video>");
      }
      else if ( GenLib.isViewerSupported(filePath) )
      {
        String s = "<iframe src=\"InlineFileDisplayServlet?filePathKey=" + filePath + 
                  "&filePathType=" + filePathType + "&addWaterMark=" + addWaterMark + "\" width=\"" + frameWidth + "\" " +
           "height=\"" + frameHeight + "\"> </iframe>";
        buffer.append(s);
      }
      buffer.append("</div>");
    }
    //System.out.println(buffer.toString());
    out.print(buffer.toString());
  }

  public void setFilePath(String filePath)
  {
    this.filePath = filePath;
  }

  public String getFilePath()
  {
    return filePath;
  }

  public void setFilePathType(String filePathType)
  {
    this.filePathType = filePathType;
  }

  public String getFilePathType()
  {
    return filePathType;
  }

  public void setFrameWidth(String frameWidth)
  {
    this.frameWidth = frameWidth;
  }

  public String getFrameWidth()
  {
    return frameWidth;
  }

  public void setFrameHeight(String frameHeight)
  {
    this.frameHeight = frameHeight;
  }

  public String getFrameHeight()
  {
    return frameHeight;
  }

  public void setVideoType(String videoType)
  {
    this.videoType = videoType;
  }

  public String getVideoType()
  {
    return videoType;
  }

  public void setVideoWidth(String videoWidth)
  {
    this.videoWidth = videoWidth;
  }

  public String getVideoWidth()
  {
    return videoWidth;
  }

  public void setVideoHeight(String videoHeight)
  {
    this.videoHeight = videoHeight;
  }

  public String getVideoHeight()
  {
    return videoHeight;
  }

  public void setAudioWidth(String audioWidth)
  {
    this.audioWidth = audioWidth;
  }

  public String getAudioWidth()
  {
    return audioWidth;
  }

  public void setAudioHeight(String audioHeight)
  {
    this.audioHeight = audioHeight;
  }

  public String getAudioHeight()
  {
    return audioHeight;
  }

  public void setAddWaterMark(String addWaterMark)
  {
    this.addWaterMark = addWaterMark;
  }

  public String getAddWaterMark()
  {
    return addWaterMark;
  }
  
}
