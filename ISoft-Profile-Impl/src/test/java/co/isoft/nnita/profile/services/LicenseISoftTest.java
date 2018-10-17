package co.isoft.nnita.profile.services;

import co.isoft.nnita.profile.api.dao.CanalesDao;
import co.isoft.nnita.profile.api.dao.JwtDao;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Canales;
import co.isoft.nnita.profile.api.modelsweb.DatosLicencia;
import co.isoft.nnita.profile.api.util.EnumTypesLicense;
import co.isoft.nnita.profile.api.util.JSonUtil;
import co.isoft.nnita.profile.impl.dao.EntityDaoImplTest;
import co.isoft.nnita.profile.impl.dao.impl.JwtImpl;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementacion de servicio de integracion
 * para encriptar datos de manejo de licencias.
 * @author Yaher Carrillo
 * @Date 07/10/2018
 */
public class LicenseISoftTest
{
    private JwtDao jwtDao = new JwtImpl();

    @Test
    public void shouldGenerateTokenCredenciales() throws Exception {
        Map<String, Object> mapConfigurtation = new HashMap<>();
        mapConfigurtation.put("client", "ISOFT");
        mapConfigurtation.put("access", "Prueba12$");

        DatosLicencia datosLicencia = new DatosLicencia();
        datosLicencia.setClienteISoft("ISOFT");
        datosLicencia.setCanal(1);
        datosLicencia.setFechaInicio(new Date());
        datosLicencia.setFechaFin(new Date());
        datosLicencia.setIp("127.0.0.1");
        datosLicencia.setTipoLicencia(EnumTypesLicense.OPEN_ALL.getCode());

        String generate = jwtDao.generarToken(datosLicencia, mapConfigurtation);
        System.out.println("Put Generate: " + generate);
    }

    @Test
    public void shouldDeGenerateTokenObjectsFailPass() throws Exception {
        Map<String, Object> mapConfigurtation = new HashMap<>();
        mapConfigurtation.put("client", "ISOFT");
        mapConfigurtation.put("access", "Prueba12$");
        Map<String, Object> mapConfigurtationOther = new HashMap<>();
        mapConfigurtationOther.put("client", "ISOFT");
        mapConfigurtationOther.put("access", "Prueba12$");

        DatosLicencia datosLicencia = new DatosLicencia();
        datosLicencia.setClienteISoft("ISOFT");
        datosLicencia.setCanal(1);
        datosLicencia.setFechaInicio(new Date());
        datosLicencia.setFechaFin(new Date());
        datosLicencia.setIp("127.0.0.1");
        datosLicencia.setTipoLicencia(EnumTypesLicense.OPEN_ALL.getCode());


        try {
            String generate = (String) jwtDao.deGenerarToken(jwtDao.generarToken(datosLicencia, mapConfigurtation), mapConfigurtationOther);
            DatosLicencia quote = (DatosLicencia) JSonUtil.fromJson(generate, DatosLicencia.class);
            System.out.println("Put Degenerate : " + quote);
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
    }

}
