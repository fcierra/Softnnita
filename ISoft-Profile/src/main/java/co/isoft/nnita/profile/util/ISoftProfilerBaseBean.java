package co.isoft.nnita.profile.util;

import co.isoft.nnita.profile.api.exceptions.ServiceException;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase base de la que deben
 * extender todos los bean faces
 * para el uso de elementos comunes entre
 * el contenedor de faces.
 * @author Yaher Carrillo
 * @Date 12/07/2018
 */
public abstract class ISoftProfilerBaseBean
{
    /**
     * Tag publico para ser usado por el log del sistema para
     * identificar usuarios invitados.
     */
    public static String USER_GUEST_LOG = "GUEST";
    /**
     * Constante que se encarga de referenciar el objeto de sesion
     * que manejan los usuarios al tener una sesion activa.
     * Esta constante permite referenciar de forma adecuada
     * el objeto de sesion del usuario actual de isoftnnita.
     */
    public static String CONSTANT_USER_SESION = "loginUsuarioActivo";

    public static FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }

    public static Application getApplication(){
        return getFacesContext().getApplication();
    }

    public static UIViewRoot getViewRoot(){
        return getFacesContext().getViewRoot();
    }

    /**
     * Devuelve HttpSession
     * @return Devuelve HttpSession
     */
    public static HttpSession getHttpSession()
    {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    /**
     * Regresa el session Id del contexto Faces
     * @return Session ID
     */
    public static String getSessionId()
    {
        return getHttpSession().getId();
    }

    /**
     * Metodo de la capa web que encripta las claves
     * @param password clave a encriptar
     * @return clave encriptada
     * @throws ServiceException Excepcion generica si ocurre un error
     */
    public static String encryptPass(String password) throws ServiceException
    {
        String encrypt = "";
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            System.out.println("Digest(in hex format):: " + sb.toString());

            //convert the byte to hex format method 2
            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1) hexString.append('0');
                hexString.append(hex);
            }
            System.out.println("Digest(in hex format):: " + hexString.toString());
            encrypt = hexString.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new ServiceException("Error Transformando clave");
        }
        return encrypt;
    }

    /**
     * Metodo que busca un recurso del archivo de texto
     * @param key llave a buscar
     * @return contenido de llave
     */
    public static String findMessage(String key){
        TextBundle text = new TextBundle();
        return text.handleGetObject(key).toString();
    }

    /**
     * Metodo que busca un recurso del archivo de texto
     * @param key llave a buscar
     * @return contenido de llave
     */
    public static String findMessageError(String key){
        TextBundle text = new TextBundle();
        String keyfind = "login.error."+key;
        return text.handleGetObject(keyfind).toString();
    }

    /**
     * Agrega un mensaje de error al contexto
     * de faces para que aparezca en los formularios
     * si el tag no esta no se mostraran.
     * @param message
     */
    public static void addErrorMessage(final String message) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
        getFacesContext().addMessage(null, fm);
    }

    /**
     * Agrega un mensaje de error al contexto
     * de faces para los validators implementados en vistas
     * @param message
     */
    public static void addErrorMessageValidator(final String message) {
        FacesMessage msg =
                new FacesMessage(message,
                        message);
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(msg);
    }
}
