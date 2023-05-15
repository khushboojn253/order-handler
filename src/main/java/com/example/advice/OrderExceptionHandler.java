package advice;

@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler{

  @ExceptionHandler(value
      = {Exception.class})
  protected ResponseEntity<Object> handleException(
      RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "Oops, technical error occured.";
    return handleExceptionInternal(ex, bodyOfResponse,
        new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
}
