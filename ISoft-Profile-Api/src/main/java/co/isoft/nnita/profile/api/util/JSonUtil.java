package co.isoft.nnita.profile.api.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Yaher Carrillo on 16/06/2017.
 */
public class JSonUtil
{
    /**
     * Objeto utilizado para convertir los objetos a formato JSON
     */
    private static Gson gson;

    /** Conversor por defecto a JSON */
    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    private JSonUtil() {

    }

    /**
     * Convierte a JSON un objeto.
     *
     * @param valor Valor del objeto.
     * @return Conversiond de objeto a JSON.
     */
    public static String toJson(Object valor) {
        JsonElement jsonElement = gson.toJsonTree(valor);
        jsonElement.getAsJsonObject().addProperty("doctype", valor.getClass().getSimpleName());
        return gson.toJson(jsonElement);
    }

    /**
     * Convierte un Json a el objeto nativo
     *
     * @param json
     * @return
     */
    public static Object fromJson(String json, Type typeObject) {
        return gson.fromJson(json,
                typeObject);
    }

    /**
     * Convierte un string con datos en formato JSON a una lista de objetos.
     *
     * @param json     JSON con los datos
     * @param listType Lista con el tipo de datos. Ejemplo si se desea regresa una lista de Entidad, el parametro debe ser new TypeToken<List<Entidad>>(){}.getType();
     * @return Lista con los objetos convertidos
     */
    public static <T> List<T> listaDesdeJson(String json, Type listType) {
        return gson.fromJson(json, listType);
    }
}
