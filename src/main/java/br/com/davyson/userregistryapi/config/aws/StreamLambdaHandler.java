package br.com.davyson.userregistryapi.config.aws;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.model.HttpApiV2ProxyRequest;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import br.com.davyson.userregistryapi.UserregistryapiApplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamLambdaHandler implements RequestStreamHandler {
    private static final SpringBootLambdaContainerHandler<HttpApiV2ProxyRequest, AwsProxyResponse> handler;

    static {
        try{
            handler = SpringBootLambdaContainerHandler.getHttpApiV2ProxyHandler(UserregistryapiApplication.class);
        }catch(ContainerInitializationException containerEx){
            throw new RuntimeException("Não foi possível iniciar a aplicação"+ containerEx.getMessage());
        }
    }
    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        handler.proxyStream(input, output, context);
    }
}
