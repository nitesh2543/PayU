package com.example.nitesh.payu.util.retrofit;

import java.util.List;

/**
 * Created by nitesh on 13/8/17.
 */

public class Errors {

    private List<Error> errors;

    public Errors() {
    }

    public List<Error> getErrors() {
        return errors;
    }

    public Error getError() throws Exception {
        try {
            return errors.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new Exception("No errors");
        }
    }

    public class Error {
        private String message;
        private String source;

        private Error() {
        }

        public String getMessage() {
            return message;
        }

        public String getSource() {
            return source;
        }
    }
}
