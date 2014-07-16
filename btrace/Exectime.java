package io.github.tacy;

import com.sun.btrace.annotations.*;
import com.sun.btrace.aggregation.*;
import static com.sun.btrace.BTraceUtils.*;

/**
 * 
 */
@BTrace public class Exectime {
    
    private static Aggregation methodDuration = Aggregations
            .newAggregation(AggregationFunction.AVERAGE);
            
    @OnMethod(clazz = "com.primeton.access.client.impl.processor.CommonServiceProcessor", 
        method = "process", 
        location = @Location(Kind.RETURN))
    public static void started(@Duration long duration) {
        Aggregations.addToAggregation(methodDuration, 
                                   duration / 1000);
        }
    
  @OnTimer(value = 10000)
  public static void printAvgMethodDuration() {
      Aggregations.printAggregation(
              "Average method duration (ms)", methodDuration);
clearAggregation(methodDuration);
  }
}
