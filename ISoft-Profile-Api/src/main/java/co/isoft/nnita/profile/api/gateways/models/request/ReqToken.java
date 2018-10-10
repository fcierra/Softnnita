/*
 * ESTE COMPONENTE FUE REALIZADO BAJO LA METODOLOGIA DE DESARROLLO DE 
 * INFORMATICA & TECNOLOGIA Y SE ENCUENTRA PROTEGIDO POR LAS LEYES DE 
 * DERECHOS DE AUTOR.
 */
package co.isoft.nnita.profile.api.gateways.models.request;

/**
 * Request modelo para transportar un token de seguridad
 * Created by Yaher Carrillo on 22/06/2017.
 *
 * @author Yaher Carrillo
 * @since 22/06/2017
 */
public class ReqToken
{

    /**
     * Referencia  a un token de seguridad
     */
    private String tokenSecurity;

    /**
     * @return
     */
    public String getTokenSecurity() {
        return tokenSecurity;
    }

    /**
     * @param tokenSecurity
     */
    public void setTokenSecurity(String tokenSecurity) {
        this.tokenSecurity = tokenSecurity;
    }
}
