package com.travelsketch.travel.docs;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

public class ApiDocumentUtil {

    public static OperationRequestPreprocessor getDocumentRequest() {
        return preprocessRequest(
//            modifyUris()
//                .scheme("http")
//                .host("localhost"),
//                .removePort(),
            prettyPrint()
        );
    }

    public static OperationResponsePreprocessor getDocumentResponse() {
        return preprocessResponse(prettyPrint());
    }
}
