package demo.src.main.java.com.example.factory;

import demo.src.main.java.com.example.processor.OrderProcessor;

@Component
public class OrderProcessorFactory {

  /**
   * Implements Factory design pattern to create the object
   * of Item processor based on the parameter given at the
   * time of execution of job.
   * @param param
   * @return ItemProcessor
   */
  public ItemProcessor buildOrderProcessor(String param) {
    if(param.equals("Number")){
      return new OrderProcessor();
    }else if(param.equals("Weight")){
      // return Processor written on weight
    } else {
      // return default processor
    }
  }

}
