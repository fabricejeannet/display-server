package web.application;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Method;
import org.restlet.data.Status;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.routing.Filter;

import java.util.HashSet;

public class FiltreCors extends Filter {
    public FiltreCors(Context context) {
        super(context);
    }

    @Override
    protected int beforeHandle(Request request, Response response) {
        if (Method.OPTIONS.equals(request.getMethod())) {

            response.setAccessControlAllowOrigin("*");

            HashSet<Method> methods = new HashSet<>();
            methods.add(new Method("GET"));
            methods.add(new Method("POST"));
            methods.add(new Method("DELETE"));
            methods.add(new Method("OPTIONS"));
            response.setAccessControlAllowMethods(methods);

            HashSet<String> headers = new HashSet<>();
            headers.add("accept");
            headers.add("origin");
            headers.add("X-Requested-With");
            headers.add("Content-Type");
            response.setAccessControlAllowHeaders(headers);

            response.setAccessControlAllowCredentials(true);

            response.setEntity(new EmptyRepresentation());
            response.setStatus(Status.SUCCESS_OK);
            return Filter.SKIP;
        }

        return super.beforeHandle(request, response);
    }

    @Override
    protected void afterHandle(Request request, Response response) {
        if (!Method.OPTIONS.equals(request.getMethod())) {
            response.setAccessControlAllowOrigin("*");
        }
        super.afterHandle(request, response);
    }


}

