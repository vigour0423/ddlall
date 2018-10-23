package com.ddl.learn.log.datachange;


import com.ddl.learn.log.datachange.dto.Tracer;

/**
 * Created by zhuyuefan on 2017/10/20.
 */
public class DataChangeTracer {

    private static final ThreadLocal threadLocal = new ThreadLocal();

    public static void startTrace(String transactionId, String module, String function, String changeType, String reason, String operatorName,
                                  String operatorAccount) {
        Object tracer = threadLocal.get();
        if (tracer == null) {
            tracer = new Tracer(transactionId, module, function, changeType, reason, operatorName,
                    operatorAccount);
            threadLocal.set(tracer);
        } else {
            ((Tracer) tracer).setTrace(true);
        }
    }

    public static void stopTrace() {
        threadLocal.remove();
    }

    public static boolean isTracing() {
        Object tracer = threadLocal.get();
        if (tracer == null) {
            return false;
        }
        return ((Tracer) tracer).isTrace();
    }

    public static void pauseTrace() {
        Object tracer = threadLocal.get();
        ((Tracer) tracer).setTrace(false);
    }

    public static void resumeTrace() {
        Object tracer = threadLocal.get();
        ((Tracer) tracer).setTrace(true);
    }

    public static Tracer getTracer() {
        return (Tracer) threadLocal.get();
    }
}
