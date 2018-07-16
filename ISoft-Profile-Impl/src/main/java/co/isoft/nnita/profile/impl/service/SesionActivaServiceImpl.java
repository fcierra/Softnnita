package co.isoft.nnita.profile.impl.service;

import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.services.SesionActivaService;

/**
 * Implementacion de conusmo de los datos
 * de sesion del usuario conectado.
 * @author Yaher Carrillo
 * @Date 13/07/2018
 */
public class SesionActivaServiceImpl implements SesionActivaService
{
    public Usuarios getUsuarioSesion() throws ServiceException
    {
        Usuarios usuario = null;
        return usuario;
    }
}
