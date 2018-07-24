package co.isoft.nnita.profile.configuration.dom.impl;

import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;
import co.isoft.nnita.profile.configuration.dom.ISesionActive;
import org.springframework.stereotype.Service;

@Service("iSesionActiveImpl")
public class ISesionActiveImpl implements ISesionActive
{
    /**
     * Datos de sesion de usuario
     */
    private DatosSesionUsuario datosSesion;

    public void setDatosSesion(DatosSesionUsuario datosSesion)
    {
        this.datosSesion = datosSesion;
    }

    @Override
    public DatosSesionUsuario getDatosSesion()
    {
        return this.datosSesion;
    }
}
