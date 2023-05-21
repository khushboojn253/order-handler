package demo.src.main.java.com.example.advice;

@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler{

  /**
   * This advice will be called in case of any exception
   * @param ex
   * @param request
   * @return ResponseEntity
   */
  @ExceptionHandler(value
      = {Exception.class})
  protected ResponseEntity<Object> handleException(
      RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "Oops, technical error occured.";
    return handleExceptionInternal(ex, bodyOfResponse,
        new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
}
