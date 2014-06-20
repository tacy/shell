 /* BTrace Script Template */  
 package io.github.tacy.btrace;  

 import com.sun.btrace.annotations.*;  
 import com.sun.btrace.aggregation.*; 
 
 import static com.sun.btrace.BTraceUtils.*;  
  
 @BTrace  
 public class LineExectime {  
    @TLS private static long startTime; 
    private static Aggregation methodDuration = Aggregations
            .newAggregation(AggregationFunction.AVERAGE);
    private static Aggregation his = Aggregations.newAggregation(AggregationFunction.QUANTIZE);

    @OnMethod(  
        clazz="com.primeton.access.client.impl.processor.CommonServiceProcessor",  
        //location=@Location(value=Kind.LINE, line=47))
        method="process",
        location=@Location(Kind.ENTRY))
    public static void onEnter(){  
    //println("enter this method");  
        startTime= timeNanos();
        //jstack();
    }  
 
    @OnMethod(  
        clazz="com.primeton.access.client.impl.processor.CommonServiceProcessor",  
        location=@Location(value=Kind.LINE, line=68))  
    public static void onReturn(){  
        //println("method end!");  
        int duration = (int)(timeNanos()-startTime)/1000000;  
        //println(duration);  
        addToAggregation(methodDuration,duration);
        addToAggregation(his, duration);
        //jstack();
        //addToAggregation(globalCount, duration);  
    }    
 
    @OnTimer(30000)  
    public static void onEvent() {  
        println("---------------------------------------------");  
        printAggregation("Hist:", his);
        printAggregation("Average:", methodDuration);
        clearAggregation(methodDuration);
        clearAggregation(his);
        //printAggregation("Global Count", globalCount);  
        println("---------------------------------------------");  
    }  
 } 
