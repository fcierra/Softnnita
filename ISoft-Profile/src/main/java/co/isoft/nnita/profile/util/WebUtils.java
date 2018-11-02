package co.isoft.nnita.profile.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Objeto util para obtener los segmentos de red
 * e identificar las ip de solicitudes
 */
@Component
public class WebUtils
{

    private static HttpServletRequest request;


    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public String getClientIp(HttpServletRequest request)
    {

        String remoteAddr = "";

        if (request != null)
        {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr))
            {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

}