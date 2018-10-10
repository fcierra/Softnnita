package co.isoft.nnita.profile.beans;

import co.isoft.nnita.profile.util.ISoftProfilerBaseBean;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * Bean FRONT-END para el manejo de la informacion
 * en el login de sistema.
 *
 * @author Yaher Carrillo
 * @Date 09/07/2018
 */
@Component
@ManagedBean(name = "commonsBean")
@RequestScoped
public class CommonsBean extends ISoftProfilerBaseBean implements Serializable
{

}
