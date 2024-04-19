public class InvalidQuizException extends Exception{
    /**
     * Constructor without parameters.
     */
    InvalidQuizException() {}
    
    /**
     * Constructor with a message parameter.
     * @param param The message describing the exception.
     */
    InvalidQuizException(String param) {
        super(param);
    }
}

// DDP_D_2306165660_TheoAnandaLemuel_Lab6