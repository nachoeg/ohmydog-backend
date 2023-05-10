import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        // Permite el acceso desde cualquier origen. Ajusta esto según tus necesidades.
        headers.add("Access-Control-Allow-Origin", "*");

        // Agrega los métodos HTTP permitidos
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");

        // Agrega los encabezados permitidos
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }
}
